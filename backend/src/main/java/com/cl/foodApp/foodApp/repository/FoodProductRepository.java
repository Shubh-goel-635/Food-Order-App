package com.cl.foodApp.foodApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cl.foodApp.foodApp.dto.FoodProduct;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Integer>{

	@Query("SELECT fp FROM FoodProduct fp WHERE menu_id = :menu_id")
	List<FoodProduct> getFoodProductByMenuId(@Param("menu_id") int id);
}