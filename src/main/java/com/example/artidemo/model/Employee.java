package com.example.artidemo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable{
	
	private static final long serialVersionUID = 4910225916550731446L;

@Id
@Column(name = "id", unique = true, nullable = false)
private Long id;

@Column(name = "firstname")
private String firstname;

@Column(name = "lastname")
private String lastname;

@Column(name = "designation")
private String designation;

@Column(name = "department")
private String department;

@Column(name = "emailid")
private String emailid;

@Column(name="contactNo")
private long contactNo;

@Column(name="Adhaar_card")
private long Adhaar_card;

@Column(name = "password")
private String password;

@Column(name = "adminRights")
private String adminRights;

public Employee() {
}

public Employee(Long id) {
	this.id = id;
}

public Employee(Long id, String firstname, String lastname, String designation, String department) {
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.designation = designation;
	this.department = department;
}

public Employee(String firstname, String lastname, String designation, String department) {
	this.firstname = firstname;
	this.lastname = lastname;
	this.designation = designation;
	this.department = department;
}


public void setId(Long id)
{
 this.id = id;
}

public Long getId()
{
	return this.id;
}

public void setFirstname(String firstname)
{
 this.firstname = firstname;
}

public String getFirstname()
{
	return this.firstname;
}

public void setLastname(String lastname)
{
 this.lastname = lastname;
}

public String getLastname()
{
	return this.lastname;
}

public void setDesignation(String designation)
{
 this.designation = designation;
}

public String getDesignation()
{
	return this.designation;
}

public void setdepartment(String department)
{
 this.department = department;
}

public String getdepartment()
{
	return this.department;
}

public long getContactNo() {
	return contactNo;
}

public void setContactNo(String contactNo) {
	Long cont = Long.valueOf(contactNo);
	this.contactNo = cont;
}

public void setemail(String emailid)
{
 this.emailid = emailid;
}

public String getemail()
{
	return this.emailid;
}

public void setpassword(String password)
{
 this.password = password;
}

public String getpassword()
{
	return this.password;
}

public void setadminRights(String adminRights)
{
 this.adminRights = adminRights;
}

public String getadminRights()
{
	return this.adminRights;
}

@Override
public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Id: ").append(this.id).append(", firstName: ").append(this.firstname).append(", lastName: ")
			.append(this.lastname).append(", Designation: ").append(this.designation).append(", department: ")
			.append(this.department);
	return sb.toString();
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (id == null || obj == null || getClass() != obj.getClass())
		return false;
	Employee toCompare = (Employee) obj;
	return id.equals(toCompare.id);
}

@Override
public int hashCode() {
	return id == null ? 0 : id.hashCode();
}
}
