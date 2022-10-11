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

import com.cl.foodApp.foodApp.dto.User;
import com.cl.foodApp.foodApp.service.UserService;
import com.cl.foodApp.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/manager")
	public ResponseEntity<ResponseStructure<User>> saveManager(@RequestBody User manager) {
		return userService.createManager(manager);
	}
	
	@PostMapping(value = "/createStaff/{managerid}")
	public ResponseEntity<ResponseStructure<User>> createStaff(@PathVariable int managerid, @RequestBody User user) {
		return userService.createStaff(managerid, user);
	}
	
	//this is for our need
	@GetMapping(value = "/user")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		return userService.getAllUser();
	}
	
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(value = "/getAllStaff")
	public  ResponseEntity<ResponseStructure<List<User>>> getAllStaff() {
		return userService.getAllStaff();
	}
	
	@PutMapping(value = "/user/{userid}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userid, @RequestBody User user) {
		return userService.updateUser(userid, user);
	}
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
		return userService.deleteUserById(id);
	}
	
	//login authentication
	@PostMapping(value = "/login")
	public ResponseEntity<ResponseStructure<User>> loginAuth(@RequestBody User user) {
		return userService.loginAuth(user);
	}
}
