package com.example.wbdvsf19projectserverjava.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.services.UserService;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {
	@Autowired
	UserService userService;
	
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
	public Object getCurrentUserFromSession(HttpSession session) {
		if (session.getAttribute("userId") != null)
			return session.getAttribute("userId");
		else
			return -1;
	}
}
