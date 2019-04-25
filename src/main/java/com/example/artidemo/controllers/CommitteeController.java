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

import com.example.artidemo.model.Committee;
import com.example.artidemo.services.CommitteeService;


@RestController
@RequestMapping("/committee")
public class CommitteeController {
	
	final static Logger logger = Logger.getLogger(DirectoryController.class);

	@Autowired
	CommitteeService committeeService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Committee> addDirectory(@RequestBody Committee committee) {
		committeeService.save(committee);
		logger.debug("Added:: " + committee);
		return new ResponseEntity<Committee>(committee, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/getAllCommittees",method = RequestMethod.GET)
	public ResponseEntity<List<Committee>> getAllCommittees() {
		List<Committee> committees = committeeService.getAll();
		if (committees.isEmpty()) {
			logger.debug("Committees does not exists");
			return new ResponseEntity<List<Committee>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + committees.size() + " Committees");
		logger.debug(Arrays.toString(committees.toArray()));
		return new ResponseEntity<List<Committee>>(committees, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getCommitteeByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommitteeByName(@PathVariable("committeetile") String name) {
		Committee committeetile = committeeService.getByCommitteetitle(name);
		if (committeetile == null) {
			String str= "{ \"response\": \"Name Does Not Exists!!!\"}";
			logger.debug("Committee with name " + name + " does not exists");
			return new ResponseEntity<>(str,HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Committee:: " + committeetile);
		return new ResponseEntity<Committee>((Committee) committeetile, HttpStatus.OK);
	}
	
	
	
	
	

}
