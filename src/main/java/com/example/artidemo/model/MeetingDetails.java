package com.example.artidemo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "meetingChat")

public class MeetingDetails implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8414747698670560369L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long chatid;
	
	@Column(name = "meetingid")
	private Long meetingid;

	@Column(name = "chatuserid")
	private Long chatuserid;
	
	@Column(name = "username")
	private String username;

	@Column(name = "chat")
	private String chat;
	
	public void setId(Long chatid)
	{
	 this.chatid = chatid;
	}

	public Long getId()
	{
		return this.chatid;
	}

	public void setmeetingId(Long meetingid)
	{
	 this.meetingid = meetingid;
	}

	public Long getmeetingid()
	{
		return this.meetingid;
	}

	public void setchatuserid(Long chatuserid)
	{
	 this.chatuserid = chatuserid;
	}

	public Long getchatuserid()
	{
		return this.chatuserid;
	}

	public void setchat(String chat)
	{
	 this.chat = chat;
	}

	public String getchat()
	{
		return this.chat;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

}
