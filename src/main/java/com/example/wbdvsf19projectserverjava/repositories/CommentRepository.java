package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Comment;

public interface CommentRepository 
	extends CrudRepository<Comment, Integer> {
	
	@Query("select comment from Comment comment")
	public List<Comment> findAllComments();

	@Query("select comment from Comment comment where comment.id=:pid")
	public Comment findCommentById(@Param("pid") Integer id);
    
    @Query("select comment from Comment comment where comment.user.id=:uid")
    public List<Comment> findCommentsForUser(@Param("uid") Integer uid);

    @Query("select comment from Comment comment where comment.objectId=:oid")
    public List<Comment> findCommentsForObject(@Param("oid") String oid);
}