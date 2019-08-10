package com.example.wbdvsf19projectserverjava.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.RawUser;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins="*", maxAge=3600, allowCredentials = "true")
public class UserController {
	@Autowired
	UserRepository userRepository;	

	@PostMapping("/api/auth")
	public Object authenticateUser(
            @RequestBody User authUser,
            HttpSession session) {
        List<User> users = userRepository.findUserByCredentials(authUser.getUsername(), authUser.getPassword());
        if (users.size() != 0) {
            User user = users.get(0);
            user.setPassword("");
            Cookie cookie = new Cookie(user.getUsername(), session.getId());
            cookie.setSecure(true);
            session.setAttribute("user", user);
            return session.getAttribute("user");
        } else {
            Message message = new Message("Login failed");
            return message;
        }
	}
	
	@GetMapping("/api/session/user")
	public Object getCurrentUserIdFromSession(HttpSession session) {
		if (session.getAttribute("user") != null) {
            return session.getAttribute("user");
        } else {
            System.out.println(session.getAttribute("user"));
            Message message = new Message("No user session");
			return message;
        }  
	}

	@PostMapping("/api/users")
	public User createUser(
            @RequestBody RawUser rawUser) {
        User newUser = new User(rawUser);
        userRepository.save(newUser);
        return newUser;
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @GetMapping("/api/users/{uid}")
    public RawUser findUserById(
            @PathVariable("uid") Integer id) {
        User user = userRepository.findUserById(id);
        RawUser rawUser = new RawUser();
        rawUser.set(user);
        return rawUser;
    }

    @PutMapping("/api/users/{uid}")
    public User updateUser(
        @PathVariable("uid") Integer id,
        @RequestBody User newUser) {
        User user = userRepository.findUserById(id);
        user.set(newUser);
        userRepository.save(user);
        return newUser;
    }

    @DeleteMapping("/api/users/{uid}")
    public List<User> deleteUser(
        @PathVariable("uid") Integer id) {
        userRepository.deleteById(id);
        return userRepository.findAllUsers();
    }
}
