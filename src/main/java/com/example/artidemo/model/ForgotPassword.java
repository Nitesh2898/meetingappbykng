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
@Table(name = "otp")
public class ForgotPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long otpId;
	
	@Column(name="otp")
	private int otp;
	
	@Column(name="id")
	private long id;

	public long getOtp() {
		return otp;
	}

	public long getOtpId() {
		return otpId;
	}

	public void setOtpId(long otpId) {
		this.otpId = otpId;
	}

	public void setOtp(int value) {
		this.otp = value;
	}

	public long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}


}
