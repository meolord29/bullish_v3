package com.bullish.assignment1v3.repository;

@Repository
public interface AdminRepository extends JpaRepository<User,Long>{
	
	Optional<Admin> findByUsername(String username);
