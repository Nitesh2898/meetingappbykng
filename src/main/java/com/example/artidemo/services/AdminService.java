package com.example.artidemo.services;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Employee;
import com.example.artidemo.repositories.EmployeeRepository;
import com.example.artidemo.model.Admin;
import com.example.artidemo.repositories.AdminRepository;

@Service
public class AdminService {
	public final static Logger logger = Logger.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	public String save(Admin entity)
	{
		if(!adminRepository.exists(entity.getId()))
		{
			logger.info("In adduser function"+entity);
			adminRepository.save(entity);
			return "You have been registered successfully.";
		}
		else
		{
			logger.info("user already exist");
			return "This sso is already registered";
		}
	}
	
	public Admin getById(long id){
		return adminRepository.findOne(id);
	}
	
	public List<Admin> getAll(){
		return adminRepository.findAll();
	}
	
	public String delete(long id)
	{
		adminRepository.delete(id);
		return "{ \"response\": \"deleted Successfully\"}";
	}
	
	
	public String updateUserProfile(Admin input){
		Admin user=adminRepository.findOne(input.getId());
		if(user!=null){
			adminRepository.delete(user.getId());
			adminRepository.save(input);
			return "{ \"response\": \"profile updated successfully\" }";	
		}
		else
		return "{ \"response\": \"failed to update\" }";	
	}
}
