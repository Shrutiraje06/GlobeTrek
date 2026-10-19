package com.indiatour.etour.service;

import java.util.List;

import com.indiatour.etour.entity.Passenger;

public interface PassengerService {

    Passenger savePassenger(Passenger passenger);

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(Long id);

    Passenger updatePassenger(Long id, Passenger passenger);

    void deletePassenger(Long id);
}