package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.model.ParkingSlot;
import org.parking.lot.abhigyan.service.ParkingLotService;

import java.util.List;

public class ColorToSlotNumberCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public ColorToSlotNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    protected String getName() {
        return COMMAND_NAME;
    }

    @Override
    protected boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final String color = command.getParams().get(0);
        List<ParkingSlot> slotList = parkingLotService.getSlotsForColor(color);
        for (ParkingSlot parkingSlot : slotList) {
            outputPrinter.printWithNewLine(parkingSlot.getSlotNumber().toString());
        }
    }
}
