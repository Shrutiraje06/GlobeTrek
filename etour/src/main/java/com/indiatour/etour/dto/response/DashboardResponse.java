package com.indiatour.etour.dto.response;

public class DashboardResponse {

    private long totalCustomers;
    private long totalPackages;
    private long totalBookings;
    private double totalRevenue;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalCustomers,
                             long totalPackages,
                             long totalBookings,
                             double totalRevenue) {
        this.totalCustomers = totalCustomers;
        this.totalPackages = totalPackages;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(long totalPackages) {
        this.totalPackages = totalPackages;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}