package com.example.artidemo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Employee;
import com.example.artidemo.model.SmtpMailSender;
import com.example.artidemo.services.EmployeeService;

@RestController
public class RootController {

final static Logger logger = Logger.getLogger(RootController.class);

@Autowired
private SmtpMailSender smtpMailSender;

@Autowired
private EmployeeService employeeService;

@RequestMapping("/sendmail")
public void sendMail() throws MessagingException{
	logger.info("#################### Notification Started######################################");

	List<String> emails = employeeService.getAllEmails();

       String[] email = new String[emails.size()];
       for(int i=0;i<emails.size();i++)
       {
    	   email[i] = emails.get(i);
       }
       smtpMailSender.send(email, "Meeting App", "New Meeting is Added");
}

@RequestMapping("/getemail")
public ResponseEntity<List<String>> getMail() 
{
List<String> emails = employeeService.getAllEmails();
if (emails.isEmpty()) {
logger.debug("emails does not exists");
return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
}
logger.debug("Found " + emails.size() + " Employees");
logger.debug(emails);
logger.debug(Arrays.toString(emails.toArray()));
return new ResponseEntity<List<String>>(emails, HttpStatus.OK);

}





}