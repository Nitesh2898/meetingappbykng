package com.example.artidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Committee;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {

	Committee findBycommitteetitle(String committee);


}
