package com.crud.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.springboot.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByStatusOrderByIdAsc(String status);
}
