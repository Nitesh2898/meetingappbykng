package com.example.artidemo.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.repositories.MeetingRepository;
import com.example.artidemo.repositories.EmployeeRepository;
import com.example.artidemo.repositories.FcmRepository;
import com.example.artidemo.services.FcmService;
import com.example.artidemo.services.MeetingService;

import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.Fcm;
import com.example.artidemo.model.DeviceInformation;
import com.example.artidemo.model.Employee;

@Service
public class FcmService {

	@Autowired
	FcmRepository fcmrepository;
	@Autowired
	MeetingService bookingservice;
	@Autowired
	EmployeeRepository driverrepository;
	@Autowired
	MeetingRepository cabschedulerepository;
	public final static Logger logger = Logger.getLogger(FcmService.class);
	
	
	public String addNewDevice(long sso,Fcm details) {
		logger.info("In adddevicedetail function");	
		List<Fcm> device=new ArrayList<>();
		device = fcmrepository.findById(sso);
		if(device.isEmpty())
		{		
				details.setSso(sso);
				fcmrepository.save(details);
				
				logger.info(details);	
				return "Device added successfully";
			}
			else if(!(device.get(0).getDeviceId().equals(details.getDeviceId()))){
				fcmrepository.delete(device.get(0).getId());
				details.setSso(sso);
				fcmrepository.save(details);
				return "Device added successfully";
			}else
				return "Could not add device details.Device already exists.";
		}

	public List<String> fetchAllDevices(){
		List<Fcm> device = new ArrayList<>();
		fcmrepository.findAll().forEach(device::add);
		 List<String>deviceList = new ArrayList<>();
	for (Fcm fcm : device) {
		deviceList.add(fcm.getDeviceId());	
	}
	return deviceList;
	}
	
	
	

//	public String addNewDriverDevice(String username, Fcm details) {
//		logger.info("In adddevicedetail function");	
//		List<Fcm> device=new ArrayList<>();
//		long sso;
//		sso = driverrepository.findByUsername(username).getDriverId();
//		device = fcmrepository.findBySso(sso);
//		if(device.isEmpty())
//		{		
//				details.setSso(sso);
//				fcmrepository.save(details);
//				
//				logger.info(details);	
//				return "Device added successfully";
//			}
//			else if(!(device.get(0).getDeviceId().equals(details.getDeviceId()))){
//				fcmrepository.delete(device.get(0).getId());
//				details.setSso(sso);
//				fcmrepository.save(details);
//				return "Device added successfully";
//			}else
//				return "Could not add device details.Device already exists.";
//	}
//
//
//	public List<String> getDriverDeviceForCabSchedule(List<Long> scheduleid) {
//		List<CabSchedule> cabschedule = cabschedulerepository.findByCabScheduleIdIn(scheduleid);
//		List<Fcm> devicelist =new ArrayList<>();
//		List<String> deviceIdlist =new ArrayList<>();
//			
//		for(CabSchedule b : cabschedule){
//			logger.info(driverrepository.findByUsernameOrUsername(b.getdestination(), b.getsource()).getDriverId());
//			logger.info(driverrepository.findByUsernameOrUsername(b.getdestination(), b.getsource()).getDriverId());
//			devicelist.addAll(fcmrepository.findBySso(driverrepository.findByUsernameOrUsername(b.getdestination(), b.getsource()).getDriverId()));
//		}
//		for (Fcm fcm : devicelist) {
//			deviceIdlist.add(fcm.getDeviceId());	
//		}
//		return deviceIdlist;
//		
//	}

}
