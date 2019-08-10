package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.User;

public interface UserRepository 
	extends CrudRepository<User, Integer> {
	
	@Query("select user from User user")
	public List<User> findAllUsers();

	@Query("select user from User user where user.id=:uid")
	public User findUserById(@Param("uid") Integer id);
	
	@Query("select user from User user where user.username=:username")
	public User findUserByUsername(@Param("username") String username);

	@Query("select user.followings from User user where user.id=:uid")
	public List<User> findAllFollowingUsersById(@Param("uid") Integer id);

	@Query("select user.followers from User user where user.id=:uid")
	public List<User> findAllFollowerUsersById(@Param("uid") Integer id);

	@Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
	public List<User> findUserByCredentials
		(@Param("username") String username,
		 @Param("password") String password);
}