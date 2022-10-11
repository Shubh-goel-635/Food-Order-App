package com.cl.foodApp.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.foodApp.foodApp.dao.FoodProductDao;
import com.cl.foodApp.foodApp.dao.MenuDao;
import com.cl.foodApp.foodApp.dao.UserDao;
import com.cl.foodApp.foodApp.dto.FoodProduct;
import com.cl.foodApp.foodApp.dto.Menu;
import com.cl.foodApp.foodApp.dto.User;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private FoodProductDao foodProductDao;
	
	@Autowired
	private UserDao userDao;
	
	public Menu getMenuByUserId(int id){
		return menuDao.getMenuByUserId(id);
	}
	
	// add product to menu from food products
	public ResponseEntity<ResponseStructure<FoodProduct>> addProductToMenu(int managerid, int productid) {
		Menu existingMenu = this.getMenuByUserId(managerid);
		Optional<FoodProduct> found = foodProductDao.getFoodProductById(productid);
		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
		if (found.isPresent()) {
			FoodProduct existingFoodProduct = found.get();
			existingFoodProduct.setMenu(existingMenu);

			responseStructure.setError(false);
			responseStructure.setMessage("food product added to menu");
			responseStructure.setData(foodProductDao.saveProduct(existingFoodProduct));
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.OK);
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("no food product found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.NO_CONTENT);			
		}
	}
	
	// remove product from menu
	public ResponseEntity<ResponseStructure<FoodProduct>> removeProductFromMenu(int userid, int productid) {
		Optional<FoodProduct> found = foodProductDao.getFoodProductById(productid);
		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
		if (found.isPresent()) {
			FoodProduct existingFoodProduct = found.get();
			existingFoodProduct.setMenu(null);

			responseStructure.setError(false);
			responseStructure.setMessage("food product removed from menu");
			responseStructure.setData(foodProductDao.saveProduct(existingFoodProduct));
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.OK);
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("no food product found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.NO_CONTENT);			
		}
	}
	
	public  ResponseEntity<ResponseStructure<List<FoodProduct>>> getMenuFoodProductByManagerId(int managerid){
		int existingMenuId = menuDao.getMenuByUserId(managerid).getId();
		List<FoodProduct> menuList = foodProductDao.getFoodProductByMenuId(existingMenuId);
		ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(menuList);
		if (menuList.isEmpty()) {
			responseStructure.setError(true);
			responseStructure.setMessage("no food products are in menu");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.OK);	
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("list of food product in menu");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.OK);			
		}
		
	}

	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getMenuFoodProductByUserrId(int userid){
		Optional<User> found = userDao.getUserById(userid);
		ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<>();
		if (found.isPresent()) {
			int menuid = found.get().getMenu().getId();

			responseStructure.setError(false);
			responseStructure.setMessage("list of food product in menu");
			responseStructure.setData(foodProductDao.getFoodProductByMenuId(menuid));
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.OK);
		}
		else {
			responseStructure.setError(false);
			responseStructure.setMessage("no food product found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.NO_CONTENT);			
		}
	}
}
