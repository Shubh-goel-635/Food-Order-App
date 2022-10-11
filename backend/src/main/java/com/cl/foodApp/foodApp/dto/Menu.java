package com.cl.foodApp.foodApp.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FoodProduct> foodProduct;

	@OneToOne
	@JoinColumn
	@JsonIgnore
	private User user;

	
	public int getId() {
		return id;
	}

	public List<FoodProduct> getFoodProduct() {
		return foodProduct;
	}

	public void setFoodProduct(List<FoodProduct> foodProduct) {
		this.foodProduct = foodProduct;
	}

	public void addFoodProduct(FoodProduct foodProduct) {
		this.foodProduct.add(foodProduct);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
