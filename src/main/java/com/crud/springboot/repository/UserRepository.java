package com.crud.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crud.springboot.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByStatusOrderByIdAsc(String status);
}
