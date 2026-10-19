package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.Booking;
import com.indiatour.etour.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    @Override
    public List<Booking> getBookingHistory(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {

        Booking existingBooking = bookingRepository.findById(id).orElse(null);

        if (existingBooking != null) {
            existingBooking.setBookingDate(booking.getBookingDate());
            existingBooking.setTravelDate(booking.getTravelDate());
            existingBooking.setNumberOfPersons(booking.getNumberOfPersons());
            existingBooking.setBookingStatus(booking.getBookingStatus());
            existingBooking.setTotalAmount(booking.getTotalAmount());
            existingBooking.setCustomer(booking.getCustomer());
            existingBooking.setTourPackage(booking.getTourPackage());

            return bookingRepository.save(existingBooking);
        }

        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}