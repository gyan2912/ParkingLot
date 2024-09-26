package org.parking.lot.abhigyan.model;

import org.parking.lot.abhigyan.exceptions.InvalidParkingSlotException;
import org.parking.lot.abhigyan.exceptions.ParkingLotException;
import org.parking.lot.abhigyan.exceptions.ParkingSlotAlreadyOccupiedException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static int MAX_CAPACITY = 10000;
    private int capacity;
    private Map<Integer, ParkingSlot> slots;

    public ParkingLot(final int capacity) {
        if (capacity > MAX_CAPACITY || capacity <= 0) {
            throw new ParkingLotException("Invalid capacity provided for the parking lot");
        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public Map<Integer, ParkingSlot> getSlots() {
        return slots;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingSlot park(Car car, final Integer slotId) {
        final ParkingSlot parkingSlot = getSlot(slotId);
        if (!parkingSlot.isSlotAvailable()) {
            throw new ParkingSlotAlreadyOccupiedException();
        }
        parkingSlot.assignCar(car);
        return parkingSlot;
    }

    public ParkingSlot makeSlotFree(final Integer slotId) {
        final ParkingSlot parkingSlot = getSlot(slotId);
        parkingSlot.unAssignCar();
        return parkingSlot;
    }

    private ParkingSlot getSlot(final Integer slotId) {
        if (slotId > getCapacity() || slotId <= 0) {
            throw new InvalidParkingSlotException();
        }
        final Map<Integer, ParkingSlot> allSlots = getSlots();
        if (!allSlots.containsKey(slotId)) {
            ParkingSlot parkingSlot = new ParkingSlot(slotId);
            allSlots.put(slotId, parkingSlot);
        }
        return allSlots.get(slotId);
    }
}


