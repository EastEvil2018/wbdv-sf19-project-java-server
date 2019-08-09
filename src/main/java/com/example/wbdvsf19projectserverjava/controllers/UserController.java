package com.example.wbdvsf19projectserverjava.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.services.UserService;

import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository repository;	

	@PostMapping("/api/auth")
	public int authenticateUser
	(@RequestBody User user,
	 HttpSession session) {
		int userId = userService.authenticateUser(user);
		if (userId != -1)
			session.setAttribute("userId", userId);
			
		return userId;
	}
	
	@GetMapping("/api/session/user")
	public Object getCurrentUserIdFromSession(HttpSession session) {
		if (session.getAttribute("userId") != null)
			return session.getAttribute("userId");
		else
			return -1;
	}

	@PostMapping("/api/users")
	public List<User> createUser(
        @RequestBody User newUser) {
        repository.save(newUser);
        return repository.findAllUsers();
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    @GetMapping("/api/users/{uid}")
    public User findUserById(
        @PathVariable("uid") Integer id) {
        return repository.findUserById(id);
    }

    @PutMapping("/api/users/{uid}")
    public List<User> updateUser(
        @PathVariable("uid") Integer id,
        @RequestBody User newUser) {
        User user = repository.findUserById(id);
        user.set(newUser);
        repository.save(user);
        return repository.findAllUsers();
    }

    @DeleteMapping("/api/users/{uid}")
    public List<User> deleteUser(
        @PathVariable("uid") Integer id) {
        repository.deleteById(id);
        return repository.findAllUsers();
    }
}
