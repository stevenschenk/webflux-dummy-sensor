package nl.quintor.dummysensor;

import nl.quintor.dummysensor.Sensor.TemperatureSensor;
import nl.quintor.dummysensor.model.Temperature;
import nl.quintor.dummysensor.model.Unit;
import nl.quintor.dummysensor.repository.TemperatureRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class DummysensorApplication {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DummysensorApplication.class, args);
        ApplicationContextProvider.getApplicationContext().getBean(MongoService.class).makeCollectionCapped("temperature", 8192);
        pushDataToDatabase();
    }


    /**
     * Push temperature to database every second
     */
    private static void pushDataToDatabase() throws InterruptedException {
        var repo = ApplicationContextProvider.getApplicationContext().getBean(TemperatureRepository.class);
        var sensor = new TemperatureSensor();

        while (true) {
            var temp = Temperature.builder()
                    .temperature(sensor.getValue())
                    .unit(Unit.CELSIUS)
                    .timestamp(LocalDateTime.now())
                    .build();

            randomChangeToFahrenheit(temp);
            randomClearValues(temp);
            repo.save(temp).subscribe();
            Thread.sleep(1000);
        }
    }

    private static void randomChangeToFahrenheit(Temperature temperature) {
        if (random.nextDouble() < 0.25) {
            temperature.setUnit(Unit.FAHRENHEIT);
            temperature.setTemperature((temperature.getTemperature() * 1.8) + 32);
        }
    }

    private static void randomClearValues(Temperature temperature) {
        if (random.nextDouble() < 0.15) {
            temperature.setUnit(null);
        }
    }
}

