package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Committee;
import com.example.artidemo.repositories.CommitteeRepository;

@Service
public class CommitteeService {
	
	public final static Logger logger = Logger.getLogger(CommitteeService.class);
	
	@Autowired
	CommitteeRepository committeeRepository;

	public void save(Committee committee) {
	
		if(!committeeRepository.exists(committee.getId()))
		{
			logger.info("In adduser function"+committee);
			committeeRepository.save(committee);
			
		}
		else
		{
			logger.info("user already exist");
			
		}
		
	}

	public List<Committee> getAll() {

			return committeeRepository.findAll();
	}

	public Committee getByCommitteetitle(String name) {
		
		return committeeRepository.findBycommitteetitle(name);
	}

}
