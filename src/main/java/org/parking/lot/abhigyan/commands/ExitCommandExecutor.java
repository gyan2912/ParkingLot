package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.service.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
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
        outputPrinter.end();
    }
}
