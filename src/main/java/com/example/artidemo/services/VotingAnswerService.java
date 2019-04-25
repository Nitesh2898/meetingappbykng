package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.VotingAnswer;
import com.example.artidemo.repositories.VotingAnswerRepository;

@Service
public class VotingAnswerService {
	
	public final static Logger logger = Logger.getLogger(CommitteeService.class);
	
	@Autowired
	VotingAnswerRepository votingAnswerRepository;

	public void save(VotingAnswer committee) {
		
		if(!votingAnswerRepository.exists(committee.getId()))
		{
			logger.info("In adduser function"+committee);
			votingAnswerRepository.save(committee);
			
		}
		else
		{
			logger.info("user already exist");
			
		}
		
	}

	public List<VotingAnswer> getAll() {
		
		return votingAnswerRepository.findAll();
	}

	public List<VotingAnswer> getByvid(Long id) {
		
		
		return votingAnswerRepository.findByvid(id);
	}
	
	
	
	

}
