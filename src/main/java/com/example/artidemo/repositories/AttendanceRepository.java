package com.example.artidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.artidemo.model.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Long>{

	@Query("SELECT DISTINCT a.employee_id,a.name FROM Attendance a WHERE a.meeting_id = :meeting_id")
	List<Attendance> findAttendanceByid(@Param("meeting_id") Long meeting_id);
	
	

	

}
