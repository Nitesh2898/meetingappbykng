package com.example.artidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artidemo.model.Voting;

public interface VotingRepository extends JpaRepository<Voting, Long>{

	//List<Voting> findByMeetingtitle(String meetingtitle);
	List<Voting> findBymeetingid(Long meetingid);

	List<Voting> findByquestionContaining(String query);


}