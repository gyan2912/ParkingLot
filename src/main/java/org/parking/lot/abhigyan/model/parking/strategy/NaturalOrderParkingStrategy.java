package org.parking.lot.abhigyan.model.parking.strategy;

import org.parking.lot.abhigyan.exceptions.NoFreeSlotAvailableException;

import java.util.TreeSet;

public class NaturalOrderParkingStrategy implements ParkingStrategy {

    TreeSet<Integer> slotTreeSet;

    public NaturalOrderParkingStrategy() {
        this.slotTreeSet = new TreeSet<>();
    }

    @Override
    public void addSlot(Integer slotId) {
        slotTreeSet.add(slotId);
    }

    @Override
    public void removeSlot(Integer slotId) {
        slotTreeSet.remove(slotId);
    }

    @Override
    public Integer getNextSlot() {
        if (slotTreeSet.isEmpty()) {
            throw new NoFreeSlotAvailableException();
        }
        return slotTreeSet.first();
    }
}
