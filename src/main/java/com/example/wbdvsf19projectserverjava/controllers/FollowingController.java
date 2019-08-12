package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.RawUser;
import com.example.wbdvsf19projectserverjava.models.User;

import com.example.wbdvsf19projectserverjava.repositories.UserRepository;
import com.example.wbdvsf19projectserverjava.services.FollowingService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class FollowingController {

    @Autowired
    FollowingService followingService;

	@Autowired
	UserRepository repository;	

	@PostMapping("/api/users/{followerId}/follow/users/{followeeId}")
	public Message followUser(
        @PathVariable("followerId") Integer followerId,
        @PathVariable("followeeId") Integer followeeId) {
        User follower = repository.findUserById(followerId);
        follower.getFollowings().add(repository.findUserById(followeeId));
        repository.save(follower);
        return new Message("User followed");
    }

    @DeleteMapping("/api/users/{followerId}/unfollow/users/{followeeId}")
	public Message unfollowUser(
        @PathVariable("followerId") Integer followerId,
        @PathVariable("followeeId") Integer followeeId) {
        User follower = repository.findUserById(followerId);
        follower.getFollowings().remove(repository.findUserById(followeeId));
        repository.save(follower);
        return new Message("User unfollowed");
    }

    @GetMapping("/api/users/{uid}/followings") 
    public List<RawUser> findAllFollowingsForUser(
            @PathVariable("uid") Integer uid) {
        List<User> users = repository.findAllFollowingUsersById(uid);
        List<RawUser> rawUsers = new ArrayList<>();
        for (User user: users) {
            RawUser rawUser = new RawUser();
            rawUser.set(user);
            rawUsers.add(rawUser);
        }
        return rawUsers;
    }

    @GetMapping("/api/users/{uid}/followers") 
    public List<RawUser> findAllFollowersForUser(
            @PathVariable("uid") Integer uid) {
        List<User> users = repository.findAllFollowerUsersById(uid);
        List<RawUser> rawUsers = new ArrayList<>();
        for (User user: users) {
            RawUser rawUser = new RawUser();
            rawUser.set(user);
            rawUsers.add(rawUser);
        }
        return rawUsers;
    }

    @DeleteMapping("/api/users/{uid}/followings")
    public Message deleteAllFollowingsForUser(
            @PathVariable("uid") Integer id) {
        return followingService.deleteUserFollowings(id);
    }

    @DeleteMapping("/api/users/{uid}/followers")
    public Message deleteAllFollowersForUser(
            @PathVariable("uid") Integer id) {
        return followingService.deleteUserFollowers(id);
    }
}
