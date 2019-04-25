package com.example.artidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artidemo.model.Directory;



@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {


}
