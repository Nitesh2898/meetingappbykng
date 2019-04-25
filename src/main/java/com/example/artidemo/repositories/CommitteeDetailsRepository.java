package com.example.artidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.CommitteeDetails;

@Repository
public interface CommitteeDetailsRepository extends JpaRepository<CommitteeDetails, Long> {

	public CommitteeDetails findBycommitteetitle(String committeetitle);

}
