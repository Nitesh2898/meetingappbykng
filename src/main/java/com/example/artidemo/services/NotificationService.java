package com.example.artidemo.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.Fcm;
import com.example.artidemo.model.Notification;
import com.example.artidemo.repositories.FcmRepository;
import com.example.artidemo.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationrepository;
	

	public final static Logger logger = Logger.getLogger(NotificationService.class);
	
	
	public String storeNewNotification(Notification details) {
		logger.info("In storeNewNotification function");	
		Notification notification;
		notification = notificationrepository.findBodyByTitle(details.getTitle());
		if(notification.toString()==null)
		{
			notificationrepository.save(details);
				return "Notification added successfully";
			}
			else{
				return "Could not add notification details. Notification already exists.";
			}
		}
	
	


	public String getNotificationforTitle(String title) {
		
		Notification  notifications;
		notifications = notificationrepository.findBodyByTitle(title);	
		return notifications.getBody();
	}

}