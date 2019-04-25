package com.example.artidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.artidemo.model.Approval;

public interface ApprovalRepository extends CrudRepository<Approval, Long>{
	
	@Query("SELECT DISTINCT a.employee_id,a.name FROM Approval a WHERE a.meeting_id = :meeting_id")
	List<Approval> findApprovalByid(@Param("meeting_id") Long meeting_id);

}
