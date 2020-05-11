package com.crud.springboot.service;

import com.crud.springboot.model.User;

public interface IUserService {

	User saveOrUpdateUser(User user) throws Exception;
	
	Object findUserById(Long id);
	
	User removeUser(User user) throws Exception;
}
