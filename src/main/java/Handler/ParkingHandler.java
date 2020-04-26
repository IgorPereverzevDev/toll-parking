package Handler;

import Entity.Car.Car;
import Entity.Parking.Park;
import Entity.Parking.Parking;
import Entity.Parking.ParkingBill;
import Entity.Parking.Slot;
import Entity.PricingPolicies.PricingPolicies;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingHandler {

    private final Parking parking;

    public Parking getParking() {
        return parking;
    }

    public ParkingHandler(PricingPolicies pricingPolicies) {
        this.parking = new Parking(pricingPolicies);
    }

    public void park(Car car) {
        parking.getSlots().stream().filter(Slot::isFree)
                .filter(slot -> slot.getSlotDetails().getEngineType().equals(car.getCarEngine().getEngineType()))
                .filter(slot -> slot.getSlotDetails().getPower() == car.getCarEngine().getPower()).findFirst().map(s -> {
            var park = new Park(s, new ParkingBill(LocalDateTime.now(), car.getPlate()));
            parking.getBooked().put(car, park);
            return park;
        }).orElseThrow();
    }

    public ParkingBill leave(Car car) {
        var slot = parking.getSlots().stream().filter(s -> s.equals(parking.getBooked().get(car).getSlot()))
                .findFirst().orElseThrow();
        slot.setFree(true);
        return getBill(car);
    }


    private ParkingBill getBill(Car car) {
        var bill = parking.getBooked().get(car).getParkingBill();
        bill.setEnd(LocalDateTime.now());
        var hours = bill.getStart().until(bill.getEnd(), ChronoUnit.HOURS);
        bill.setAmount(parking.getPricingPolicies().getPrice(hours));
        parking.getBooked().remove(car);
        return bill;
    }
}
