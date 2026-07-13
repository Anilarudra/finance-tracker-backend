package com.example.financetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financetracker.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);
	
	Optional<Users> findById(Long id);

}
