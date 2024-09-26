package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.model.ParkingSlot;
import org.parking.lot.abhigyan.service.ParkingLotService;

import java.util.List;
import java.util.Optional;

public class SlotForRegCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "slot_number_for_registration_number";

    public SlotForRegCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
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
        final List<ParkingSlot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String registrationNumber = command.getParams().get(0);
        final Optional<ParkingSlot> foundSlot = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(registrationNumber)).findFirst();
        if (foundSlot.isPresent()) {
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        } else {
            outputPrinter.notFound();
        }
    }
}
