package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Voting;
import com.example.artidemo.repositories.VotingRepository;

@Service
public class VotingService {
	
	public final static Logger logger = Logger.getLogger(VotingService.class);
	@Autowired
	private VotingRepository votingRepository;
	
	public String save(Voting entity)
	{
		if(!votingRepository.exists(entity.getMeetingid()) && !votingRepository.exists(entity.getEmployeeid()))
		{
			logger.info("In addVoting function"+entity);
			votingRepository.save(entity);
			return "Your vote is saved succeessfully";
		}
		else
		{
			logger.info("user voted already");
			return "This user_id is already voted for this meeting";
		}
	}
	
	/*public Voting getById(long id)
	{
		return votingRepository.findOne(id);
	}*/
	
	/* public List<Voting> getVotingbyMeetingTitle(String meetingtitle)
	 {
		 return votingRepository.findByMeetingtitle(meetingtitle);
	 }*/
	 
	 public List<Voting> getVotingbymeetingid(Long meetingid)
	 {
		 return votingRepository.findBymeetingid(meetingid);
	 }

	public List<Voting> getVotingbyquestion(String query) {
		
		return votingRepository.findByquestionContaining(query);
	}

}
