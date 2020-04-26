package Entity.Car;

import Entity.CarEngine;
import Entity.EngineType;

public class Gasoline implements CarEngine {

    private final EngineType engineType;

    public Gasoline(EngineType engineType) {
        this.engineType = engineType;

    }

    @Override
    public Gasoline createGasoline(EngineType carEngine) {
        return new Gasoline(carEngine);
    }

    @Override
    public Electric createElectric(EngineType carEngine, int power) {
        return null;
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public EngineType getEngineType() {
        return engineType;
    }
}
