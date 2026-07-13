package com.example.financetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.financetracker.model.Budgets;

@Repository
public interface BudgetRepository extends JpaRepository<Budgets, Long> {
	
	
	Optional<Budgets> findByCategoryAndUserUserid(String category, Long userId);

	List<Budgets> findByUserUserid(Long userId);
	

}
