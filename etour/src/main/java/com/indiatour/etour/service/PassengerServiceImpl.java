package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.Passenger;
import com.indiatour.etour.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public Passenger updatePassenger(Long id, Passenger passenger) {

        Passenger existingPassenger = passengerRepository.findById(id).orElse(null);

        if (existingPassenger != null) {

            existingPassenger.setFullName(passenger.getFullName());
            existingPassenger.setAge(passenger.getAge());
            existingPassenger.setGender(passenger.getGender());
            existingPassenger.setEmail(passenger.getEmail());
            existingPassenger.setPhone(passenger.getPhone());
            existingPassenger.setBooking(passenger.getBooking());

            return passengerRepository.save(existingPassenger);
        }

        return null;
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}