package org.parking.lot.abhigyan.mode;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.commands.CommandExecutor;
import org.parking.lot.abhigyan.commands.CommandExecutorFactory;
import org.parking.lot.abhigyan.exceptions.InvalidCommandException;
import org.parking.lot.abhigyan.model.Command;

import java.io.IOException;

public abstract class Mode {
    private final CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(final OutputPrinter outputPrinter, final CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.commandValidate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }

    }

    public abstract void process() throws IOException;
}
