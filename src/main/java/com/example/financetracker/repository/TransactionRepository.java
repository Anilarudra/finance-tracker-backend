package com.example.financetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financetracker.model.Transactions;


@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
	
	
	 Optional<Transactions> findById(Long id);
	 
	 List<Transactions> findByUserUserid(Long userid);

}
