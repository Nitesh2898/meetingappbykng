package com.example.artidemo.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.SmtpMailSender;
import com.example.artidemo.services.MeetingService;
import com.example.artidemo.services.EmployeeService;
import com.example.artidemo.services.MeetingDetailsService;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {
	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	MeetingService meetingService;
	
	@Autowired
	MeetingDetailsService meetingDetailsService;
	EmployeeService employeeService;
	SmtpMailSender smtpMailSender;
	RootController rootController;

	@Autowired 
	DeviceInformationController DeviceInformationController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Meetings> addMeeting(@RequestBody Meetings meeting) throws JSONException,MessagingException{
		meetingService.save(meeting);
		String title = meeting.getmeetingtitle();
		String agenda = meeting.getagenda();
		String time = meeting.getmeetingDate();
		String venue = meeting.getVenue();
		String description = meeting.getdescription();
		logger.debug("Added:: " + meeting);
		DeviceInformationController.sendNotification();
		meetingService.mail(title,agenda,time,venue,description);
		//rootController.sendMail();
		
//		List<String> emails = employeeService.getAllEmails();
//
//	       String[] email = new String[emails.size()];
//	       for(int i=0;i<emails.size();i++)
//	       {
//	    	   email[i] = emails.get(i);
//	       }
//	       smtpMailSender.send(email, "Meeting App", " New Meeting is Added");
	       return new ResponseEntity<Meetings>(meeting, HttpStatus.CREATED);
		
	}

	@RequestMapping(path="/updateMeeting",method = RequestMethod.PUT)
	public ResponseEntity<String> updateMeeting(@RequestBody Meetings meeting) {
		String str = meetingService.updateMeetings(meeting);
		if (str == null) {
			logger.debug("Meeting with id " + meeting.getId() + " does not exists");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
	}
	
	@RequestMapping(path="/getMeetingById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Meetings> getMeeting(@PathVariable("id") Long id) {
		Meetings meeting = meetingService.getById(id);
		if (meeting == null) {
			logger.debug("Meeting with id " + id + " does not exists");
			return new ResponseEntity<Meetings>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Meeting:: " + meeting);
		return new ResponseEntity<Meetings>(meeting, HttpStatus.OK);
	}


	@RequestMapping(path="/getAllMeeting",method = RequestMethod.GET)
	public ResponseEntity<List<Meetings>> getAllMeeting() {
		List<Meetings> meeting = meetingService.getAll();
		if (meeting.isEmpty()) {
			logger.debug("Meeting does not exists");
			return new ResponseEntity<List<Meetings>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + meeting.size() + " Meeting");
		logger.debug(meeting);
		logger.debug(Arrays.toString(meeting.toArray()));
		return new ResponseEntity<List<Meetings>>(meeting, HttpStatus.OK);
	}


	@RequestMapping(path = "/deleteMeetingById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMeeting(@PathVariable("id") Long id) {
		Meetings meeting = meetingService.getById(id);
		if (meeting == null) {
			logger.debug("Meeting with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			meetingService.delete(id);
			logger.debug("Meeting with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/gettimestamp")
	public Meetings gettime() {
	//	Logger.debug("In gettimestamp in method");
		Calendar cal1=Calendar.getInstance();
		Timestamp timestamp1=new Timestamp(cal1.getTime().getTime());
	       Timestamp timestamp2=new Timestamp(new Date(0).getTime());
	       logger.info("In findByMeetingForToday method" + timestamp1);
	        Calendar cal=Calendar.getInstance();
	        cal.add(Calendar.MINUTE,10);
	        timestamp2 =new Timestamp(cal.getTime().getTime());
	        logger.info("In findByMeetingForToday method" + timestamp2);
	        Meetings meetingFortoday = meetingService.findByMeetingDateLessThanAndMeetingDateGreaterThan(timestamp2,timestamp1);
	        logger.info("In findByMeetingForToday method" + meetingFortoday);
		return meetingFortoday;
	}
	
//	@RequestMapping(method = RequestMethod.GET,path = "/getLatertimestamp")
//	public Meetings getLatertime() {
//	//	Logger.debug("In gettimestamp in method");
//		Calendar cal1=Calendar.getInstance();
//		Timestamp timestamp1=new Timestamp(cal1.getTime().getTime());
//	       Timestamp timestamp2=new Timestamp(new Date(0).getTime());
//	       logger.info("In findByMeetingForToday method" + timestamp1);
//	        Calendar cal=Calendar.getInstance();
//	        cal.add(Calendar.MINUTE,6);
//	        timestamp2 =new Timestamp(cal.getTime().getTime());
//	        logger.info("In findByMeetingForToday method" + timestamp2);
//	        Meetings meetingFortoday = meetingService.findByMeetingDateEndGreaterThanAndMeetingDateEndLessThan(timestamp1,timestamp2);
//	        logger.info("In findByMeetingForToday method" + meetingFortoday);
//		return meetingFortoday;
//	}
	
	@RequestMapping(method= RequestMethod.GET,value="/getMeetingforToday")
	public ResponseEntity<?> findByMeetingForToday(){
		logger.info("In findByMeetingForToday method");
		Timestamp start = meetingService.convertToTimestamp(LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).now().plusDays(1));
		Timestamp end = meetingService.convertToTimestamp (LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).now());		
		logger.info("Fetching all Meeting details of the last one month");
		List<Meetings> meetingFortoday = meetingService.findByMeetingDateGreaterThanAndMeetingDateLessThan(end,start);
		logger.info("All Meeting details of the last one month: "+meetingFortoday+"Timestamp end:"+end+"Timestamp start:"+start);
//		for(Meetings m1 : meetingFortoday)
//		{
////			finalList = meetingService.getById(m1.getId());
//			Long id = m1.getId();
//			MeetingDetails meetingDetails = meetingDetailsService.getById(id);
//			meetingFortoday.add(meetingDetails);
//		}
		return new ResponseEntity<>(meetingFortoday, HttpStatus.OK);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(method= RequestMethod.GET,value="/getMeetingforFuture")
	public ResponseEntity<?> findByMeetingDate(){
		logger.info("Fetching all meeting details of today");
		Timestamp start = meetingService.convertToTimestamp(LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).now().plusDays(1));
		List<Meetings> meetingForFuture = meetingService.findByMeetingDateGreaterThanCurrent(start);
		logger.info("All meeting details of today: "+meetingForFuture);
		return new ResponseEntity<>(meetingForFuture, HttpStatus.OK);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(method= RequestMethod.GET,value="/getMeetingforOneMonth")
	public ResponseEntity<?> findByMeetingDateGreaterThanAndMeetingDateLessThan(){
		logger.info("In findByMeetingDateGreaterThanAndMeetingDateLessThan method");
		Timestamp start = meetingService.convertToTimestamp(LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).now());
		Timestamp end = meetingService.convertToTimestamp (LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).now().minusMonths(1));		
		logger.info("Fetching all Meeting details of the last one month");
		List<Meetings> meetingForLastMonth = meetingService.findByMeetingDateGreaterThanAndMeetingDateLessThan(end,start);
		logger.info("All Meeting details of the last one month: "+meetingForLastMonth+"Timestamp end:"+end+"Timestamp start:"+start);
		return new ResponseEntity<>(meetingForLastMonth, HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.GET,value="/getMeetingId")  
	@ResponseBody
	public List<Long> getMeetingId() {
		List<Long> meetingIdList =  new ArrayList<>();
		String time = meetingService.getTimeForMeeting();//add argument here
			try {
				meetingIdList=meetingService.getMeetingIdForCurrentTime(time+".0");
			} catch (NullPointerException e) {
				logger.error("getMeetingIdForCurrentTime not found."+time);
			}
		
		logger.info("List of Meeting");
		return meetingIdList;
	}	
	
	
	@RequestMapping(method= RequestMethod.GET,value="/getNotification")
	public void getNotification() throws JSONException{
		DeviceInformationController.sendNotification();
	}
	
	@RequestMapping(method= RequestMethod.GET,value="/getremainder")
	public void getNotification1() throws JSONException{
		DeviceInformationController.sendNotificationOnMeeting();
	}
}
