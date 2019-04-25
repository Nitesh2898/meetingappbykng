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

import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.Admin;
import com.example.artidemo.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	final static Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
		String str = adminService.save(admin);
		logger.debug("Added:: " + admin);
		return new ResponseEntity<>(new JsonResponse().convertToJson(str), HttpStatus.OK);
	}

}
