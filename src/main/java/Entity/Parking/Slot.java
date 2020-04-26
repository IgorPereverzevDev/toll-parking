package Entity.Parking;

import Entity.CarEngine;

public class Slot {

    private boolean free;
    private final CarEngine carEngine;

    public Slot(boolean free, CarEngine carEngine) {
        this.free = free;
        this.carEngine = carEngine;
    }

    public boolean isFree() {
        return free;
    }

    public CarEngine getSlotDetails() {
        return carEngine;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

}
