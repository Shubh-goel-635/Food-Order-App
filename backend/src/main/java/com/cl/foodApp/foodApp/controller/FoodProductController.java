package com.cl.foodApp.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.foodApp.foodApp.dto.FoodProduct;
import com.cl.foodApp.foodApp.service.FoodProductService;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin
public class FoodProductController {
	@Autowired
    private FoodProductService foodProductService;
    
	@GetMapping("/foodProduct")
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getAllFoodProduct() {
		return foodProductService.getAllProducts();
	}
	
	@GetMapping("/foodProduct/{id}")
	public ResponseEntity<ResponseStructure<FoodProduct>> getFoodProductById(@PathVariable int id) {
		return foodProductService.getFoodProductById(id);
	}
	
    @PostMapping("/foodProduct")
    public ResponseEntity<ResponseStructure<FoodProduct>> saveProduct(@RequestBody FoodProduct foodProduct ) {
        return foodProductService.saveProduct(foodProduct);
    }
    
    @PutMapping("/foodProduct/{id}")
    public ResponseEntity<ResponseStructure<FoodProduct>> updateProduct(@PathVariable int id,  @RequestBody FoodProduct foodProduct) {
        return foodProductService.updateProduct(id, foodProduct);
    }
    
    @DeleteMapping("/foodProduct/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
        return foodProductService.deleteProduct(id);
    }
}
