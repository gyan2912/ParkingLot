package org.parking.lot.abhigyan;

import org.parking.lot.abhigyan.commands.CommandExecutorFactory;
import org.parking.lot.abhigyan.exceptions.InvalidModeException;
import org.parking.lot.abhigyan.mode.FileMode;
import org.parking.lot.abhigyan.mode.InteractiveMode;
import org.parking.lot.abhigyan.service.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);
        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileInputMode(args)) {
            new FileMode(outputPrinter, commandExecutorFactory, args[0]).process();
        } else {
            throw new InvalidModeException();
        }
        System.out.println("Hello world!");
    }

    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }

    private static boolean isFileInputMode(final String[] args) {
        return args.length == 1;
    }
}