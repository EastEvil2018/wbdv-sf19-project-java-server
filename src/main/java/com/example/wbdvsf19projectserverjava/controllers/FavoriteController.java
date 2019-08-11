package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.*;

import com.example.wbdvsf19projectserverjava.repositories.FavoriteRepository;
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
public class FavoriteController {

	@Autowired
    FavoriteRepository favoriteRepository;	
    
    @Autowired
    UserRepository userRepository;	

	@PostMapping("/api/users/{uid}/favorites")
	public List<Favorite> createFavorite(
        @PathVariable("uid") Integer userId,
        @RequestBody Favorite favorite) {
        User user = userRepository.findUserById(userId);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        return favoriteRepository.findFavoritesForUser(userId);
    }

    @GetMapping("/api/favorites") 
    public List<Favorite> findAllFavorites() {
            return favoriteRepository.findAllFavorites();
    }

    @GetMapping("/api/favorites/{lid}") 
    public Favorite findFavoriteById(
            @PathVariable("lid") Integer lid) {
            return favoriteRepository.findFavoriteById(lid);
    }

    @GetMapping("/api/users/{uid}/favorites") 
    public List<Favorite> findAllFavoritesForUser(
        @PathVariable("uid") Integer uid) {
            return favoriteRepository.findFavoritesForUser(uid);
    }

    @GetMapping("/api/products/{type}/{oid}/favorites") 
    public List<Favorite> findAllFavoritesForProduct(
            @PathVariable("oid") String oid,
            @PathVariable("type") ProductType type) {
        
        return favoriteRepository.findFavoritesForProduct(oid, type);
    }

    @DeleteMapping("/api/favorites/{lid}")
	public List<Favorite> deleteFavorite(
            @PathVariable("lid") Integer lid) {
        favoriteRepository.deleteById(lid);
        return favoriteRepository.findAllFavorites();
    }


}
