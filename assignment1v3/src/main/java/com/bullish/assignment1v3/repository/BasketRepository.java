package com.bullish.assignment1v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bullish.assignment1v3.model.store.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long>{

    Optional<Basket> findByUsernameAndProductName(String clientUsername, String productName);

    List<Basket> findByUsernameIs(String username);

    List<Basket> findByProductName(String productName);
    
}
