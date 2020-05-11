package com.crud.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springboot.model.User;
import com.crud.springboot.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveOrUpdateUser(User user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public Object findUserById(Long id){
		if (id != (long) 0) {
			return userRepository.findById(id);
		}else {
			return userRepository.findByStatusOrderByIdAsc("Y");
		}
	}

	@Override
	public User removeUser(User user) throws Exception{
		return userRepository.save(user);
	}

}
