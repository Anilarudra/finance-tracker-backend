package com.example.financetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financetracker.model.Budgets;
import com.example.financetracker.model.Users;
import com.example.financetracker.repository.BudgetRepository;
import com.example.financetracker.repository.UserRepository;

@Service
public class BudgetsService {
	
	@Autowired
	private BudgetRepository bRepository;
	@Autowired
	private UserRepository uRepository;

	public Budgets addBudget(Budgets budget, Long userId) {
		// TODO Auto-generated method stub
		
		Users user = uRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    budget.setUser(user);
	    bRepository.save(budget);
		
		return budget;
	}

	public Budgets updateBudget(Budgets budget, String category, Long userId) {
		// TODO Auto-generated method stub
		
		Budgets existingBudget = bRepository
		        .findByCategoryAndUserUserid(category, userId)
		        .orElseThrow(() -> new RuntimeException("Budget not found"));
        System.out.println("Existing Budget Limit = " + existingBudget.getLimitAmount());
	    existingBudget.setLimitAmount(budget.getLimitAmount());
	    bRepository.save(existingBudget);
        System.out.println("Updated Budget Limit = " + existingBudget.getLimitAmount());
	    
	    return existingBudget;
	}

	public List<Budgets> getUserBudgets(Long userId) {
		// TODO Auto-generated method stub
		
		List<Budgets> budgets = bRepository.findByUserUserid(userId);
		return budgets;
	}

	public void deleteBudget(Long userId, String category) {
		// TODO Auto-generated method stub
		
		Budgets existingBudget = bRepository
		        .findByCategoryAndUserUserid(category, userId)
		        .orElseThrow(() -> new RuntimeException("Budget not found"));
	    bRepository.delete(existingBudget);
		
	}

}
