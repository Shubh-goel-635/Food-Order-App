package com.cl.foodApp.foodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.foodApp.foodApp.dto.Item;
import com.cl.foodApp.foodApp.repository.ItemRepository;

@Repository
public class ItemDao {
	@Autowired
	private ItemRepository itemRepository;
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public List<Item> getAllItemWithOrderId(int orderid){
		return itemRepository.getItemByOrderId(orderid);
	}
}
