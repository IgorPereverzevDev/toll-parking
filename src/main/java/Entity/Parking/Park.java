package Entity.Parking;

public class Park {

    private final Slot slot;
    private final ParkingBill parkingBill;

    public Park(Slot slot, ParkingBill parkingBill) {
        this.slot = slot;
        this.parkingBill = parkingBill;
    }

    public ParkingBill getParkingBill() {
        return parkingBill;
    }

    public Slot getSlot() {
        return slot;
    }


}
