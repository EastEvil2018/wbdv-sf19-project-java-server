package com.example.wbdvsf19projectserverjava.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Comment;
import com.example.wbdvsf19projectserverjava.models.ProductType;

public interface CommentRepository 
	extends CrudRepository<Comment, Integer> {
	
	@Query("select comment from Comment comment")
	public List<Comment> findAllComments();

	@Query("select comment from Comment comment where comment.id=:pid")
	public Comment findCommentById(@Param("pid") Integer id);
    
    @Query("select comment from Comment comment where comment.user.id=:uid")
    public List<Comment> findCommentsForUser(@Param("uid") Integer uid);

    @Query("select comment from Comment comment where comment.productId=:pid and comment.productType=:type")
	public List<Comment> findCommentsForProduct(@Param("pid") String pid, @Param("type") ProductType type);
	
	@Query("select comment from Comment comment order by comment.createTime desc")
	public Page<Comment> findMostRecentComments(PageRequest pageable);
}