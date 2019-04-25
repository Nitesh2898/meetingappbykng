package com.example.artidemo.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.JsonResponse;
import com.example.artidemo.model.Voting;
import com.example.artidemo.services.VotingService;

@RestController
@RequestMapping("/Voting")
public class VotingController {
	
	final static Logger logger = Logger.getLogger(VotingController.class);
	
	@Autowired
	VotingService votingService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Voting> addVote(@RequestBody Voting vote) {
		String str = votingService.save(vote);
		logger.debug("Added:: " + vote);
		return new ResponseEntity<Voting>(vote, HttpStatus.CREATED);
	}
	
/*	@RequestMapping(path="/getVotingById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Voting> getVoting(@PathVariable("id") Long id) {
		Voting vote = votingService.getById(id);
		if (vote == null) {
			logger.debug("Voting with id " + id + " does not exists");
			return new ResponseEntity<Voting>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Vote:: " + vote);
		return new ResponseEntity<Voting>(vote, HttpStatus.OK);
	}
	*/
	/*@RequestMapping(path="/getVotingByMeetingTitle/{meetingtitle}", method = RequestMethod.GET)
	public ResponseEntity<Voting> getVotingByMeetingTitle(@PathVariable("meetingtitle") String meetingtitle) {
		List<Voting> vote = votingService.getVotingbyMeetingTitle(meetingtitle);
		if (vote == null) {
			logger.debug("Voting with id " + meetingtitle + " does not exists");
			return new ResponseEntity<Voting>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Vote:: " + vote);
		return new ResponseEntity<Voting>((Voting) vote, HttpStatus.OK);
	}*/
	
	@RequestMapping(path="/getVotingBymeetingid/{meetingid}", method = RequestMethod.GET)
	public ResponseEntity<List<Voting>> getVotingBymeetingid(@PathVariable("meetingid") Long meetingid) {
		List<Voting> vote = votingService.getVotingbymeetingid(meetingid);
		if (vote == null) {
			logger.debug("Voting with id " + meetingid + " does not exists");
			return new ResponseEntity<List<Voting>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Vote:: " + vote);
		logger.debug(vote);
		logger.debug(Arrays.toString(vote.toArray()));
		return new ResponseEntity<List<Voting>>(vote, HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/getVotingByquestion/{question}", method = RequestMethod.GET)
	public ResponseEntity<List<Voting>> getVotingByquestion(@PathVariable("question") String query) {
		List<Voting> question = votingService.getVotingbyquestion(query);
		if (question == null) {
			logger.debug("Voting with id " + query + " does not exists");
			return new ResponseEntity<List<Voting>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Vote:: " + question);
		logger.debug(question);
		logger.debug(Arrays.toString(question.toArray()));
		return new ResponseEntity<List<Voting>>(question, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}