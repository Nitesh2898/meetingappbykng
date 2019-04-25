package com.example.artidemo.services;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Employee;
import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.SmtpMailSender;
import com.example.artidemo.repositories.MeetingRepository;


@Service
public class MeetingService {
	
	public final static Logger logger = Logger.getLogger(MeetingService.class);
	
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private SmtpMailSender smtpMailSender;

	@Autowired
	private EmployeeService employeeService;
	
	public Meetings save(Meetings entity)
	{
		return meetingRepository.save(entity);
	}
	
	public Meetings getById(long id){
		return meetingRepository.findOne(id);
	}
	
	public List<Meetings> getAll(){
		return meetingRepository.findAll();
	}
	
	public void delete(long id)
	{
		meetingRepository.delete(id);
	}
	
	public List<Meetings> getTodaysMeeting(Timestamp today) {
		logger.info("In getTodaysMeetingForUser Method");
		List<Meetings> meetingForToday =  findByMeetingDateAfter(convertStringToTimestamp(getCurrentTimeUsingDate()));
		logger.info("getTodaysMeetingForUser Method"+meetingForToday);	
		return meetingForToday; 
	}
	
	public String getTimeForMeeting() {
		Calendar Istcal = Calendar.getInstance();
		TimeZone tz = Istcal.getTimeZone();
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		String timenow = now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE)+ ":"+"00";
		now.add(Calendar.MINUTE,10);//put if else here
		logger.info(timenow);
		String time="";
		if(now.get(Calendar.SECOND)<10){
			time = now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE)+ ":"+"00";
		}
		else  if(now.get(Calendar.HOUR_OF_DAY)<10){
			time = now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+"0"+ now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE)+ ":"+"00";
		} 
		else  if(now.get(Calendar.MINUTE)<10){
			time = now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+now.get(Calendar.HOUR_OF_DAY)+ ":"+ "0"+ now.get(Calendar.MINUTE)+ ":"+"00";
		}	 
		else
			time = now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE)+ ":"+"00";
		logger.info(time);
		return time;
	}
	

	
	public List<Long> getMeetingIdForCurrentTime(String currentTimeUsingDate) {
		Timestamp t = Timestamp.valueOf(currentTimeUsingDate);
		logger.info("=-----------------inside getCabScheduleIdForCurrentTime:-----");	
		logger.info("------------scheduled time:-----"+t);	
		List<Meetings> meetingschedule = meetingRepository.findByMeetingDate(t);		
		List<Long> meetingids = new ArrayList<>();
		for(Meetings meeting1 : meetingschedule)
			meetingids.add(meeting1.getId());
		logger.info("cabscheduleId:-----"+meetingids);	
		return meetingids;
	}
	
	public String getCurrentTimeUsingDate() {
		logger.info("In GetCurrentTimeUsing Date Method");
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		String formattedDate= dateFormat.format(date);  
		logger.info("date format:"+ dateFormat);
		System.out.println("formattedDate"+formattedDate);
		return formattedDate;
	}
	
	public String updateMeetings(Meetings input){
		Meetings user=meetingRepository.findOne(input.getId());
		if(user!=null){
			meetingRepository.delete(user.getId());
			meetingRepository.save(input);
			return "{ \"response\": \"Meeting updated successfully\" }";	
		}
		else
		return "{ \"response\": \"failed to update\" }";	
	}

	public  Timestamp convertStringToTimestamp(String str_date) {
		logger.info("In convertStringToTimestamp function");
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(str_date);
			logger.info("convertStringToTimestamp date: "+date);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			logger.info("convertStringToTimestamp timestampDate: "+timeStampDate);
			return timeStampDate;
		} catch (ParseException e) {
			logger.error("Could not convert string to timestamp. Error while parsing date");
		}
		return null;
	}	
	
	public Timestamp convertToTimestamp(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
		Timestamp timeStamp = new Timestamp(date.getTime());
		logger.info("timestamp from convertToTimestamp method"+timeStamp+" date"+date);
		return timeStamp;
	}
	
	public List<Meetings> findByMeetingDateAfter(Timestamp today) {
		logger.info("In findByMeetingDateAfter function");
		List <Meetings> meetingForToday = meetingRepository.findByMeetingDateAfter(today);
		logger.info("In getAllBoardingPoints function"+meetingForToday);
		return meetingForToday; 
	}

	public List<Meetings> findByMeetingDateGreaterThanAndMeetingDateLessThan(Timestamp end, Timestamp start) {
		logger.info("In findByMeetingDateGreaterThanAndBookingDateLessThan function");
		List<Meetings>  meetingForPastMonth = meetingRepository.findByMeetingDateGreaterThanAndMeetingDateLessThan(end, start);
		return meetingForPastMonth;
	}
	
	public Meetings findByMeetingDateLessThanAndMeetingDateGreaterThan(Timestamp end, Timestamp start) {
		logger.info("In findByMeetingDateGreaterThanAndBookingDateLessThan function");
		Meetings  meetingForPastMonth = meetingRepository.findByMeetingDateLessThanAndMeetingDateGreaterThan(end, start);
		return meetingForPastMonth;
	}

	public List<Meetings> findByMeetingDateGreaterThanCurrent(Timestamp start) {
		logger.info("In findByMeetingDateGreaterThanAndMeetingDateLessThan function");
		List<Meetings>  meetingForFuture = meetingRepository.findByMeetingDateAfter(start);
		return meetingForFuture;
	}

	public void mail(String title, String agenda,String time, String venue,String description) throws MessagingException {
		List<String> emails = employeeService.getAllEmails();
		

	       String[] email = new String[emails.size()];
	       for(int i=0;i<emails.size();i++)
	       {
	    	   email[i] = emails.get(i);
	       }
	       smtpMailSender.send(email, title, "Agenda Of Meeting : "+agenda+" || Date & Time : "+time+" || Venue : "+venue+" || Description : "+description);
		
	}
	
}
