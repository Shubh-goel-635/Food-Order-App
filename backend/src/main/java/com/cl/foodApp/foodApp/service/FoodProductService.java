package com.cl.foodApp.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.foodApp.foodApp.dao.FoodProductDao;
import com.cl.foodApp.foodApp.dto.FoodProduct;
import com.cl.foodApp.foodApp.util.ResponseStructure;
@Service
public class FoodProductService {
	@Autowired
    private FoodProductDao foodProductDao;
    
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getAllProducts() {
		List<FoodProduct> foodProducts = foodProductDao.getAllProduct();
		ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(foodProducts);
		if (foodProducts == null) {
			responseStructure.setError(true);
			responseStructure.setMessage("no food products found");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>> (responseStructure, HttpStatus.OK);
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("list of all food products");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>> (responseStructure, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<ResponseStructure<FoodProduct>> getFoodProductById(int foodProductId) {
		Optional<FoodProduct> found = foodProductDao.getFoodProductById(foodProductId);
		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
		if (found.isPresent()) {
			responseStructure.setError(false);
			responseStructure.setMessage("data found");
			responseStructure.setData(found.get());
			return new ResponseEntity<ResponseStructure<FoodProduct>> (responseStructure, HttpStatus.OK);
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("no data found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<FoodProduct>> (responseStructure, HttpStatus.NO_CONTENT);
		}
	}
	
    public ResponseEntity<ResponseStructure<FoodProduct>> saveProduct(FoodProduct foodProduct) {
    	ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
    	responseStructure.setError(false);
    	responseStructure.setMessage("food product saved");
    	responseStructure.setData(foodProductDao.saveProduct(foodProduct));
        return new ResponseEntity<ResponseStructure<FoodProduct>> (responseStructure, HttpStatus.OK);
    }
    
    public ResponseEntity<ResponseStructure<FoodProduct>> updateProduct(int foodProductId, FoodProduct foodProduct) {
    	ResponseStructure<FoodProduct> existiFoodProduct = this.getFoodProductById(foodProductId).getBody();
    	if(existiFoodProduct.getData() != null) {
    		FoodProduct oldFoodProduct = existiFoodProduct.getData();
    		BeanUtils.copyProperties(foodProduct, oldFoodProduct, "id", "menu");
    		
    		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
    		responseStructure.setError(false);
    		responseStructure.setMessage("food product updated succesfully");
    		responseStructure.setData(foodProductDao.saveProduct(oldFoodProduct));
    		return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.OK);
    	}
    	else {
    		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
    		responseStructure.setError(false);
    		responseStructure.setMessage("food product not available to update");
    		responseStructure.setData(null);
    		return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.NO_CONTENT);
    	}
    }
    
    public ResponseEntity<ResponseStructure<String>> deleteProduct(int foodProductId) {
    	ResponseStructure<String> responseStructure = new ResponseStructure<>();
    	foodProductDao.deleteProduct(foodProductId);
    	responseStructure.setError(false);
    	responseStructure.setMessage("food product deleted");
    	responseStructure.setData("food product with "+ foodProductId + " deleted");
        return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
    }
}
