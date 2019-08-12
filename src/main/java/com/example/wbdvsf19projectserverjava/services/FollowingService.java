package com.example.wbdvsf19projectserverjava.services;

import java.util.List;

import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FollowingService {
    @Autowired
    UserRepository userRepository;

    public Message deleteUserFollowings(Integer id) {
        User user = userRepository.findUserById(id);
        user.setFollowings(null);
        userRepository.save(user);
        return new Message("User " + id.toString() + " followings deleted");
    }

    public Message deleteUserFollowers(Integer id) {
        User user = userRepository.findUserById(id);
        List<User> followers = user.getFollowers();
        for (User follower: followers) {
            follower.getFollowings().remove(user);
        }
        userRepository.saveAll(followers);
        return new Message("User " + id.toString() + " followers deleted");
    }

}