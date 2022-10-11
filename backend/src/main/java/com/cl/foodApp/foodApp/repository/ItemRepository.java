package com.cl.foodApp.foodApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cl.foodApp.foodApp.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	@Query("SELECT i FROM Item i WHERE food_order_id = :food_order_id")
	List<Item> getItemByOrderId(@Param("food_order_id") int food_order_id);
}