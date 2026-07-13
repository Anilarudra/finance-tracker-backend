package com.example.financetracker.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transactions")
public class Transactions {
	
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String type;
     private String category;
	 private String note;
	 private Double amount;
	 @Column(name="date")
	 private Date date;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id", referencedColumnName="userid")
	 @JsonIgnoreProperties({"transactions"})
	 private Users user;
	 
	 @ManyToOne
	 @JoinColumn(name="account_id", referencedColumnName = "id")
	 @JsonIgnoreProperties({"transactions","user"})
	 private Accounts account;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getType() {
		 return type;
	 }

	 public void setType(String type) {
		 this.type = type;
	 }

	 public String getCategory() {
		 return category;
	 }

	 public void setCategory(String category) {
		 this.category = category;
	 }

	 public String getNote() {
		 return note;
	 }

	 public void setNote(String note) {
		 this.note = note;
	 }

	 public Double getAmount() {
		 return amount;
	 }

	 public void setAmount(Double amount) {
		 this.amount = amount;
	 }

	 public Date getDate() {
		 return date;
	 }

	 public void setDate(Date date) {
		 this.date = date;
	 }

	 public Users getUser() {
		 return user;
	 }

	 public void setUser(Users user) {
		 this.user = user;
	 }

	 public Accounts getAccount() {
		 return account;
	 }

	 public void setAccount(Accounts account) {
		 this.account = account;
	 }

	 public Transactions(Long id, String type, String category, String note, Double amount, Date date, Users user, Accounts account) {
		super();
		this.id = id;
		this.type = type;
		this.category = category;
		this.note = note;
		this.amount = amount;
		this.date = date;
		this.user = user;
		this.account = account;
	 }

	 public Transactions() {
		 super();
	 }
}
