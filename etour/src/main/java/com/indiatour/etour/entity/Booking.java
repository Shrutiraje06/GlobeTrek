package com.indiatour.etour.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    @Column(name = "number_of_persons")
    private Integer numberOfPersons;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TourPackage tourPackage;

    // Default Constructor
    public Booking() {
    }

    // Parameterized Constructor
    public Booking(Long bookingId, LocalDate bookingDate, LocalDate travelDate,
                   Integer numberOfPersons, String bookingStatus,
                   Double totalAmount, User customer,
                   TourPackage tourPackage) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
        this.numberOfPersons = numberOfPersons;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.tourPackage = tourPackage;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate=" + bookingDate +
                ", travelDate=" + travelDate +
                ", numberOfPersons=" + numberOfPersons +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}