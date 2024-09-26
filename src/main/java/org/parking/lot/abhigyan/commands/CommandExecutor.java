package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    public boolean commandValidate(Command command) {
        if (!command.getCommandName().equals(getName())) {
            return false;
        }
        return validate(command);
    }

    protected abstract String getName();

    protected abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
