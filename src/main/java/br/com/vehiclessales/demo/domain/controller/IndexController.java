package br.com.vehiclessales.demo.domain.controller;


import br.com.vehiclessales.demo.domain.entities.Vehicle;
import br.com.vehiclessales.demo.domain.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private VehicleService vehicleService;




}
