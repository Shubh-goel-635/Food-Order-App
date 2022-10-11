package com.cl.foodApp.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cl.foodApp.foodApp.dto.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{
	@Query("SELECT m FROM Menu m WHERE User_id= :User_id")
	Menu getMenuByUserId(@Param("User_id") int id);
}