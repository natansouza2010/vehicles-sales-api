package br.com.vehiclessales.demo.domain.controller;


import br.com.vehiclessales.demo.domain.entities.Vehicle;

import br.com.vehiclessales.demo.domain.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/veiculos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/")
    public ResponseEntity<Vehicle> saveVehicle(@Valid @RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.save(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> getVehicle (@PathVariable("id") String id){
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if(vehicle.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getVehicles(){
        List<Vehicle> vehicleList = vehicleService.listAll();
        if(vehicleList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vehicleList);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") String id, @RequestBody @Valid Vehicle vehicle){
        Optional<Vehicle> ve = vehicleService.findById(id);
        if(ve.isPresent()){
            ve.get().setName(vehicle.getName());
            ve.get().setBrand(vehicle.getBrand());
            ve.get().setUrlPhoto(vehicle.getUrlPhoto());
            return new ResponseEntity<>(vehicleService.save(ve.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle (@PathVariable("id") String id){
        if(vehicleService.findById(id).isPresent()){
            vehicleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }





}
