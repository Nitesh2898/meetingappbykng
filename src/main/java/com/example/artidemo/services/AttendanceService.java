package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Attendance;
import com.example.artidemo.repositories.AttendanceRepository;

@Service
public class AttendanceService {
	
	public final static Logger logger = Logger.getLogger(AttendanceService.class);
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public String save(Attendance entity)
	{
		if(!attendanceRepository.exists(entity.getMeeting_id())  && !attendanceRepository.exists(entity.getEmployee_id()))
		{
			logger.info("In add attendance function"+entity);
			attendanceRepository.save(entity);
			return "Attendance have been successfully Added.";
		}
		else
		{
			logger.info("attendance already Given");
			return "This user_id attendance already given";
		}
	}

	public List<Attendance> getByMeetingId(Long id) {
		
		return attendanceRepository.findAttendanceByid(id);
	}

}
