package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Favorite;

public interface FavoriteRepository 
	extends CrudRepository<Favorite, Integer> {
	
	@Query("select favorite from Favorite favorite")
	public List<Favorite> findAllFavorites();

	@Query("select favorite from Favorite favorite where favorite.id=:pid")
	public Favorite findFavoriteById(@Param("pid") Integer id);
    
    @Query("select favorite from Favorite favorite where favorite.user.id=:uid")
    public List<Favorite> findFavoritesForUser(@Param("uid") Integer uid);

    @Query("select favorite from Favorite favorite where favorite.objectId=:oid")
    public List<Favorite> findFavoritesForObject(@Param("oid") String oid);
}