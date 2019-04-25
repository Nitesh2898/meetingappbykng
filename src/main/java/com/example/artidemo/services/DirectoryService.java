package com.example.artidemo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.artidemo.model.Directory;
import com.example.artidemo.repositories.DirectoryRepository;

@Service
public class DirectoryService {
	
	public final static Logger logger = Logger.getLogger(DirectoryService.class);
	@Autowired
	private DirectoryRepository directoryRepository;
	
	public Directory getById(long id){
		return directoryRepository.findOne(id);
	}
	
	public List<Directory> getAll(){
		return directoryRepository.findAll();
	}
	
	public void delete(long id)
	{
		directoryRepository.delete(id);
	}

	public void save(Directory employee) {
		// TODO Auto-generated method stub
		
		if(!directoryRepository.exists(employee.getId()))
		{
			logger.info("In adduser function"+employee);
			directoryRepository.save(employee);
			
		}
		else
		{
			logger.info("user already exist");
			
		}
		
	}
	

}
