package com.bullish.assignment1v3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bullish.assignment1v3.model.users.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	Optional<Admin> findByUsername(String username);
}