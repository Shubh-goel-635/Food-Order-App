package com.cl.foodApp.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.foodApp.foodApp.dto.FoodProduct;
import com.cl.foodApp.foodApp.repository.FoodProductRepository;

@Repository
public class FoodProductDao {
	@Autowired
    private FoodProductRepository foodProductRepository;
    
	public List<FoodProduct> getAllProduct() {
		return foodProductRepository.findAll();
	}
	
	public Optional<FoodProduct> getFoodProductById(int foodProductId) {
		return foodProductRepository.findById(foodProductId);
	}
	
    public FoodProduct saveProduct(FoodProduct foodProduct) {
        return foodProductRepository.saveAndFlush(foodProduct);
    }
    
    public void deleteProduct(int foodProductid) {
        foodProductRepository.deleteById(foodProductid);
    }
    
    public List<FoodProduct> getFoodProductByMenuId(int menuid){
    	return foodProductRepository.getFoodProductByMenuId(menuid);
    }
}
