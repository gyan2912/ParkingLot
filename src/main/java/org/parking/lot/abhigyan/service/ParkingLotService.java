package org.parking.lot.abhigyan.service;

import org.parking.lot.abhigyan.exceptions.ParkingLotException;
import org.parking.lot.abhigyan.model.Car;
import org.parking.lot.abhigyan.model.ParkingLot;
import org.parking.lot.abhigyan.model.ParkingSlot;
import org.parking.lot.abhigyan.model.parking.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(final Car car) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void makeSlotAvailable(final Integer slot) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slot);
        parkingStrategy.addSlot(slot);
    }

    public List<ParkingSlot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<ParkingSlot> occupiedSlots = new ArrayList<>();
        final Map<Integer, ParkingSlot> allSlots = parkingLot.getSlots();
        for (int i = 0; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final ParkingSlot parkingSlot = allSlots.get(i);
                if (!parkingSlot.isSlotAvailable()) {
                    occupiedSlots.add(parkingSlot);
                }
            }
        }
        return occupiedSlots;
    }

    private void validateParkingLotExists() {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking lot doesn't exists.");
        }
    }

    public List<ParkingSlot> getSlotsForColor(final String color) {
        List<ParkingSlot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .toList();
    }
}
