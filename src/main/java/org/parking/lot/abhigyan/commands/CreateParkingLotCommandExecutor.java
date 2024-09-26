package org.parking.lot.abhigyan.commands;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.model.Command;
import org.parking.lot.abhigyan.model.ParkingLot;
import org.parking.lot.abhigyan.model.parking.strategy.NaturalOrderParkingStrategy;
import org.parking.lot.abhigyan.service.ParkingLotService;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final int parkingCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderParkingStrategy());
        outputPrinter.printWithNewLine("Created a parking lot" + parkingLot.getCapacity() + "slots.");
    }

}
