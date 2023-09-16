package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Cab;
import com.driver.repository.AdminRepository;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository;

	@Autowired
	CabRepository cabRepository;
	@Autowired
	AdminRepository adminRepository;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		// add a new cab in DB
		Cab cab = new Cab();
		cab.setAvailable(true);
		cab.setRatePerKm(10);
		Cab savedCab = cabRepository.save(cab);
		Driver driver = new Driver();
		driver.setMobile(mobile);
		driver.setPassword(password);
		driver.setCab(savedCab);
		savedCab.setDriver(driver);
		driverRepository.save(driver);
		// add driver to admin list
		List<Admin> list = adminRepository.findAll();
		Admin admin = list.get(0);
		admin.getDrivers().add(driver);
		adminRepository.save(admin);
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		if(!driverOptional.isPresent()){
			return;
		}
		Driver driver = driverOptional.get();
		List<Admin> adminList = adminRepository.findAll();
		Admin admin = adminList.get(0);
		admin.getDrivers().remove(driver);
		adminRepository.save(admin);
		driverRepository.delete(driver);
	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		Driver driver = driverOptional.get();
		driver.getCab().setAvailable(false);
	}

    public List<Driver> listOfDrivers() {
		List<Driver> list = driverRepository.findAll();
		return list;
    }
}
