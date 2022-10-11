package com.cl.foodApp.foodApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.foodApp.foodApp.dto.Menu;
import com.cl.foodApp.foodApp.repository.MenuRepository;
@Repository
public class MenuDao {
	@Autowired
	private MenuRepository menuRepository;
	
	public Menu createMenu(Menu menu) {
		return menuRepository.save(menu);
	}
	
	public Menu getMenuByUserId(int id) {
		return menuRepository.getMenuByUserId(id);
	}

}
