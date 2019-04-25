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

import com.example.artidemo.model.CommitteeDetails;
import com.example.artidemo.services.CommitteeDetailsService;


@RestController
@RequestMapping("/committeedetails")
public class CommitteeDetailsController {
	
	final static Logger logger = Logger.getLogger(DirectoryController.class);

	@Autowired
	CommitteeDetailsService committeeDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CommitteeDetails> addDirectory(@RequestBody CommitteeDetails committeedetails) {
		committeeDetailsService.save(committeedetails);
		logger.debug("Added:: " + committeedetails);
		return new ResponseEntity<CommitteeDetails>(committeedetails, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/getAllCommitteeDetails",method = RequestMethod.GET)
	public ResponseEntity<List<CommitteeDetails>> getAllCommitteeDetails() {
		List<CommitteeDetails> committeedetails = committeeDetailsService.getAll();
		if (committeedetails.isEmpty()) {
			logger.debug("Committee Details does not exists");
			return new ResponseEntity<List<CommitteeDetails>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + committeedetails.size() + " CommitteeDetails");
		logger.debug(Arrays.toString(committeedetails.toArray()));
		return new ResponseEntity<List<CommitteeDetails>>(committeedetails, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getCommitteeDetailsByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommitteeDetailsByName(@PathVariable("committeetile") String name) {
		CommitteeDetails committeetile = committeeDetailsService.getByCommitteetitle(name);
		if (committeetile == null) {
			String str= "{ \"response\": \"Name Does Not Exists!!!\"}";
			logger.debug("Committee with name " + name + " does not exists");
			return new ResponseEntity<>(str,HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Committee Details:: " + committeetile);
		return new ResponseEntity<CommitteeDetails>((CommitteeDetails) committeetile, HttpStatus.OK);
	}
	

}
