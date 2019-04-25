package com.example.artidemo.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Approval;
import com.example.artidemo.services.ApprovalService;


@RestController
@RequestMapping("/approval")
public class ApprovalController {
	
	final static Logger logger = Logger.getLogger(ApprovalController.class);

	@Autowired 
	ApprovalService approvalService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Approval> addAttendance(@RequestBody Approval approval) throws JSONException{
		approvalService.save(approval);
		logger.debug("Added:: " + approval);
		return new ResponseEntity<Approval>(approval, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/getApprovalByMeetingId/{meeting_id}", method = RequestMethod.GET)
	public ResponseEntity<List<Approval>> getApprovalByMeetingId(@PathVariable("meeting_id") Long id) {
		List<Approval> approval = approvalService.getByMeetingId(id);
		if (approval == null) {
			logger.debug("approval with id " + id + " does not exists");
			return new ResponseEntity<List<Approval>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Approval:: " + approval);
		return new ResponseEntity<List<Approval>>(approval, HttpStatus.OK);
	}
	

}
