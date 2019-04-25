package com.example.artidemo.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Employee;
import com.example.artidemo.services.EmployeeService;
import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.Admin;
import com.example.artidemo.repositories.AdminRepository;
import com.example.artidemo.services.AdminService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;
	@Autowired
	AdminService adminService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		String str = empService.save(employee);
		logger.debug("Added:: " + employee);
		return new ResponseEntity<>(new JsonResponse().convertToJson(str), HttpStatus.OK);
	}


	@RequestMapping(path="/updateEmployeeById/{id}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		Employee existingEmp = empService.getById(employee.getId());
		if (existingEmp == null) {
			logger.debug("Employee with id " + employee.getId() + " does not exists");
			String str=  "{ \"response\": \"does not exist\" }";	
			return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
		} else {
			String str = empService.updateUserProfile(employee);
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
	}
	
	@RequestMapping(path="/getEmployeeById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") Long id) {
		Admin admin = adminService.getById(id);
		if(admin == null)
		{
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			}
		return new ResponseEntity<>(employee, HttpStatus.OK);
		}
		else
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}


	@RequestMapping(path="/getAllEmployees",method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = empService.getAll();
		if (employees.isEmpty()) {
			logger.debug("Employees does not exists");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + employees.size() + " Employees");
		logger.debug(employees);
		logger.debug(Arrays.toString(employees.toArray()));
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}


	@RequestMapping(path = "/deleteEmployeeById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			String str = empService.delete(id);
			logger.debug("Employee with id " + id + " deleted");
			return new ResponseEntity<>(str,HttpStatus.GONE);
		}
	}
	
	@RequestMapping(method= RequestMethod.POST,value="/login")
	public ResponseEntity<String> Login(@RequestBody Employee input){
		String str="";
		try{
			str=empService.login(input);
		}catch (HttpMessageNotReadableException e) 
		{
			logger.error("Could not authenticate. Required request body is missing");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not authenticate. Required request body is missing"),HttpStatus.BAD_REQUEST);
		}catch (java.lang.NullPointerException e) {
			logger.error("Could not authenticate. Required request body is incomplete");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not authenticate. Required request body is incomplete"),HttpStatus.BAD_REQUEST);
		}catch (DataAccessException e) {
			logger.error("Username does not exist.");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not authenticate."),HttpStatus.BAD_REQUEST);
		}
		if(str==null)
		{
			logger.error("Could not authenticate. Insufficient data sent.");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not authenticate Admin. Insufficient data sent."),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(str,HttpStatus.OK);			
	}
}
