package com.aldeamo.bartender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeamo.bartender.entity.Arrays;

@Repository
public interface BartenderRepository extends JpaRepository<Arrays, Integer> {
	
}
