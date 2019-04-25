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

import com.example.artidemo.model.Attendance;
import com.example.artidemo.services.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
	final static Logger logger = Logger.getLogger(AttendanceController.class);

	@Autowired 
	AttendanceService attendanceService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) throws JSONException{
		attendanceService.save(attendance);
		logger.debug("Added:: " + attendance);
		return new ResponseEntity<Attendance>(attendance, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/getAttendanceByMeetingId/{meeting_id}", method = RequestMethod.GET)
	public ResponseEntity<List<Attendance>> getAttendanceByMeetingId(@PathVariable("meeting_id") Long id) {
		List<Attendance> attendance = attendanceService.getByMeetingId(id);
		if (attendance == null) {
			logger.debug("attendance with id " + id + " does not exists");
			return new ResponseEntity<List<Attendance>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Attendance:: " + attendance);
		return new ResponseEntity<List<Attendance>>(attendance, HttpStatus.OK);
	}
}
