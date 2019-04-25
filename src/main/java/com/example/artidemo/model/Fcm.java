package com.example.artidemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Entity
@Table(name = "deviceinfo")
public class Fcm {

	@Id
	@JsonProperty("id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="userid")
	@JsonProperty("userid")
	private long userid;
	
	@Column(name = "deviceId")
	@JsonProperty("deviceId")
	private String deviceId;
	
	@Column(name = "platform")
	@JsonProperty("platform")
	private String platform;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
	

	@JsonProperty("userid")
	public long getSso() {
		return userid;
	}
	
	@JsonProperty("sso")
	public void setSso(long sso) {
		this.userid = sso;
	}
	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}
	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@JsonProperty("platform")
	public String getPlatform() {
		return platform;
	}
	@JsonProperty("platform")
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setSso1(String id2) {
		// TODO Auto-generated method stub
		
	}
	
	}
