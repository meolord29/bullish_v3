package com.bullish.assignment1v3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bullish.assignment1v3.model.store.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	Optional<Product> findByName(String name);
}
