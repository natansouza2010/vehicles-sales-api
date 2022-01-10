package br.com.vehiclessales.demo.domain.repository;

import br.com.vehiclessales.demo.domain.entities.Vehicle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends MongoRepository<Vehicle,String> {

}
