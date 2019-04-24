package nl.quintor.dummysensor.repository;

import nl.quintor.dummysensor.model.Temperature;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends ReactiveMongoRepository<Temperature, Long> {
}
