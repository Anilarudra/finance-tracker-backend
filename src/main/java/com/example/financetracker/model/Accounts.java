package com.example.financetracker.model;



import java.util.List;

import com.example.financetracker.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String accountNumber;
	private String bankName;
	private Double balance;
	private AccountStatus status;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="userid")
	@JsonIgnoreProperties({"transactions"})
	private Users user;
	
	@OneToMany(mappedBy="account")
	@JsonIgnoreProperties({"account","user"})
	private List<Transactions> transactions;
	
	public List<Transactions> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Accounts(Long id, String accountNumber, String bankName, Double balance, AccountStatus status,
			Users user, List<Transactions> transactions) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		
		this.bankName = bankName;
		this.balance = balance;
		this.status = status;
		this.user = user;
		this.transactions = transactions;
	}

	

	public Accounts() {
		super();
	}
	
	
}
