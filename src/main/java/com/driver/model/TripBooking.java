package com.driver.model;
import com.driver.model.TripStatus;

import javax.persistence.*;

@Entity
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    private String from;
    private String to;

    private int customerId;
    @OneToOne
    @JoinColumn
    Cab cab;

    public TripBooking() {
    }

    public TripBooking(int tripBookingId, TripStatus tripStatus, String from, String to, int customerId, Cab cab) {
        this.tripBookingId = tripBookingId;
        this.tripStatus = tripStatus;
        this.from = from;
        this.to = to;
        this.customerId = customerId;
        this.cab = cab;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }


    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}