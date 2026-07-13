package com.example.financetracker.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financetracker.model.Transactions;
import com.example.financetracker.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping("/{userId}/add")
	public Transactions addTransaction(@PathVariable("userId") Long userId, @RequestBody Transactions transaction) {
		// Implement transaction addition logic here
		transactionService.addTransaction(transaction, userId);
		return transaction;
	}
	
	@PutMapping("/{transactionId}/update")
	public Transactions updateTransaction(@PathVariable("transactionId") Long transactionId, @RequestBody Transactions transaction) {
		// Implement transaction update logic here
		System.out.println("PUT endpoint called");
		return transactionService.updateTransaction(transaction, transactionId);
		
	}
	
	@DeleteMapping("/{transactionId}/delete")
	public String deleteTransaction(@PathVariable("transactionId") Long transactionId) {
		// Implement transaction deletion logic here
		transactionService.deleteTransaction(transactionId);
		return "Transaction deleted successfully";
	}
	
	
	@GetMapping("/{userId}")
	public List<Transactions> getUserTransactions(@PathVariable Long userId) {
	    // Implement logic to retrieve user transactions here
	    return transactionService.getUserTransactions(userId);
	}
	
	
	
	@GetMapping("/{userId}/download")
    public ResponseEntity<InputStreamResource> downloadCSV( @PathVariable Long userId) {

        ByteArrayInputStream csv = transactionService.exportTransactions(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=transactions.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csv));
    }
}
