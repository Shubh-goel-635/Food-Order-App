package com.cl.foodApp.foodApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.foodApp.foodApp.dao.FoodOrderDao;
import com.cl.foodApp.foodApp.dao.UserDao;
import com.cl.foodApp.foodApp.dto.FoodOrder;
import com.cl.foodApp.foodApp.dto.User;
import com.cl.foodApp.foodApp.javaMail.Mail;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@Service
public class FoodOrderService {
	@Autowired
	private FoodOrderDao foodOrderDao;	
	@Autowired
	private UserDao userDao;
	@Autowired
	private Mail mail;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> createOrder(int staffid, FoodOrder foodOrder) {
		LocalDateTime orderCreatedTime = LocalDateTime.now();
		LocalDateTime orderDeliveryTime = LocalDateTime.now().plusMinutes(30);
		
		User staff = userDao.getUserById(staffid).get();
		foodOrder.setStatus("received");
		foodOrder.setUser(staff);
		foodOrder.setOrderCreatedTime(orderCreatedTime);
		foodOrder.setOrderDeliveryTime(orderDeliveryTime);
		
		
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
		responseStructure.setError(false);
		responseStructure.setMessage("food order initialized");
		responseStructure.setData(foodOrderDao.saveFoodOrder(foodOrder));
		
		return new ResponseEntity<ResponseStructure<FoodOrder>> (responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>> updateOrderStatus(FoodOrder foodOrder) {
		FoodOrder existinFoodOrder = foodOrderDao.getFoodOrderById(foodOrder.getId()).get();
		
		BeanUtils.copyProperties(foodOrder, existinFoodOrder, "id", "user", "orderCreatedTime", "orderDeliveryTime", "totalPrice", "items");
		FoodOrder savedFoodORder = foodOrderDao.saveFoodOrder(existinFoodOrder);
		
		mail.updateOrderMail(savedFoodORder);
		
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
		responseStructure.setError(false);
		responseStructure.setMessage("food order updated");
		responseStructure.setData(savedFoodORder);
		
		return new ResponseEntity<ResponseStructure<FoodOrder>> (responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getAllFoodOrder() {
		List<FoodOrder> foodOrders = foodOrderDao.getAllFoodOrder();
		ResponseStructure<List<FoodOrder>> responseStructure = new ResponseStructure<>();
		responseStructure.setError(false);
		responseStructure.setMessage("List of all orders");
		responseStructure.setData(foodOrders);
		
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(responseStructure, HttpStatus.OK);
	}
	
	public FoodOrder getFoodOrderById(int orderid) {
		return foodOrderDao.getFoodOrderById(orderid).get();
	}
	
}
