package Entity.Parking;

import Entity.Car.Car;
import Entity.PricingPolicies.PricingPolicies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking {

    private final Map<Car, Park> booked;
    private final List<Slot> slots;
    private final PricingPolicies pricingPolicies;

    public Parking(PricingPolicies pricingPolicies) {
        this.pricingPolicies = pricingPolicies;
        this.booked = new HashMap<>();
        this.slots = new ArrayList<>();
    }

    public PricingPolicies getPricingPolicies() {
        return pricingPolicies;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public Map<Car, Park> getBooked() {
        return booked;
    }

}
