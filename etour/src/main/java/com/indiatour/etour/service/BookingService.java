package com.indiatour.etour.service;

import java.util.List;
import com.indiatour.etour.entity.Booking;

public interface BookingService {

    Booking saveBooking(Booking booking);

    List<Booking> getAllBookings();
    List<Booking> getBookingHistory(String email);

    Booking getBookingById(Long id);

    Booking updateBooking(Long id, Booking booking);

    void deleteBooking(Long id);
    
}