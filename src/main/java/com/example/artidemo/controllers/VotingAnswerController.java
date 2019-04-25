package com.example.artidemo.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Meetings;
import com.example.artidemo.model.VotingAnswer;
import com.example.artidemo.services.VotingAnswerService;



@RestController
@RequestMapping("/votinganswer")
public class VotingAnswerController {
	
	final static Logger logger = Logger.getLogger(VotingAnswerController.class);

	@Autowired
	VotingAnswerService votinganswerService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VotingAnswer> addVoting(@RequestBody VotingAnswer votinganswer) {
		votinganswerService.save(votinganswer);
		logger.debug("Added:: " + votinganswer);
		return new ResponseEntity<VotingAnswer>(votinganswer, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/getAllVotingAnswers",method = RequestMethod.GET)
	public ResponseEntity<List<VotingAnswer>> getAllVotingAnswers() {
		List<VotingAnswer> votinganswers = votinganswerService.getAll();
		if (votinganswers.isEmpty()) {
			logger.debug("Voting Answer does not exists");
			return new ResponseEntity<List<VotingAnswer>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + votinganswers.size() + " VotingAnswer");
		logger.debug(Arrays.toString(votinganswers.toArray()));
		return new ResponseEntity<List<VotingAnswer>>(votinganswers, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(path="/getVotingAnswerByvid/{vid}", method = RequestMethod.GET)
	public ResponseEntity<List<VotingAnswer>> getVotingAnswerByvid(@PathVariable("vid") Long id) {
		List<VotingAnswer> vid = votinganswerService.getByvid(id);
		if (vid == null) {
			logger.debug("Voting with id " + id + " does not exists");
			return new ResponseEntity<List<VotingAnswer>>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Voting:: " + vid);
		return new ResponseEntity<List<VotingAnswer>>(vid, HttpStatus.OK);
	}

}
