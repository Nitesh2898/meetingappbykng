package com.example.artidemo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.MeetingDetails;
import com.example.artidemo.repositories.MeetingDetailsRepository;

@Service
public class MeetingDetailsService {
	@Autowired
	private MeetingDetailsRepository meetingRepository;
	
	public MeetingDetails save(MeetingDetails entity)
	{
		return meetingRepository.save(entity);
	}
	
	public MeetingDetails getById(long id){
		return meetingRepository.findOne(id);
	}
	
	public List<MeetingDetails> getAll(){
		return meetingRepository.findAll();
	}
	
	public void delete(long id)
	{
		meetingRepository.delete(id);
	}

	public MeetingDetails getByMeetingId(Long id) {
		return meetingRepository.findOne(id);
	}

	public List<MeetingDetails> getAllChat(Long meetingid) {
		return meetingRepository.findBymeetingid(meetingid);
	}

	/*public List<MeetingDetails> getAllById(MeetingDetails id) {
		// TODO Auto-generated method stub
		//return meetingRepository.findAll(id);
	} */

	
}
