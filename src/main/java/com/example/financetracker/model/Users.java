package com.example.financetracker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long userid;
	  private String name;
	  
	  @Column(unique = true)
	  private String email;
	  private String password;
	  
	  
	  @OneToMany(mappedBy="user")
	  @JsonIgnoreProperties({"user","account"})
	  private List<Transactions> transactions;
	  
	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	  @JsonIgnoreProperties({"user","transactions"})
	  
	  private List<Accounts> accounts;

	  
	  public Long getUserid() {
		  return userid;
	  }
	  public void setUserid(Long userid) {
		  this.userid = userid;
	  }
	  public String getName() {
		  return name;
	  }
	  public void setName(String name) {
		  this.name = name;
	  }
	  public String getEmail() {
		  return email;
	  }
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  public String getPassword() {
		  return password;
	  }
	  public void setPassword(String password) {
		  this.password = password;
	  }
	  
	  public List<Transactions> getTransactions() {
			return transactions;
	  }
		  
	  public void setTransactions(List<Transactions> transactions) {
			  this.transactions = transactions;
		  
	  }
	  
	  public List<Accounts> getAccounts() {
			return accounts;
	  }
		  
	  public void setAccounts(List<Accounts> accounts) {
			  this.accounts = accounts;
		  
	  }
	  
	  public Users(String name, String email, String password, List<Transactions> transactions, List<Accounts> accounts) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.transactions = transactions;
		this.accounts = accounts;
	  }
	  
	  public Users() {
		super();
	  }
	    
	  
}

