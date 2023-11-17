package com.bullish.assignment1v3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bullish.assignment1v3.model.store.ConfirmedPurchase;

@Repository
public interface ConfirmedPurchaseRepository extends JpaRepository<ConfirmedPurchase,Long>{
	
	Optional<ConfirmedPurchase> findByUsername(String username);
}
