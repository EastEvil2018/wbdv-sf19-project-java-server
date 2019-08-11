package com.example.wbdvsf19projectserverjava.controllers;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.wbdvsf19projectserverjava.models.BCrypt;
import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.RawUser;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600, allowCredentials = "true")
public class UserController {
	@Autowired
	UserRepository userRepository;	

	@PostMapping("/api/auth")
	public Object authenticateUser(
            @RequestBody RawUser rawUser,
            HttpSession session) {
        User user = userRepository.findUserByUsername(rawUser.getUsername());
        if (user != null && BCrypt.checkpw(rawUser.getPassword(), user.getPassword())) {
            rawUser.set(user);
            session.setAttribute("user", rawUser);
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
    
    @GetMapping("/api/logout")
    public Message logout(HttpSession session) {
        session.invalidate();
        return new Message("Log out");
    }

	@PostMapping("/api/users")
	public Object createUser(
            @RequestBody RawUser rawUser) {
        User user = userRepository.findUserByUsername(rawUser.getUsername());
        if (user != null) {
            return new Message("Duplicate username");
        } else {
            User newUser = new User(rawUser);
            newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
            userRepository.save(newUser);
            rawUser.set(newUser);
            return rawUser;
        }
        
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
    public RawUser updateUser(
        @PathVariable("uid") Integer id,
        @RequestBody RawUser rawUser) {
        User user = userRepository.findUserById(id);
        user.set(rawUser);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        rawUser.set(user);
        return rawUser;
    }

    @DeleteMapping("/api/users/{uid}")
    public List<User> deleteUser(
        @PathVariable("uid") Integer id) {
        userRepository.deleteById(id);
        return userRepository.findAllUsers();
    }
}
