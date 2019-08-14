package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Comment;
import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.ProductType;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.CommentRepository;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class CommentController {

	@Autowired
    CommentRepository commentRepository;	
    
    @Autowired
    UserRepository userRepository;	

	@PostMapping("/api/users/{uid}/comments")
	public List<Comment> createComment(
        @PathVariable("uid") Integer userId,
        @RequestBody Comment comment) {
        User user = userRepository.findUserById(userId);
        comment.setUser(user);
        commentRepository.save(comment);
        return commentRepository.findCommentsForUser(userId);
    }

    @GetMapping("/api/comments") 
    public List<Comment> findAllComments() {
            return commentRepository.findAllComments();
    }

    @GetMapping("/api/comments/{cid}") 
    public Comment findCommentById(
            @PathVariable("cid") Integer cid) {
            return commentRepository.findCommentById(cid);
    }

    @GetMapping("/api/users/{uid}/comments") 
    public List<Comment> findAllCommentsForUser(
            @PathVariable("uid") Integer uid) {
        return commentRepository.findCommentsForUser(uid);
    }

    @GetMapping("/api/products/{type}/{oid}/comments") 
    public List<Comment> findAllCommentsForProduct(
            @PathVariable("oid") String oid,
            @PathVariable("type") ProductType type) {
        return commentRepository.findCommentsForProduct(oid, type);
    }

    @GetMapping("/api/comments/recent/{num}")
    public List<Comment> findMostRecentComments(
            @PathVariable("num") int num) {
        Page<Comment> pageable = commentRepository.findMostRecentComments(PageRequest.of(0, num));
        List<Comment> comments = pageable.getContent();
        return comments;
    }

    @PutMapping("/api/comments/{cid}")
    public Comment updateComment(
            @PathVariable("cid") Integer cid,
            @RequestBody Comment comment){
        Comment oldComment = commentRepository.findCommentById(cid);
        oldComment.setComment(comment.getComment());
        commentRepository.save(oldComment);
        return commentRepository.findCommentById(cid);
        
    }

    @DeleteMapping("/api/comments/{cid}")
	public Message deleteComment(
            @PathVariable("cid") Integer cid) {
        commentRepository.deleteById(cid);
        return new Message("Comment deleted");
    }


}
