package com.driver.model;
import javax.persistence.*;

@Entity
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cabId;

    private int ratePerKm;
    private Boolean isAvailable;

    @OneToOne
    @JoinColumn
    Driver driver;

    @OneToOne(mappedBy = "cab", cascade = CascadeType.ALL)
    TripBooking tripBooking;

    public Cab() {
    }

    public Cab(int cabId, int ratePerKm, Boolean isAvailable) {
        this.cabId = cabId;
        this.ratePerKm = ratePerKm;
        this.isAvailable = isAvailable;
    }

    public Driver getDriver() {
        return driver;
    }

    public TripBooking getTripBooking() {
        return tripBooking;
    }

    public void setTripBooking(TripBooking tripBooking) {
        this.tripBooking = tripBooking;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public int getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(int ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}