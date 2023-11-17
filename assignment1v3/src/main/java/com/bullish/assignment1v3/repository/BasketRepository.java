package com.bullish.assignment1v3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long>{
	
	Optional<Client> findByUsername(String username);
}
