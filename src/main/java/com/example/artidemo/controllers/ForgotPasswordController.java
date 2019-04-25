package com.example.artidemo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.ForgotPassword;
import com.example.artidemo.model.Employee;
import com.example.artidemo.repositories.EmployeeRepository;
import com.example.artidemo.services.ForgotPasswordService;

@Controller
public class ForgotPasswordController {
	public final static Logger logger = Logger.getLogger(ForgotPasswordController.class);
	@Autowired
	EmployeeRepository userRepository;
	
	@Autowired
	ForgotPasswordService forgotpasswordservice;
	
	@RequestMapping(method= RequestMethod.GET,value="/verifyUser/{sso}/{contactNo}")
	public ResponseEntity<?> validateMobileNumber(@PathVariable long sso,@PathVariable long contactNo){
		logger.info("Validating Mobile Number:"+contactNo);
		boolean isSsoValid=forgotpasswordservice.verifySso(sso);
		if(isSsoValid){
		boolean isMobileValid=forgotpasswordservice.verifyMobileNumber(sso,contactNo);
		if(isMobileValid){
		int otp = forgotpasswordservice.generateOTP(sso);
		boolean otpsent = forgotpasswordservice.sendOTP(otp,contactNo);
		return new ResponseEntity<>(new JsonResponse().convertToJson("1","User is verified and OTP sent to registered mobile number"), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(new JsonResponse().convertToJson("0","Invalid Mobile Number"), HttpStatus.BAD_REQUEST);
		}
		}
		else
		return new ResponseEntity<>(new JsonResponse().convertToJson("0","Invalide SSO"), HttpStatus.BAD_REQUEST);			
	}
	
	
/*	@RequestMapping(method= RequestMethod.POST,value="/verifySso/{sso}")
	public ResponseEntity<?> validateSso(@PathVariable long sso){
		logger.info("Validating SSO:"+sso);
		boolean isValid=forgotpasswordservice.verifySso(sso);
		return new ResponseEntity<>(isValid, HttpStatus.OK);			
	}
	
	@RequestMapping(method= RequestMethod.POST,value="/generateOtp/{sso}")
	public  ResponseEntity<?> generateOtpforUser(@PathVariable long sso){
		logger.info("Generating OTP for User: "+sso);
		int otp = forgotpasswordservice.generateOTP(sso);
		boolean otpsent = forgotpasswordservice.sendOTP(otp);
		return new ResponseEntity<>(otpsent,HttpStatus.OK);
	}*/
	
	@RequestMapping(method= RequestMethod.POST,value="/verifyOTP")
	public ResponseEntity<?> verifyOtp(@RequestBody ForgotPassword forgetpassword){
		logger.info("Verifying OTP");
		boolean verified = forgotpasswordservice.verifyOtp(forgetpassword);
		if(verified){
			return new ResponseEntity<>(new JsonResponse().convertToJson("1","OTP is verified"),HttpStatus.OK);			
		}
		else{
			return new ResponseEntity<>(new JsonResponse().convertToJson("0","OTP verification failed"),HttpStatus.OK);			
		}
				
	}
	
/*	@RequestMapping(method= RequestMethod.POST,value="/sendsms")
	public void Sendsms() throws JSONException{
		logger.info("sendingsms");
		msg.sendSms();	
	}	
	
	
	@RequestMapping(method= RequestMethod.POST,value="/sendIOSPush")
	public void SendIOssms() throws JSONException{
		logger.info("sendingsms");
		iosmsg.SendIosToken();	
	}	
	*/
}
