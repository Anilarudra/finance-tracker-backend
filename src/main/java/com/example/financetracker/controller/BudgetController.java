package com.example.financetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financetracker.model.Budgets;
import com.example.financetracker.service.BudgetsService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
	
	@Autowired
	private BudgetsService bService;
	
	@PostMapping("/{userId}/add")
	public Budgets addBudget(@PathVariable("userId") Long userId, @RequestBody Budgets budget) {
		// Implement budget addition logic here
		
		System.out.println("Budget Limit : " + budget.getLimitAmount());
		return bService.addBudget(budget, userId);
		
	}
	
	@PatchMapping("/{userId}/{category}/setlimit")
	public Budgets updateBudget(@PathVariable("userId") Long userId, @PathVariable("category") String category, @RequestBody Budgets budget) {
		// Implement budget update logic here
		return bService.updateBudget(budget, category, userId);
		
	}
	
	@GetMapping("/{userId}")
	public List<Budgets> getUserBudgets(@PathVariable Long userId) {
	    // Implement logic to retrieve user budgets here
	    return bService.getUserBudgets(userId);
	}
	
	@DeleteMapping("/{userId}/{category}/delete")
	public String deleteBudget(@PathVariable("userId") Long userId, @PathVariable("category") String category) {
	    // Implement budget deletion logic here
	    bService.deleteBudget(userId, category);
	    return "Budget deleted successfully";
	}

}
