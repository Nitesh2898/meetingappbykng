package com.example.artidemo.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.artidemo.model.Directory;
import com.example.artidemo.services.DirectoryService;


/**
 * 
 * @author BytesTree
 *
 */

@RestController
@RequestMapping("/directory")
public class DirectoryController {

	final static Logger logger = Logger.getLogger(DirectoryController.class);

	@Autowired
	DirectoryService empService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Directory> addDirectory(@RequestBody Directory directory) {
		empService.save(directory);
		logger.debug("Added:: " + directory);
		return new ResponseEntity<Directory>(directory, HttpStatus.CREATED);
	}


	@RequestMapping(path="/updateDirectory",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateDirectory(@RequestBody Directory directory) {
		Directory existingEmp = empService.getById(directory.getId());
		if (existingEmp == null) {
			logger.debug("Directory with id " + directory.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.save(directory);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(path="/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Directory> getDirectory(@PathVariable("id") Long id) {
		Directory directory = empService.getById(id);
		if (directory == null) {
			logger.debug("Directory with id " + id + " does not exists");
			return new ResponseEntity<Directory>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Directory:: " + directory);
		return new ResponseEntity<Directory>(directory, HttpStatus.OK);
	}


	@RequestMapping(path="/getDirectory",method = RequestMethod.GET)
	public ResponseEntity<List<Directory>> getAllDirectorys() {
		List<Directory> directorys = empService.getAll();
		if (directorys.isEmpty()) {
			logger.debug("Directorys does not exists");
			return new ResponseEntity<List<Directory>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + directorys.size() + " Directorys");
		logger.debug(Arrays.toString(directorys.toArray()));
		return new ResponseEntity<List<Directory>>(directorys, HttpStatus.OK);
	}


	@RequestMapping(path="/deleteDirectory/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDirectory(@PathVariable("id") Long id) {
		Directory directory = empService.getById(id);
		if (directory == null) {
			logger.debug("Directory with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.delete(id);
			logger.debug("Directory with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
