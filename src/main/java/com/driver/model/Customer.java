package com.driver.model;
import javax.persistence.*;

@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;

    @ManyToOne
    @JoinColumn
    Admin admin;
    private int tripId;

    public Customer() {
    }

    public Customer(int customerId, String customerName, Admin admin, int tripId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.admin = admin;
        this.tripId = tripId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}