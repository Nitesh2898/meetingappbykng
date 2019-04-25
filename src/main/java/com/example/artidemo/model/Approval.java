package com.example.artidemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval")
public class Approval implements java.io.Serializable{

	private static final long serialVersionUID = 1649028720269185244L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "meeting_id")
	private Long meeting_id;

	@Column(name = "employee_id")
	private Long employee_id;
	
	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMeeting_id() {
		return meeting_id;
	}

	public void setMeeting_id(Long meeting_id) {
		this.meeting_id = meeting_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
