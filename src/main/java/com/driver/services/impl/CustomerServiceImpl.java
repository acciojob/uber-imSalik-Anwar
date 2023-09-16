package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.AdminRepository;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository;
	@Autowired
	AdminRepository adminRepository;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository.save(customer);
		List<Admin> list = adminRepository.findAll();
		Admin admin = list.get(0);
		admin.getCustomers().add(customer);
		adminRepository.save(admin);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if(!customerOptional.isPresent()){
			return;
		}
		Customer customer = customerOptional.get();
		tripBookingRepository.deleteById(customer.getTripId());
		List<Admin> list = adminRepository.findAll();
		Admin admin = list.get(0);
		admin.getCustomers().remove(customer);
		adminRepository.save(admin);
		customerRepository.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		// 1. get the driver with lowest driverId with an available car;
		List<Driver> driverList = driverRepository2.findAllOrderByDriverId();
		Driver freeDriver = null;
		for(Driver driver : driverList){
			if(driver.getCab().getAvailable()){
				driver.getCab().setAvailable(false);
				freeDriver = driver;
				break;
			}
		}
		if(freeDriver == null){
			throw new Exception("No cab available!");
		}
		// 2. get the customer
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if(!customerOptional.isPresent()){
			throw  new Exception("Customer not found!");
		}
		Customer customer = customerOptional.get();
		// 3. do transaction
		TripBooking newTrip = new TripBooking();
		TripBooking savedTrip = tripBookingRepository.save(newTrip);
		savedTrip.setFrom(fromLocation);
		savedTrip.setTo(toLocation);
		savedTrip.setTripStatus(TripStatus.CONFIRMED);
		savedTrip.setCustomerId(customerId);

		customer.setTripId(savedTrip.getTripBookingId());
		customerRepository.save(customer);
		return savedTrip;
	}
	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripOptional = tripBookingRepository.findById(tripId);
		if(!tripOptional.isPresent()){
			return;
		}
		TripBooking trip = tripOptional.get();
		// free the car
		trip.getCab().setAvailable(true);
		// update trip status
		trip.setTripStatus(TripStatus.COMPLETED);
		// remove trip from customer's list
//		trip.getCustomer().getTrips().remove(trip);
	}
	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripOptional = tripBookingRepository.findById(tripId);
		if(!tripOptional.isPresent()){
			return;
		}
		TripBooking trip = tripOptional.get();
		// free the car
		trip.getCab().setAvailable(true);
		// update trip status
		trip.setTripStatus(TripStatus.CANCELED);
		// remove trip from customer's list
//		trip.getCustomer().getTrips().remove(trip);
	}
	public List<Customer> listOfCustomers(){
		List<Customer> list = customerRepository.findAll();
		return  list;
	}
}
