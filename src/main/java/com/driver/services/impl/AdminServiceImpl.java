package com.driver.services.impl;

import java.util.List;
import java.util.Optional;

import com.driver.responseDTO.AdminResponse;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		adminRepository.save(admin);
	}

	@Override
	public AdminResponse updatePassword(Integer adminId, String password) {
		//Update the password of admin with given id
		Optional<Admin> adminOptional = adminRepository.findById(adminId);
		if(!adminOptional.isPresent()){
			return null;
		}
		Admin admin = adminOptional.get();
		admin.setAdminPassword(password);
		adminRepository.save(admin);

		AdminResponse adminResponse = new AdminResponse();
		adminResponse.setAdminName(admin.getAdminName());
		adminResponse.setAdminId(admin.getAdminId());
		adminResponse.setAdminPassword(admin.getAdminPassword());
		return adminResponse;
	}

	@Override
	public void deleteAdmin(int adminId){
		// Delete admin without using deleteById function
		adminRepository.deleteById(adminId);
	}
	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
		List<Admin> list = adminRepository.findAll();
		Admin admin = list.get(0);
		return admin.getCustomers();
	}
	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers
		List<Admin> list = adminRepository.findAll();
		Admin admin = list.get(0);
		return admin.getDrivers();
	}


}
