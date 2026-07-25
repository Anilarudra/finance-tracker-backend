package com.example.financetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.financetracker.enums.AccountStatus;
import com.example.financetracker.model.Accounts;
import com.example.financetracker.model.Users;
import com.example.financetracker.repository.AccountsRepository;
import com.example.financetracker.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	public void registerUser(Users user) {
		// TODO Auto-generated method stub
		
		// Encrypt password before saving
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        System.out.println(
            "User registered successfully: "
            + user.getUserid()
            + " "
            + user.getEmail()
        );
    }

	public Accounts addAccount(Long userId, Accounts account) {

	    

	    Users user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    

	    Accounts existing = accountsRepository.findByAccountNumber(account.getAccountNumber());

	    if(existing != null){
	        throw new RuntimeException("Account already exists");
	    }

	   

	    List<Accounts> accounts = accountsRepository.findByUserUserid(userId);

	    if(accounts.isEmpty()){
	        account.setStatus(AccountStatus.PRIMARY);
	    }else{
	        account.setStatus(AccountStatus.SECONDARY);
	    }

	    account.setUser(user);


	    Accounts saved = accountsRepository.save(account);

	 

	    return saved;
	}

	public List<Accounts> getUserAccounts(Long userId) {
		
		Users user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    List<Accounts> accounts = accountsRepository.findByUserUserid(userId);

	    if (!accounts.isEmpty()) {
	        return accounts;
	    }
		return null;
	}

	public void deleteAccount(Long userId, Long accountId) {
		// TODO Auto-generated method stub
		
		Users user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Accounts account = accountsRepository.findById(accountId)
	            .orElseThrow(() -> new RuntimeException("Account not found"));

	    if (!account.getUser().getUserid().equals(userId)) {
	        throw new RuntimeException("Account does not belong to the user");
	    }

	    accountsRepository.delete(account);
		
	}
	

}
