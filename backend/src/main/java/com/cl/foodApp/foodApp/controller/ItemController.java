package com.cl.foodApp.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.foodApp.foodApp.dto.Item;
import com.cl.foodApp.foodApp.service.ItemService;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@GetMapping("items/{orderid}")
	public ResponseEntity<ResponseStructure<List<Item>>> getItemsByOrderId(@PathVariable int orderid) {
		return itemService.getItemByOrderId(orderid);
	}
	
	@PostMapping("addItem/{orderid}")
	public ResponseEntity<ResponseStructure<List<Item>>>  addItemToMenu(@PathVariable int orderid, @RequestBody List<Item> items) {
		return itemService.addItemToMenu(orderid, items);
	}
}
