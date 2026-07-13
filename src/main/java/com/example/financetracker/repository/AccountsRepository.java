package com.example.financetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financetracker.model.Accounts;
import com.example.financetracker.model.Users;

@Repository

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	List<Accounts> findByUserUserid(Long userId);

	Accounts findByAccountNumber(String accountNumber);

	
	
	
	

	

}
