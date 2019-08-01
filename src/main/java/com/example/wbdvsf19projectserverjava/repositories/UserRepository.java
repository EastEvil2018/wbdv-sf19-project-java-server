package com.example.wbdvsf19projectserverjava.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.User;

public interface UserRepository 
	extends CrudRepository<User, Integer> {
	
	@Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
	public List<User> findUserByCredentials
		(@Param("username") String username,
		 @Param("password") String password);
}