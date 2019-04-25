package com.example.artidemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name  = "Voting")
public class Voting implements java.io.Serializable{
	
	private static final long serialVersionUID = 7442038804931071106L;

	@Id
	@Column(name = "id", unique = true, nullable = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "meetingid")
	public Long meetingid;
	
	@Column(name = "meetingtitle")
	public String meetingtitle;
	
	@Column(name = "employeeid")
	public Long employeeid;
	
	/*@Column(name  = "Timestamp")
	private Timestamp timestamp;*/
	
	@Column(name  = "question")
	private String question;

	public Long getVId() {
		return id;
	}

	public void setVId(Long vid) {
		this.id = vid;
	}

	public Long getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(Long meetingid) {
		this.meetingid = meetingid;
	}

	public String getMeetingtitle() {
		return meetingtitle;
	}

	public void setMeetingtitle(String meetingtitle) {
		this.meetingtitle = meetingtitle;
	}

	public Long getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}

	/*public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}*/

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	

		
}
	
	
