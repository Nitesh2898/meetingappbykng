package com.example.artidemo.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Departments;
import com.example.artidemo.services.DeaprtmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	DeaprtmentService departmentService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Departments> addDepartment(@RequestBody Departments department) {
		departmentService.save(department);
		logger.debug("Added:: " + department);
		return new ResponseEntity<Departments>(department, HttpStatus.CREATED);
	}


/*	@RequestMapping(path="/updatedepartment",method = RequestMethod.PUT)
	public ResponseEntity<String> updateDepartment(@RequestBody Departments department) {
		Departments existingdepartment = departmentService.getById(department.getId());
		if (existingdepartment == null) {
			String str= "{ \"response\": \"Id Does Not Exists!!!\"}";
			logger.debug("Department with id " + department.getId() + " does not exists");
			return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
		} else {
			String str = departmentService.updateDepartment(department);
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
	}*/
	
	@RequestMapping(path="/getdepartmentById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id) {
		Departments department = departmentService.getById(id);
		if (department == null) {
			String str= "{ \"response\": \"Id Does Not Exists!!!\"}";
			logger.debug("Department with id " + id + " does not exists");
			return new ResponseEntity<>(str,HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Department:: " + department);
		return new ResponseEntity<Departments>(department, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getdepartmentByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getDepartmentByName(@PathVariable("name") String name) {
		Departments department = departmentService.getByName(name);
		if (department == null) {
			String str= "{ \"response\": \"Name Does Not Exists!!!\"}";
			logger.debug("Department with id " + name + " does not exists");
			return new ResponseEntity<>(str,HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Department:: " + department);
		return new ResponseEntity<Departments>((Departments) department, HttpStatus.OK);
	}


	@RequestMapping(path="/getAlldepartment",method = RequestMethod.GET)
	public ResponseEntity<?> getAllDepartment() {
		List<Departments> department = departmentService.getAll();
		if (department.isEmpty()) {
			String str= "{ \"response\": \"Does Not Exists!!!\"}";
			logger.debug("Department does not exists");
			return new ResponseEntity<>(str,HttpStatus.NOT_FOUND);
		}
		logger.debug("Found " + department.size() + " Departments");
		logger.debug(department);
		logger.debug(Arrays.toString(department.toArray()));
		return new ResponseEntity<>(department, HttpStatus.OK);
	}


	/*@RequestMapping(path = "/deletedepartmentById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) {
		Departments department = departmentService.getById(id);
		if (department == null) {
			String str= "{ \"response\": \"Id Does Not Exists!!!\"}";
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
		} else 
		{
			String str = departmentService.delete(id);
			logger.debug("Employee with id " + id + " deleted");
			return new ResponseEntity<String>(str,HttpStatus.GONE);
		}
	}
	*/
}
