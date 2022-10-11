package com.cl.foodApp.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.foodApp.foodApp.dto.FoodOrder;
import com.cl.foodApp.foodApp.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	public List<FoodOrder> getAllFoodOrder(){
		return foodOrderRepository.findAll();
	}
	
	public Optional<FoodOrder> getFoodOrderById(int id){
		return foodOrderRepository.findById(id);
	}
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return foodOrderRepository.save(foodOrder);
	}
	
	public void deleteOrderById(int id) {
		foodOrderRepository.deleteById(id);
	}
	public Integer updateUserIdToNull(int id) {
		return foodOrderRepository.updateOrderWithUserIdNull(id);
	}
}
