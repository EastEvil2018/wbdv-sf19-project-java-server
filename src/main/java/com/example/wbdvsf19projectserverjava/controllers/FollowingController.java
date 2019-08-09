package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

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
public class FollowingController {

	@Autowired
	UserRepository repository;	

	@PostMapping("/api/users/{followerId}/follow/users/{followeeId}")
	public List<User> followUser(
        @PathVariable("followerId") Integer followerId,
        @PathVariable("followeeId") Integer followeeId) {
        User follower = repository.findUserById(followerId);
        follower.getFollowings().add(repository.findUserById(followeeId));
        repository.save(follower);
        return repository.findAllFollowingUsersById(followerId);
    }

    @DeleteMapping("/api/users/{followerId}/unfollow/users/{followeeId}")
	public List<User> unfollowUser(
        @PathVariable("followerId") Integer followerId,
        @PathVariable("followeeId") Integer followeeId) {
        User follower = repository.findUserById(followerId);
        follower.getFollowings().remove(repository.findUserById(followeeId));
        repository.save(follower);
        return repository.findAllFollowingUsersById(followerId);
    }

    @GetMapping("/api/users/{uid}/followings") 
    public List<User> findAllFollowingUsers(
        @PathVariable("uid") Integer uid) {
            return repository.findAllFollowingUsersById(uid);
    }

    @GetMapping("/api/users/{uid}/followers") 
    public List<User> findAllFollowerUsers(
        @PathVariable("uid") Integer uid) {
            return repository.findAllFollowerUsersById(uid);
    }
}
