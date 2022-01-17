package br.com.vehiclessales.demo.domain.repository;

import br.com.vehiclessales.demo.domain.entities.Vehicle;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle,String> {
    @Override
    <S extends Vehicle> List<S> findAll(Example<S> example, Sort sort);
}
