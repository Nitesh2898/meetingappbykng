package com.example.artidemo.repositories;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Meetings;

@Repository
public interface MeetingRepository extends JpaRepository<Meetings, Long>{
	public List<Meetings> findByMeetingDate(Timestamp today);
	public List<Meetings> findByMeetingDateGreaterThanAndMeetingDateLessThan(Timestamp end, Timestamp start);
	//Meetings findByMeetingDateEndGreaterThanAndMeetingDateEndLessThan(Timestamp end, Timestamp start);
	public List<Meetings> findByMeetingDateAfter(Timestamp start);
	public Meetings findByMeetingDateLessThanAndMeetingDateGreaterThan(Timestamp end, Timestamp start);
}
