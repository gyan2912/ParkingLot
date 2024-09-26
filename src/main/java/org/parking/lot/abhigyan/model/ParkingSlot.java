package org.parking.lot.abhigyan.model;

public class ParkingSlot {
    private Car parkedCar;
    private Integer slotNumber;
    public ParkingSlot(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public boolean isSlotAvailable() {
        return parkedCar == null;
    }

    public void assignCar(Car car) {
        this.parkedCar = car;
    }

    public void unAssignCar() {
        this.parkedCar = null;
    }
}
