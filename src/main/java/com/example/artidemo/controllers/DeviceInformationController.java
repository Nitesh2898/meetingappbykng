package com.example.artidemo.controllers;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.artidemo.Constants;
import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.DeviceInformation;
import com.example.artidemo.model.Employee;
import com.example.artidemo.model.Fcm;
import com.example.artidemo.model.Meetings;
import com.example.artidemo.services.FcmService;
import com.example.artidemo.services.NotificationService;
import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.MeetingDetails;

@RestController

public class DeviceInformationController {
	@Autowired
	FcmService fcmservice;
	@Autowired
	NotificationService notificationservice;
	@Autowired
	private MeetingsController meetingController;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public final static Logger logger = Logger.getLogger(DeviceInformationController.class);
	@Autowired
	private RestTemplate restTemplate;

//	@Scheduled(cron = "0 0/5 * * * *", zone = "IST")
	@RequestMapping(value = "sendpushnotificationtodevice/send/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> sendNotification() throws JSONException {
		logger.info("####################Android Push Notification Started######################################");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		List<Long> meetingid = meetingController.getMeetingId();
		logger.info(meetingid);
		deviceIdList = fcmservice.fetchAllDevices();
		logger.info(deviceIdList);
		JSONArray jsArray = new JSONArray(deviceIdList);
		
		body.put("registration_ids", jsArray);

		JSONObject notification = new JSONObject();
		notification.put("title", Constants.notifytitle);
		notification.put("body",Constants.notifymessage);

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			logger.info("####################Android Push Notification Completed######################################");
		return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
					HttpStatus.OK);

		}
		logger.info("####################Android Push Notification Completed######################################");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	@RequestMapping(value = "sendpushnotificationchat/send/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> sendchatNotification(MeetingDetails meeting) throws JSONException {
		logger.info("####################Android Push Notification Started######################################");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		List<Long> meetingid = meetingController.getMeetingId();
		logger.info(meetingid);
		deviceIdList = fcmservice.fetchAllDevices();
		logger.info(deviceIdList);
		JSONArray jsArray = new JSONArray(deviceIdList);
		
		body.put("registration_ids", jsArray);

		JSONObject notification = new JSONObject();
		notification.put("title", meeting.getchatuserid());
		notification.put("body",meeting.getchat());

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			logger.info("####################Android Push Notification Completed######################################");
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
				HttpStatus.OK);

		}
		logger.info("####################Android Push Notification Completed######################################");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@Scheduled(cron = "0 0/5 * * * *", zone = "IST")
	@RequestMapping(value = "sendpushnotificationtodeviceonaction/send/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> sendNotificationOnMeeting() throws JSONException {
		logger.info("####################Android Push Notification Started######################################");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		Meetings meetingid = meetingController.gettime();
		
		deviceIdList = fcmservice.fetchAllDevices();
		logger.info(deviceIdList);
		JSONArray jsArray = new JSONArray(deviceIdList);
		
		body.put("registration_ids", jsArray);
        String str = meetingid.getagenda();
        String str2 = meetingid.getmeetingtitle();
		JSONObject notification = new JSONObject();
		notification.put("title", str);
		notification.put("body",str2);

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			logger.info("####################Android Push Notification Completed######################################");
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
					HttpStatus.OK);

		}
		logger.info("####################Android Push Notification Completed######################################");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	//@Scheduled(cron = "0 0/1 * * * *", zone = "IST")
	@RequestMapping(value = "sendpushnotificationtodeviceonmeetingend/send/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> sendNotificationOnMeetingEnd() throws JSONException {
		logger.info("####################Android Push Notification Started######################################");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		//Meetings meetingid = meetingController.getLatertime();
		
		deviceIdList = fcmservice.fetchAllDevices();
		logger.info(deviceIdList);
		JSONArray jsArray = new JSONArray(deviceIdList);
		
		body.put("registration_ids", jsArray);
       // String str = meetingid.getagenda();
      //  String str2 = meetingid.getmeetingtitle();
		JSONObject notification = new JSONObject();
		//notification.put("title", str);
		notification.put("body","add minutes of meeting");

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			logger.info("####################Android Push Notification Completed######################################");
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
				HttpStatus.OK);

		}
		logger.info("####################Android Push Notification Completed######################################");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
/*
	public ResponseEntity<String> sendNotificationOnAction(List<String> deviceId, String action) throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);
		logger.info(deviceId);

		JSONObject body = new JSONObject();

		JSONArray jsonArr = new JSONArray(deviceId);
		//body.put("collapse_key", "Updates Available");
		body.put("registration_ids", jsonArr);

		JSONObject notification = new JSONObject();
		notification.put("title", Constants.notifytitle);
		String notificationmessage = notificationservice.getNotificationforTitle(action);
		logger.info(notificationmessage);
		notification.put("body", notificationmessage);

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	//@Scheduled(cron = "0 0/5 7-20 ? * MON-FRI", zone = "IST")
	@RequestMapping(value = "sendpushnotificationtodriverdevice/send/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> sendNotificationToDriver() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		List<Long> scheduleid = canschedulecontroller.getCabscheduleId();
		deviceIdList = fcmservice.getDriverDeviceForCabSchedule(scheduleid);
		logger.info(deviceIdList);
		
		
		JSONArray jsArray = new JSONArray(deviceIdList);
		// body.put("collapse_key", "Updates Available");
		body.put("registration_ids", jsArray);

		JSONObject notification = new JSONObject();
		notification.put("title", "MTC CAB BHGE");
		notification.put("body","Passeneger List");

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	public ResponseEntity<String> sendNotificationToDriverOnAction(long cabScheduleId) throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",Constants.AuthKey);

		JSONObject body = new JSONObject();
		List<String> deviceIdList;
		List<Long> scheduleid = new ArrayList<>();
		scheduleid.add(cabScheduleId);
		deviceIdList = fcmservice.getDriverDeviceForCabSchedule(scheduleid);
		logger.info(deviceIdList);
		JSONArray jsArray = new JSONArray(deviceIdList);
		// body.put("collapse_key", "Updates Available");
		body.put("registration_ids", jsArray);

		JSONObject notification = new JSONObject();
		notification.put("title", "MTC CAB BHGE");
		notification.put("body","Passeneger List");

		JSONObject data = new JSONObject();
		data.put("refresh", true);

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

		String response;
		try {
			response = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, String.class);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<>(new JsonResponse().convertToJson("No subscribers for the schedule"),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
*/

}
