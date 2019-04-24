package nl.quintor.dummysensor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Document
public class Temperature {
    //    @Id
//    private long id;
    private double temperature;
    private Unit unit;
    private LocalDateTime timestamp;
}


