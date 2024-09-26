package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Car;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.model.ParkingSlot;
import org.parking.lot.abhigyan.service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    private static String padString(final String word, final int length) {
        return word + " ".repeat(Math.max(0, length - word.length()));
    }

    @Override
    protected String getName() {
        return COMMAND_NAME;
    }

    @Override
    protected boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        List<ParkingSlot> slotList = parkingLotService.getOccupiedSlots();
        if (slotList.isEmpty()) {
            outputPrinter.parkingLotEmpty();
            return;
        }
        outputPrinter.statusHeader();
        for (ParkingSlot parkingSlot : slotList) {
            final Car parkedCar = parkingSlot.getParkedCar();
            final String slotId = parkingSlot.getSlotNumber().toString();
            outputPrinter.printWithNewLine(padString(slotId, 12)
                    + padString(parkedCar.getRegistrationNumber(), 19)
                    + parkedCar.getColor());
        }
    }
}
