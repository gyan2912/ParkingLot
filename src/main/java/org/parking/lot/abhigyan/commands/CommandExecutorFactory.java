package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final ParkingLotService parkingLotService) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        commands.put(CreateParkingLotCommandExecutor.COMMAND_NAME,
                new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        commands.put(ColorToRegNumberCommandExecutor.COMMAND_NAME,
                new ColorToRegNumberCommandExecutor(parkingLotService, outputPrinter));
        commands.put(ColorToSlotNumberCommandExecutor.COMMAND_NAME,
                new ColorToSlotNumberCommandExecutor(parkingLotService, outputPrinter));
        commands.put(LeaveCommandExecutor.COMMAND_NAME,
                new LeaveCommandExecutor(parkingLotService, outputPrinter));
        commands.put(ParkCommandExecutor.COMMAND_NAME,
                new ParkCommandExecutor(parkingLotService, outputPrinter));
        commands.put(SlotForRegCommandExecutor.COMMAND_NAME,
                new SlotForRegCommandExecutor(parkingLotService, outputPrinter));
        commands.put(StatusCommandExecutor.COMMAND_NAME,
                new StatusCommandExecutor(parkingLotService, outputPrinter));
        commands.put(ExitCommandExecutor.COMMAND_NAME,
                new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        return commands.get(command.getCommandName());
    }
}
