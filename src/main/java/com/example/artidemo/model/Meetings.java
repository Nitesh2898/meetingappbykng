package com.example.artidemo.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "meetingSchedule")

public class Meetings implements java.io.Serializable{
	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@JsonProperty("meetingId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long meetingId;

	@Column(name = "agenda")
	private String agenda;

	@Column(name = "description")
	private String description;

	@Column(name = "meetingDate")
	private Timestamp meetingDate;
	
	@Column(name = "meetingDateEnd")
	private Timestamp meetingDateEnd;
	
	@Column(name = "fileUrl")
	private String fileUrl;

	@Column(name = "venue")
	private String venue;
	
	@Column(name = "updates")
	private String updates;
	
	@Column(name = "decision")
	private String decision;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "meetingtitle")
	private String meetingtitle;

	public void setId(Long meetingId)
	{
	 this.meetingId = meetingId;
	}

	public Long getId()
	{
		return this.meetingId;
	}

	public void setagenda(String agenda)
	{
	 this.agenda = agenda;
	}

	public String getagenda()
	{
		return this.agenda;
	}

	public void setdescription(String description)
	{
	 this.description = description;
	}

	public String getdescription()
	{
		return this.description;
	}

	public String getmeetingDate() {
		return this.meetingDate.toString();
	}
	
	public String getmeetingDateEnd() {
		return this.meetingDateEnd.toString();
	}

	public void setmeetingDate (String meetingDate ) {
		Timestamp t= Timestamp.valueOf(meetingDate );
		this.meetingDate = t;
	}
	
	public void setmeetingDateEnd(String meetingDateEnd) {
		Timestamp t= Timestamp.valueOf(meetingDateEnd);
		this.meetingDateEnd = t;
	}
	
	public void setfileUrl(String fileUrl)
	{
	 this.fileUrl = fileUrl;
	}

	public String getfileUrl()
	{
		return this.fileUrl;
	}
	
	public void setVenue(String venue)
	{
	 this.venue = venue;
	}
	
	public String getVenue()
	{
		return this.venue;
	}
	
	public void setupdates(String updates)
	{
	 this.updates = updates;
	}
	
	public String getupdates()
	{
		return this.updates;
	}
	
	public void setdecision(String decision)
	{
	 this.decision = decision;
	}
	
	public String getdecision()
	{
		return this.decision;
	}
	
	public void setaction(String action)
	{
	 this.action = action;
	}
	
	public String getaction()
	{
		return this.action;
	}
	
	public void setmeetingtitle(String meetingtitle)
	{
	 this.meetingtitle = meetingtitle;
	}

	public String getmeetingtitle()
	{
		return this.meetingtitle;
	}

}
