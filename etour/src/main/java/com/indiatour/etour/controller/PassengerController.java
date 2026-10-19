package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.Passenger;
import com.indiatour.etour.service.PassengerService;

@RestController
@RequestMapping("/passengers")
@CrossOrigin(origins = "*")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Add Passenger
    @PostMapping
    public Passenger savePassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    // Get All Passengers
    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // Get Passenger By ID
    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    // Update Passenger
    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable Long id,
                                     @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);
    }

    // Delete Passenger
    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Long id) {

        passengerService.deletePassenger(id);

        return "Passenger deleted successfully.";
    }
}