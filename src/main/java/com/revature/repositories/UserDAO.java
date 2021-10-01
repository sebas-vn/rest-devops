package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Address;
import com.revature.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{

	/*
	 * This is a Property Expression
	 * Property Expressions infer SQL statements on entities based on the DIRECT
	 * properties of the entity being mangaged.
	 */
	
	// Since we are inheriting all methods from JPARepository, we already have save(), get()...etc...
	public Optional<User> findByUsername(String username);
	// eventually venture into Optional.ofNullable

	@Transactional // This means I want this particualr method to be part of a transaction
	// @Modifying  // I would use this if I intend for some method to manipulate and modify data in a DB
	@Query(value= "FROM User WHERE email LIKE %:substr") // Note that I'm calling the Java properties and object name -> this is JPQL 
	public List<User> findByEmailContains(String substr); // johnsmi -> reutrns johnsmith@gmail.com
	
}
