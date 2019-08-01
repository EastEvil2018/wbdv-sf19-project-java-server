package com.example.wbdvsf19projectserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;	
	
	public int authenticateUser(User authUser) {
		List<User> users 
			= repository.findUserByCredentials(authUser.getUsername(), authUser.getPassword());
		if (users.size() != 0) {
			return users.get(0).getId();
		} else 
			return -1;
	}
}
