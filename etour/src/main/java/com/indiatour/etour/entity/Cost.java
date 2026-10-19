package com.indiatour.etour.entity;

import jakarta.persistence.*;

@Entity
@Table(name="costs")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costId;

    private Double hotelCost;

    private Double transportCost;

    private Double foodCost;

    private Double guideCost;

    private Double gst;

    private Double totalCost;

    @OneToOne
    @JoinColumn(name="package_id")
    private TourPackage tourPackage;

    public Cost() {
    }

    public Long getCostId() {
        return costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }

    public Double getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(Double hotelCost) {
        this.hotelCost = hotelCost;
    }

    public Double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(Double transportCost) {
        this.transportCost = transportCost;
    }

    public Double getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(Double foodCost) {
        this.foodCost = foodCost;
    }

    public Double getGuideCost() {
        return guideCost;
    }

    public void setGuideCost(Double guideCost) {
        this.guideCost = guideCost;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }
}