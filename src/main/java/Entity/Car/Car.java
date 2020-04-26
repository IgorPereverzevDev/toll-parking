package Entity.Car;

import Entity.CarEngine;

public class Car {

    private final String plate;
    private final CarEngine carEngine;

    public Car(String plate, CarEngine carEngine) {
        this.plate = plate;
        this.carEngine = carEngine;
    }

    public CarEngine getCarEngine() {
        return carEngine;
    }

    public String getPlate() {
        return plate;
    }

}
