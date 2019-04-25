package com.example.artidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Employee;
import com.example.artidemo.model.ForgotPassword;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<ForgotPassword> findByIdAndContactNo(long id, long contactNo);
	
	@Query("SELECT e.emailid FROM Employee e")
	List<String> findAllEmails();

}
