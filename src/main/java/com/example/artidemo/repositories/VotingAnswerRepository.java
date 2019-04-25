package com.example.artidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.VotingAnswer;

@Repository
public interface VotingAnswerRepository extends JpaRepository<VotingAnswer, Long>{

	public List<VotingAnswer> findByvid(Long vid);

}
