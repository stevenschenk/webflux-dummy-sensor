package nl.quintor.dummysensor.Sensor;

import java.util.Random;

public class TemperatureSensor implements Sensor {
    private double currentTemperature;
    private Random random;

    public TemperatureSensor() {
        this.random = new Random();
        this.currentTemperature = random.nextDouble() * 25;
    }

    @Override
    public double getValue() {
        updateCurrentTemperature();
        return currentTemperature;
    }

    private void updateCurrentTemperature() {
        currentTemperature += getRandomDouble();
    }

    private double getRandomDouble() {
        return 0.5 - random.nextDouble(); //Return random double between -0.5 and 0.5
    }
}
