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
@Table(name = "department")
public class Departments implements java.io.Serializable{
	private static final long serialVersionUID = 4910225916550731446L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "contactNo")
	private Long contactNo;

	@Column(name = "email")
	private String email;


	public void setId(Long id)
	{
	 this.id = id;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setname(String name)
	{
	 this.name = name;
	}

	public String getname()
	{
		return this.name;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		Long cont = Long.valueOf(contactNo);
		this.contactNo = cont;
	}

	public String getEmailDate() {
		return this.email;
	}

	public void setEmailDate(String email) {
		this.email = email;
	}

}
