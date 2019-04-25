package com.example.artidemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Departments;
import com.example.artidemo.repositories.DepartmentRepository;

@Service
public class DeaprtmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Departments save(Departments entity)
	{
		return departmentRepository.save(entity);
	}
	
	public Departments getById(long id){
		return departmentRepository.findOne(id);
	}
	
	public Departments getByName(String name){
		return departmentRepository.findByName(name);
	}
	
	public List<Departments> getAll(){
		return departmentRepository.findAll();
	}
	
	public void delete(long id)
	{
		departmentRepository.delete(id);
	}
}
