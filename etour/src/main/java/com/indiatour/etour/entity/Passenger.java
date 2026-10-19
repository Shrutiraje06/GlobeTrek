package com.indiatour.etour.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "full_name")
    private String fullName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // Default Constructor
    public Passenger() {
    }

    // Parameterized Constructor
    public Passenger(Long passengerId, String fullName, Integer age,
                     String gender, String email, String phone,
                     Booking booking) {
        this.passengerId = passengerId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.booking = booking;
    }

    // Getters and Setters

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Passenger [passengerId=" + passengerId +
                ", fullName=" + fullName +
                ", age=" + age +
                ", gender=" + gender +
                ", email=" + email +
                ", phone=" + phone + "]";
    }
}