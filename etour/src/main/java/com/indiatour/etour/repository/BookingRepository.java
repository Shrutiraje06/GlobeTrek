package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.indiatour.etour.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT SUM(b.totalAmount) FROM Booking b")
    Double getTotalRevenue();
    List<Booking> findByCustomerEmail(String email);
}