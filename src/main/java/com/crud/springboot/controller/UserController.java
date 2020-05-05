package com.crud.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springboot.model.ResponseMessage;
import com.crud.springboot.model.User;
import com.crud.springboot.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;

	/**
	 * This is used to save the user
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/customer/save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		try {
			userRepo.save(user);
			return new ResponseEntity(new ResponseMessage("Success"), HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage("Failed"), HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	/**
	 * This is used to retreve users
	 * If id is pass as 0 then it will return all active user list and
	 * If id is pass as specific one then it will return specific user only
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/cutomer/filter/{id}")
	public Object getUser(@PathVariable("id") Long id) {
		if (id != (long) 0) {
			return userRepo.findById(id);
		}else {
			return userRepo.findByStatus("Y");
		}
	}
	
	/**
	 * This is used to update user
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/customer/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		try {
			userRepo.save(user);
			return new ResponseEntity(new ResponseMessage("Success"), HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage("Failed"), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * This is used to remove user
	 * This will update user status as "N" if user is removed
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/customer/remove")
	public ResponseEntity<?> removeUser(@RequestBody User user) {
		try {
			userRepo.save(user);
			return new ResponseEntity(new ResponseMessage("Success"), HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage("Failed"), HttpStatus.EXPECTATION_FAILED);
		}
	}

}