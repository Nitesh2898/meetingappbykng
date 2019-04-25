package com.example.artidemo.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.artidemo.model.Fcm;

public interface FcmRepository extends CrudRepository<Fcm, Integer> {

	List<Fcm> findById(Long id);

	
}
