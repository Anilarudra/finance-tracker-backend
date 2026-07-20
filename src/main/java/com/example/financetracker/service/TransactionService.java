package com.example.financetracker.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.financetracker.model.Accounts;
import com.example.financetracker.model.Transactions;
import com.example.financetracker.model.Users;
import com.example.financetracker.repository.AccountsRepository;
import com.example.financetracker.repository.TransactionRepository;
import com.example.financetracker.repository.UserRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository tRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private AccountsRepository accRepo;
	
	
	//<--------------- Add Transaction ------------------>
	
	public void addTransaction(Transactions transaction, Long userId) throws RuntimeException {
		// TODO Auto-generated method stub
		
		Users user = uRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
		
		List<Accounts> accounts = accRepo.findByUserUserid(userId);
		if (accounts.isEmpty()) {
		    throw new RuntimeException("Account not found");
		}
		Accounts account = accounts.get(0);

	    transaction.setUser(user);
	    transaction.setAccount(account);
	    
	    if(transaction.getType().equalsIgnoreCase("expense")) {
	    	account.setBalance(account.getBalance() - transaction.getAmount());
	    } else if(transaction.getType().equalsIgnoreCase("income")) {
	    	account.setBalance(account.getBalance() + transaction.getAmount());
	    }
	    accRepo.save(account);
	    tRepo.save(transaction);
		
		System.out.println("Transaction added successfully"); 
	}
	
	//<----------------- Update Transaction ------------------>

	public Transactions updateTransaction(Transactions transaction, Long transactionId) throws RuntimeException {
		// TODO Auto-generated method stub
		
		Transactions existingTransaction = tRepo.findById(transactionId)
	            .orElseThrow(() -> new RuntimeException("Transaction not found"));

		Users user = existingTransaction.getUser();
		System.out.println("Existing ID = " + existingTransaction.getId());
		existingTransaction.setId(transactionId);
	    existingTransaction.setType(transaction.getType());
	    existingTransaction.setCategory(transaction.getCategory());
	    existingTransaction.setNote(transaction.getNote());
	    existingTransaction.setAmount(transaction.getAmount());
	    existingTransaction.setDate(transaction.getDate());
	    existingTransaction.setUser(user);
	    tRepo.save(existingTransaction);
		
		System.out.println("Transaction updated successfully");
		return existingTransaction;
	}

	
	//<----------------- Delete Transaction ------------------>
	
	public ResponseEntity<Void> deleteTransaction(Long transactionId) {
		// TODO Auto-generated method stub
		Transactions existingTransaction = tRepo.findById(transactionId)
	            .orElseThrow(() -> new RuntimeException("Transaction not found"));

	    tRepo.delete(existingTransaction);
	    System.out.println("Transaction deleted successfully");
	    return ResponseEntity.noContent().build();
		
	}

	//<------------------ Export Transactions to CSV ------------------>
	
	 public ByteArrayInputStream exportTransactions(Long userId) {

	        List<Transactions> transactions = tRepo.findByUserUserid(userId);

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        PrintWriter writer = new PrintWriter(out);

	        // CSV Header
	        writer.println("Id,Type,Category,Note,Amount,Date");

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        for (Transactions t : transactions) {

	            writer.println(
	                    t.getId() + "," +
	                    t.getType() + "," +
	                    t.getCategory() + "," +
	                    t.getNote() + "," +
	                    t.getAmount() + "," +
	                    sdf.format(t.getDate())
	            );
	        }

	        writer.flush();

	        return new ByteArrayInputStream(out.toByteArray());
	    }

	 public List<Transactions> getUserTransactions(Long userId) {
		// TODO Auto-generated method stub
		 
		 Users user = uRepo.findById(userId)
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    List<Transactions> transactions = tRepo.findByUserUserid(userId);
		    return transactions;
		
	 }

	
}
