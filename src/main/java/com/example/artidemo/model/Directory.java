package com.example.artidemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author BytesTree
 *
 */

@Entity
@Table(name = "directory")
public class Directory implements java.io.Serializable {

	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "department", length = 20)
	private String department;

	@Column(name = "designation", length = 20)
	private String designation;
	
	@Column(name = "mobile_no", length = 20)
	private Long mobile_no;
	
	@Column(name = "phone_no", length = 20)
	private Long phone_no;

	@Column(name = "email")
	private String email;

	public Directory() {
	}

	public Directory(Long id) {
		this.id = id;
	}

	public Directory(Long id, String name,String department, String designation, Long mobile_no, Long phone_no,String email) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.designation = designation;
		this.mobile_no = mobile_no;
		this.phone_no = phone_no;
		this.email = email;
	}

	public Directory(String name,String department, String designation, Long mobile_no, Long phone_no,String email) {
		this.name = name;
		this.name = name;
		this.department = department;
		this.designation = designation;
		this.mobile_no = mobile_no;
		this.phone_no = phone_no;
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(Long phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.id).append(", Name: ").append(this.name).append(", Department: ").append(this.department).append(", Designation: ")
				.append(this.designation).append(", Mobile: ").append(this.mobile_no).append(", Phone: ")
				.append(this.phone_no).append(", Email: ").append(this.email);
		return sb.toString();
	}


}

	

