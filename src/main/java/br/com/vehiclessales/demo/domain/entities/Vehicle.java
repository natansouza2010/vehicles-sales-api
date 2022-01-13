package br.com.vehiclessales.demo.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicle")
public class Vehicle {
    @Id
    private String id;
    private String name;
    private String brand;
    private String model;
    private Double value;
    private String urlPhoto;


}
