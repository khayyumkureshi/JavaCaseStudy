package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Train;

public interface TrainRepository extends JpaRepository<Train,Integer>{
	
}