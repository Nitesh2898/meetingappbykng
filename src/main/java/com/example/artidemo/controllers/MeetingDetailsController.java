package com.example.artidemo.controllers;
import java.util.Arrays;
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


import com.example.artidemo.model.Admin;
import com.example.artidemo.model.Employee;
import com.example.artidemo.model.MeetingDetails;
import com.example.artidemo.model.Meetings;
import com.example.artidemo.repositories.AdminRepository;
import com.example.artidemo.services.EmployeeService;
import com.example.artidemo.services.MeetingDetailsService;
import com.example.artidemo.services.MeetingService;

@RestController
@RequestMapping("/meetingChat")
public class MeetingDetailsController {
	final static Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	MeetingDetailsService meetingDetailsService;
	@Autowired 
	DeviceInformationController DeviceInformationController;
	@Autowired
	MeetingService meetingService;
	@Autowired
	EmployeeService empService;
	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addMeetingDetails(@RequestBody MeetingDetails meeting) throws JSONException {
		Long id = meeting.getmeetingid();
		Meetings meeting1 = meetingService.getById(id);
		if(meeting1 != null)
		{
			Long id1 = meeting.getchatuserid();
			Admin admin= adminRepository.findOne(id1);
			if(admin!=null)
			{
				meetingDetailsService.save(meeting);
				DeviceInformationController.sendchatNotification(meeting);
				String str = "{ \"response\": \"added Successfully\"}";
				return new ResponseEntity<String>(str, HttpStatus.CREATED);
			}
			else
			{
			Employee existingEmp = empService.getById(id1);
			if(existingEmp != null)
			{
				meetingDetailsService.save(meeting);
				DeviceInformationController.sendchatNotification(meeting);
				String str = "{ \"response\": \"added Successfully\"}";
			  return new ResponseEntity<String>(str, HttpStatus.CREATED);
			}
			else
			{
				String str = "{ \"response\": \"user not found\"}";
				return new ResponseEntity<String>(str, HttpStatus.NOT_FOUND);
			}
			}
		}
		else
		{
			String str = "{ \"response\": \"meeting not found\"}";
			return new ResponseEntity<String>(str, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/getChatById/{id}", method = RequestMethod.GET)
	public ResponseEntity<MeetingDetails> getMeetingChat(@PathVariable("id") Long id) {
		MeetingDetails meeting = meetingDetailsService.getById(id);
		if (meeting == null) {
			logger.debug("Meeting with id " + id + " does not exists");
			return new ResponseEntity<MeetingDetails>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Meeting:: " + meeting);
		return new ResponseEntity<MeetingDetails>(meeting, HttpStatus.OK);
	}
	
	/*@RequestMapping(path="/getAllChatDetails/{chatmeetingid}",method = RequestMethod.GET)
	public ResponseEntity<List<MeetingDetails>> getAllChatDetails(@PathVariable("id") Iterable<ID> id) {
		List<MeetingDetails> meeting = meetingDetailsService.getAllById(id);
		if (meeting.isEmpty()) {
			logger.debug("Meeting does not exists");
			return new ResponseEntity<List<MeetingDetails>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + meeting.size() + " Meeting");
		logger.debug(meeting);
		logger.debug(Arrays.toString(meeting.toArray()));
		return new ResponseEntity<List<MeetingDetails>>(meeting, HttpStatus.OK);
	}*/
	
	
	@RequestMapping(path="/getAllMeetingDetails",method = RequestMethod.GET)
	public ResponseEntity<List<MeetingDetails>> getAllMeetingDetails() {
		List<MeetingDetails> meeting = meetingDetailsService.getAll();
		if (meeting.isEmpty()) {
			logger.debug("Meeting does not exists");
			return new ResponseEntity<List<MeetingDetails>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + meeting.size() + " Meeting");
		logger.debug(meeting);
		logger.debug(Arrays.toString(meeting.toArray()));
		return new ResponseEntity<List<MeetingDetails>>(meeting, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getAllMeetingDetails/{meetingid}",method = RequestMethod.GET)
	public ResponseEntity<List<MeetingDetails>> getAllMeetingChat(@PathVariable("meetingid") Long meetingid) {
		List<MeetingDetails> meeting = meetingDetailsService.getAllChat(meetingid);
		if (meeting.isEmpty()) {
			logger.debug("Meeting does not exists");
			return new ResponseEntity<List<MeetingDetails>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + meeting.size() + " Meeting");
		logger.debug(meeting);
		logger.debug(Arrays.toString(meeting.toArray()));
		return new ResponseEntity<List<MeetingDetails>>(meeting, HttpStatus.OK);
	}
	
	
}
