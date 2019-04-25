package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Approval;
import com.example.artidemo.repositories.ApprovalRepository;



@Service
public class ApprovalService {

public final static Logger logger = Logger.getLogger(ApprovalService.class);
	
	@Autowired
	private ApprovalRepository approvalRepository;
	
	public String save(Approval entity)
	{
		if(!approvalRepository.exists(entity.getMeeting_id())  && !approvalRepository.exists(entity.getEmployee_id()))
		{
			logger.info("In add approval function"+entity);
			approvalRepository.save(entity);
			return "Approval have been successfully Added.";
		}
		else
		{
			logger.info("approval already Given");
			return "This user_id approval already given";
		}
	}

	public List<Approval> getByMeetingId(Long id) {
		
		return approvalRepository.findApprovalByid(id);
	}
	
}
