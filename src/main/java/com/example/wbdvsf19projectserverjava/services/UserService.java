package com.example.wbdvsf19projectserverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
