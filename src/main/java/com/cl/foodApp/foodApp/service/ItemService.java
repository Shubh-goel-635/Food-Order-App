package com.cl.foodApp.foodApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.foodApp.foodApp.dao.FoodOrderDao;
import com.cl.foodApp.foodApp.dao.ItemDao;
import com.cl.foodApp.foodApp.dto.FoodOrder;
import com.cl.foodApp.foodApp.dto.Item;
import com.cl.foodApp.foodApp.javaMail.Mail;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@Service
public class ItemService {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private FoodOrderDao foodOrderDao;
	@Autowired
	private Mail mail;
	
	public ResponseEntity<ResponseStructure<List<Item>>> addItemToMenu(int orderid, List<Item> items) {
		FoodOrder foodOrder = foodOrderDao.getFoodOrderById(orderid).get();
		
		for (Item item : items) {
			item.setFoodOrder(foodOrder);
			itemDao.saveItem(item);
		}
		mail.createOrderMail(orderid, items);
		ResponseStructure<List<Item>> responseStructure = new ResponseStructure<>();
		responseStructure.setError(false);
		responseStructure.setMessage("Products added to order");
		responseStructure.setData(items);
		
		return new ResponseEntity<ResponseStructure<List<Item>>>(responseStructure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Item>>> getItemByOrderId(int orderid) {
		List<Item> items = itemDao.getAllItemWithOrderId(orderid);
		
		if (items.isEmpty()) {
			ResponseStructure<List<Item>> responseStructure = new ResponseStructure<>();
			responseStructure.setError(true);
			responseStructure.setMessage("no items link with this order");
			responseStructure.setData(items);
			
			return new ResponseEntity<ResponseStructure<List<Item>>>(responseStructure, HttpStatus.OK);
		}
		else {
			ResponseStructure<List<Item>> responseStructure = new ResponseStructure<>();
			responseStructure.setError(false);
			responseStructure.setMessage("list of products");
			responseStructure.setData(items);
			
			return new ResponseEntity<ResponseStructure<List<Item>>>(responseStructure, HttpStatus.OK);
		}
	}
}
