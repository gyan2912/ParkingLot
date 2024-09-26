package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.service.ParkingLotService;
import org.parking.lot.abhigyan.validator.IntegerValidator;

public class LeaveCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    protected String getName() {
        return COMMAND_NAME;
    }

    @Override
    protected boolean validate(Command command) {
        if (command.getParams().size() != 1) {
            return false;
        }
        ;
        return IntegerValidator.isInteger(command.getParams().get(0));
    }

    @Override
    public void execute(Command command) {
        final int slotId = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotAvailable(slotId);
        outputPrinter.printWithNewLine("Slot " + slotId + " is now available");
    }
}
