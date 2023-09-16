package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String adminName;
    private String adminPassword;

    // one admin can have many customer
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();
    // one admin can also have many drivers
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Driver> drivers = new ArrayList<>();

    public Admin() {
    }

    public Admin(int adminId, String adminName, String adminPassword) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}