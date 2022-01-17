package br.com.vehiclessales.demo.domain.services;

import br.com.vehiclessales.demo.domain.entities.Vehicle;
import br.com.vehiclessales.demo.domain.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> listAll(){
        Sort sort = Sort.by("value").descending();
        return vehicleRepository.findAll(sort);
    }

    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }


    public Optional<Vehicle> findById(String id) {
        return vehicleRepository.findById(id);

    }

    public void deleteById(String id){
        if(findById(id).isPresent()){
            vehicleRepository.deleteById(id);
        }

    }
}
