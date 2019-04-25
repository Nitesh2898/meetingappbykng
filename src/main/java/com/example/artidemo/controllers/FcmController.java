package com.example.artidemo.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.services.FcmService;
import com.example.artidemo.controllers.FcmController;
import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.Fcm;

@RestController
public class FcmController {
	public final static Logger logger = Logger.getLogger(FcmController.class);
	@Autowired
	FcmService fcmservice;
	
	@RequestMapping(method=RequestMethod.POST,value="/storedevice/{id}")
	@ResponseBody
	public ResponseEntity<?> addcabdetail(@PathVariable long id,@RequestBody Fcm details) throws NullPointerException{
		logger.info("Adding new device with details:");
		logger.info("Adding new device with details sso:"+id);
		logger.info("Device ID.:"+details.getDeviceId()+"Platform : "+details.getPlatform());
		String str=null;
		try{
			str=fcmservice.addNewDevice(id,details);		
		}catch (HttpMessageNotReadableException e) 
		{
			logger.error("Could not add device details.Required request body is missing.");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not add cab details.Required request body is missing"),HttpStatus.BAD_REQUEST);
		}
		if(str==null)
		{
			logger.error("Could not add device details. Insufficient data sent.");
			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not add cab details. Insufficient data sent."),HttpStatus.BAD_REQUEST);
		}
		logger.info("Adding new device"+str);
		return new ResponseEntity<>(new JsonResponse().convertToJson(str),HttpStatus.OK);
	}
	
	
//	@RequestMapping(method=RequestMethod.POST,value="/storedriverdevice/{username}")
//	@ResponseBody
//	public ResponseEntity<?> addcabdetail(@PathVariable String username,@RequestBody Fcm details) throws NullPointerException{
//		logger.info("Adding new device with details:");
//		logger.info("Adding new device with details sso:"+username);
//		logger.info("Device ID.:"+details.getDeviceId()+"Platform : "+details.getPlatform());
//		String str=null;
//		try{
//			str=fcmservice.addNewDriverDevice(username,details);		
//		}catch (HttpMessageNotReadableException e) 
//		{
//			logger.error("Could not add device details.Required request body is missing.");
//			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not add cab details.Required request body is missing"),HttpStatus.BAD_REQUEST);
//		}
//		if(str==null)
//		{
//			logger.error("Could not add device details. Insufficient data sent.");
//			return new ResponseEntity<>(new JsonResponse().convertToJson("Could not add cab details. Insufficient data sent."),HttpStatus.BAD_REQUEST);
//		}
//		logger.info("Adding new device"+str);
//		return new ResponseEntity<>(new JsonResponse().convertToJson(str),HttpStatus.OK);
//	}
	
	
//	@RequestMapping(method= RequestMethod.GET,value="/getdevices/{cabScheduleId}")  
//	@ResponseBody
//	public ResponseEntity<?> getDevices(@PathVariable List<Long> cabScheduleId) {
//		logger.info("Fetching device ids for Cab Schedule ID:"+cabScheduleId);
//		List<String> deviceid = fcmservice.getDeviceForCabSchedule(cabScheduleId);
//		if(deviceid.isEmpty()){
//			logger.error("No results found for deviceid for given cab schedule in database");
//			return new ResponseEntity<>(new JsonResponse().convertToJson("cab schedule id:"+cabScheduleId+" not found."), HttpStatus.NOT_FOUND);
//		}
//		logger.info("Returned the devices.");
//		return new ResponseEntity<>(deviceid, HttpStatus.OK);
//	}
			
	
}
