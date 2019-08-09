package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Comment;
import com.example.wbdvsf19projectserverjava.models.Track;
import com.example.wbdvsf19projectserverjava.models.TrackComment;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.services.UserService;
import com.example.wbdvsf19projectserverjava.repositories.TrackCommentRepository;
import com.example.wbdvsf19projectserverjava.repositories.TrackRepository;
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
public class CommentController {

	@Autowired
    TrackCommentRepository trackCommentRepository;	
    
    @Autowired
    UserRepository userRepository;	
    
    @Autowired
	TrackRepository trackRepository;	

	@PostMapping("/api/users/{uid}/comment/{type}/{id}")
	public List<? extends Comment> createComment(
        @PathVariable("uid") Integer userId,
        @PathVariable("type") String commentType,
        @PathVariable("id") Integer objectId,
        @RequestBody Comment comment) {
        User user = userRepository.findUserById(userId);
        switch(commentType) {
            case "Track":
                Track track = trackRepository.findTrackById(objectId);
                TrackComment trackComment = new TrackComment(comment);
                user.getTrackComments().add(trackComment);
                track.getTrackComments().add(trackComment);
                trackCommentRepository.save(trackComment);
                return trackCommentRepository.findTrackCommentsForUser(userId);
            case "Album":
                break;
            case "Artist":
                break;
            default:
                break;
        }
        return trackCommentRepository.findTrackCommentsForUser(userId);
    }

    // @DeleteMapping("/api/users/{followerId}/unfollow/users/{followeeId}")
	// public List<User> unfollowUser(
    //     @PathVariable("followerId") Integer followerId,
    //     @PathVariable("followeeId") Integer followeeId) {
    //     User follower = repository.findUserById(followerId);
    //     follower.getFollowings().remove(repository.findUserById(followeeId));
    //     repository.save(follower);
    //     return repository.findAllFollowingUsersById(followerId);
    // }

    // @GetMapping("/api/users/{uid}/followings") 
    // public List<User> findAllFollowingUsers(
    //     @PathVariable("uid") Integer uid) {
    //         return repository.findAllFollowingUsersById(uid);
    // }

    // @GetMapping("/api/users/{uid}/followers") 
    // public List<User> findAllFollowerUsers(
    //     @PathVariable("uid") Integer uid) {
    //         return repository.findAllFollowerUsersById(uid);
    // }
}
