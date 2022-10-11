package com.cl.foodApp.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cl.foodApp.foodApp.dto.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer>{
	@Modifying
	@Transactional
	@Query("UPDATE FoodOrder SET user_id = NULL WHERE user_id = :id")
	Integer updateOrderWithUserIdNull(@Param("id") int id);
}