package com.cl.foodApp.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cl.foodApp.foodApp.dto.FoodProduct;
import com.cl.foodApp.foodApp.service.MenuService;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	// this can only be done by manager
	@GetMapping("/addProductToMenu/{managerid}/{productid}")
	public ResponseEntity<ResponseStructure<FoodProduct>> addProductToMenu(@PathVariable int managerid, @PathVariable int productid) {
		return menuService.addProductToMenu(managerid, productid);
	}

	//this can only be done by manager
	@GetMapping("/removeProductFromMenu/{managerid}/{productid}")
	public ResponseEntity<ResponseStructure<FoodProduct>> removeProductToMenu(@PathVariable int managerid, @PathVariable int productid) {
		return menuService.removeProductFromMenu(managerid, productid);
	}

	// this can only be access by manager
	@GetMapping("/menuProductByManagerId/{managerid}")
	public  ResponseEntity<ResponseStructure<List<FoodProduct>>> getMenuProductByManagerId(@PathVariable int managerid){
		return menuService.getMenuFoodProductByManagerId(managerid);
	}
	
	//this can only be access by staff
	@GetMapping("/menuProductByStaffId/{staffid}")
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getMenyById(@PathVariable int staffid) {
		return menuService.getMenuFoodProductByUserrId(staffid);
	}
}
