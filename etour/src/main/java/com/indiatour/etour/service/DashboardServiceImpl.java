package com.indiatour.etour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.dto.response.DashboardResponse;
import com.indiatour.etour.repository.BookingRepository;
import com.indiatour.etour.repository.UserRepository;
import com.indiatour.etour.repository.TourPackageRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Override
    public DashboardResponse getDashboard() {

    	long totalCustomers = userRepository.count();

        long totalPackages = tourPackageRepository.count();

        long totalBookings = bookingRepository.count();

        Double totalRevenue = bookingRepository.getTotalRevenue();

        if (totalRevenue == null) {
            totalRevenue = 0.0;
        }

        return new DashboardResponse(
                totalCustomers,
                totalPackages,
                totalBookings,
                totalRevenue);
    }
}