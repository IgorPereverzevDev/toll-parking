package Entity;

import Entity.Car.Electric;
import Entity.Car.Gasoline;

public interface CarEngine {

    Gasoline createGasoline(EngineType carEngine);

    Electric createElectric(EngineType carEngine, int power);

    int getPower();

    EngineType getEngineType();

}
