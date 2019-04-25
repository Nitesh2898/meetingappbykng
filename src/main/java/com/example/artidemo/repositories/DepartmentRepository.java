package com.example.artidemo.repositories;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, Long>{
	public List<Departments> findDepartmentByName(Departments department);
	public Departments findByName(String department);
}
