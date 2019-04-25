package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.CommitteeDetails;
import com.example.artidemo.repositories.CommitteeDetailsRepository;

@Service
public class CommitteeDetailsService {
	
public final static Logger logger = Logger.getLogger(CommitteeDetailsService.class);
	
	@Autowired
	CommitteeDetailsRepository committeeDetailsRepository;

	public void save(CommitteeDetails committeedetails) {
	
		if(!committeeDetailsRepository.exists(committeedetails.getId()))
		{
			logger.info("In adduser function"+committeedetails);
			committeeDetailsRepository.save(committeedetails);
			
		}
		else
		{
			logger.info("user already exist");
			
		}
		
	}

	public List<CommitteeDetails> getAll() {
		
		return committeeDetailsRepository.findAll();
	}

	public CommitteeDetails getByCommitteetitle(String name) {

		return committeeDetailsRepository.findBycommitteetitle(name);
	}

}
