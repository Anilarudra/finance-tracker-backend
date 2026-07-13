package com.example.financetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budgets")
public class Budgets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String category;
	private Double limitAmount;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userid")	
	@JsonBackReference
	private Users user;
	
	
	public Budgets() {
		super();
	}
	
	public Budgets(Long id, String category, Double limitAmount, Users user) {
		super();
		this.id = id;
		this.category = category;
		this.limitAmount = limitAmount;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getLimitAmount() {
		return limitAmount;
	}
	public void setLimitAmount(Double limitAmount) {
		this.limitAmount = limitAmount;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

}
