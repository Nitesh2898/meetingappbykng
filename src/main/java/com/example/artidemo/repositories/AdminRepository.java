package com.example.artidemo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Admin;
import com.example.artidemo.model.ForgotPassword;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	List<ForgotPassword> findByIdAndContactNo(long id, long contactNo);
	
}
