package Entity.Car;

import Entity.CarEngine;
import Entity.EngineType;

public class Electric implements CarEngine {

    private final EngineType engineType;
    private final int power;

    public Electric(EngineType engineType, int power) {
        this.engineType = engineType;
        this.power = power;
    }

    @Override
    public Gasoline createGasoline(EngineType carEngine) {
        return null;
    }

    @Override
    public Electric createElectric(EngineType carEngine, int power) {
        return new Electric(carEngine, power);
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public EngineType getEngineType() {
        return engineType;
    }
}
