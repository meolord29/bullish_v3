package com.bullish.assignment1v3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bullish.assignment1v3.model.users.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
	
	Optional<Client> findByUsername(String username);
}
