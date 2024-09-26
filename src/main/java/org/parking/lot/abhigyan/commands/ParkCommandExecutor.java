package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.exceptions.NoFreeSlotAvailableException;
import org.parking.lot.abhigyan.model.Car;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) {
        final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try {
            final Integer parkingSlot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Alloted slot Number: " + parkingSlot);
        } catch (NoFreeSlotAvailableException e) {
            outputPrinter.parkingLotFull();
        }
    }

}
