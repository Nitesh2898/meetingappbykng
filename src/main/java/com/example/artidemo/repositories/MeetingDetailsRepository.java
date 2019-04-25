package com.example.artidemo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.MeetingDetails;

@Repository
public interface MeetingDetailsRepository extends JpaRepository<MeetingDetails, Long>{

	MeetingDetails findOne(Long mid);
	/*List<MeetingDetails> findAllById(Iterable<ID> ids);*/

	List<MeetingDetails> findBymeetingid(Long meetingid);

}
