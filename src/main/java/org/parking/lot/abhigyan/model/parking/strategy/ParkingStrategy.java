package org.parking.lot.abhigyan.model.parking.strategy;

public interface ParkingStrategy {

    public void addSlot(Integer slotId);

    public void removeSlot(Integer slotId);

    public Integer getNextSlot();
}
