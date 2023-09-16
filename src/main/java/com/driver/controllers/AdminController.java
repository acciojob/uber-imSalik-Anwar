package com.driver.controllers;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.responseDTO.AdminResponse;
import com.driver.services.AdminService;
import com.driver.services.impl.AdminServiceImpl;
import com.driver.services.impl.CustomerServiceImpl;
import com.driver.services.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServiceImpl adminService;
	@Autowired
	DriverServiceImpl driverService;

	@PostMapping("/register") // marked
	public ResponseEntity<Void> registerAdmin(@RequestBody Admin admin){
		adminService.adminRegister(admin);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update") // marked
	public ResponseEntity<AdminResponse> updateAdminPassword(@RequestParam Integer adminId, @RequestParam String password){
		AdminResponse updatedAdmin = adminService.updatePassword(adminId, password);
		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	}

	@DeleteMapping("/delete") // marked
	public void deleteAdmin(@RequestParam Integer adminId){
		adminService.deleteAdmin(adminId);
	}

	@GetMapping("/listOfCustomers")
	public List<Customer> listOfCustomers() {
		return adminService.getListOfCustomers();
	}

	@GetMapping("/listOfDrivers")
	public List<Driver> listOfDrivers() {
		return adminService.getListOfDrivers();
	}
}
