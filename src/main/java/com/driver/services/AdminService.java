package com.driver.services;

import java.util.List;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.responseDTO.AdminResponse;

public interface AdminService {

	public void adminRegister(Admin admin);

	public AdminResponse updatePassword(Integer adminId, String password);

	public void deleteAdmin(int adminId);

	public List<Driver> getListOfDrivers();
	
	public List<Customer> getListOfCustomers();
}
