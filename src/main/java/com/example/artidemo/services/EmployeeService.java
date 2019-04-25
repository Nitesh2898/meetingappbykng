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
public class EmployeeService {

	public final static Logger logger = Logger.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	public String save(Employee entity)
	{
		if(!employeeRepository.exists(entity.getId()))
		{
			logger.info("In adduser function"+entity);
			employeeRepository.save(entity);
			return "You have been registered successfully.";
		}
		else
		{
			logger.info("user already exist");
			return "This user_id is already registered";
		}
	}
	
	public Employee getById(long id){
		return employeeRepository.findOne(id);
	}
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}
	
	public String delete(long id)
	{
		employeeRepository.delete(id);
		return "{ \"response\": \"deleted Successfully\"}";
	}
	
	public String login(Employee input) {
	
		Admin admin= adminRepository.findOne(input.getId());
		if(admin!=null)
			return adminLogin(admin,input);		
		else
		{
			Employee user = employeeRepository.findOne(input.getId());
			if(user!=null)
				return userLogin(user,input);		
		}
		return "{ \"response\": \"Please Register\" }";
	}
	
	private String adminLogin(Admin admin, Employee input) {
		if(admin.getpassword().equals(input.getpassword()))
		{
			return "{ \"entity\": \"admin\", \"response\": \"success\"}";
		}
		else
			return "{ \"entity\": \"admin\", \"response\": \"wrong password\" }";
	}
	
	public String updateUserProfile(Employee input){
		Employee user=employeeRepository.findOne(input.getId());
		if(user!=null){
			employeeRepository.delete(user.getId());
			employeeRepository.save(input);
			return "{ \"response\": \"profile updated successfully\" }";	
		}
		else
		return "{ \"response\": \"failed to update\" }";	
	}

	private String userLogin(Employee user, Employee input) {
		if(user.getpassword().equals(input.getpassword()))
		{
				return "{ \"entity\": \"user\", \"response\": \"success\"}";
		}
		else
			return "{ \"entity\": \"user\", \"response\": \"wrong password\" }";
	}
	
	
	public List<String> getAllEmails() {

		return employeeRepository.findAllEmails();
		}
	
	
	
	
	
	
	
}
