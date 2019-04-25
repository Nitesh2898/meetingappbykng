package com.example.artidemo.repositories;



import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.artidemo.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, String>{

	public Notification findBodyByTitle(String title);

}
