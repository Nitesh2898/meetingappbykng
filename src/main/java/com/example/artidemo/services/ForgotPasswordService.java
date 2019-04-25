package com.example.artidemo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.Constants;
import com.example.artidemo.model.ForgotPassword;
import com.example.artidemo.repositories.ForgotPasswordRepository;
import com.example.artidemo.repositories.EmployeeRepository;
import com.example.artidemo.repositories.AdminRepository;

@Service
public class ForgotPasswordService {
	
	public final static Logger logger = Logger.getLogger(ForgotPasswordService.class);
	
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	ForgotPasswordRepository forgotpasswordRepository;
	@Autowired
	AdminRepository adminRepo;
	

	public boolean verifyMobileNumber(long id,long contactNo) {
		if(adminRepo.findByIdAndContactNo(id,contactNo).isEmpty()){
			if(empRepository.findByIdAndContactNo(id,contactNo).isEmpty())
			{
				return false;
			}	
			else
				return true;
		}
		else
		{
			return true;
		}
	
	}

	public boolean verifySso(long id) {
		if(adminRepo.findOne(id) != null){
			return true;
		}
		else if(empRepository.findOne(id) != null){
			return true;
		}
		else
		return false;
	}

	public int generateOTP(long id) {
		Random random = new Random();
		int value = random.nextInt(9000)+1000;
		ForgotPassword forgotpassword = new ForgotPassword();
		List<ForgotPassword> fp = forgotpasswordRepository.findById(id);
		if(!fp.isEmpty()){
			forgotpasswordRepository.delete(fp);
		}
		forgotpassword.setOtp(value);
		forgotpassword.setid(id);
		forgotpasswordRepository.save(forgotpassword);
		return value;
	}

	public boolean sendOTP(int otp,long contactNo) {
		String mainUrl=Constants.URL;
		String mobiles = String.valueOf(contactNo);
		String message = "OTP to reset your Password is: "+otp;
		String route=Constants.Route;
		URLConnection myURLConnection=null;
		URL myURL=null;
		BufferedReader reader=null;
		String encoded_message=URLEncoder.encode(message);
		StringBuilder sbPostData= new StringBuilder(mainUrl);
		sbPostData.append(Constants.AUTH_KEY+Constants.AUTHENTICATION_KEY); 
		sbPostData.append(Constants.MOBILE+mobiles);
		sbPostData.append(Constants.MESSAGE+encoded_message);
		sbPostData.append(Constants.ROUTE+route);
		sbPostData.append(Constants.SENDER+Constants.SENDER_ID);
		mainUrl = sbPostData.toString();
		logger.error("sending Message Using Auth Key :"+mainUrl);
		try
		{
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null) 
			logger.info("message successful"+response);
			reader.close();
			return true;
			} 
			catch (IOException e) 
			{ 
				logger.error("Exception occured while sending SMS"+e.getMessage());
				return false;
			}
			}

	public boolean verifyOtp(ForgotPassword forgetpasswordinput) {
		List<ForgotPassword> forgotpassword = forgotpasswordRepository.findById(forgetpasswordinput.getid());
		if(!forgotpassword.isEmpty()){
		if(forgotpassword.get(0).getOtp()==forgetpasswordinput.getOtp()){
			return true;
		}
		
		else
			return false;
		}
		else{
			return false;
		}
	} 
	}


