package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;


public class RawUser {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName; 
	private RoleType role;
	private String profilePhoto;
	private Timestamp createTime;
	private String intro;

	private List<User> followings;
	private List<User> followers;
	private List<Playlist> playlists;
	private List<Comment> comments;
	private List<Favorite> favorites;

	public void set(User newUser) {
		this.id = newUser.getId();
		this.username = newUser.getUsername();
		this.password = newUser.getPassword();
		this.firstName = newUser.getFirstName();
		this.lastName = newUser.getLastName();
		this.role = newUser.getRole();
		this.intro = newUser.getIntro();
		byte[] profilePhotoBytes = newUser.getProfilePhoto();
		this.profilePhoto = new String (profilePhotoBytes);
		this.createTime = newUser.getCreateTime();
		this.followers = newUser.getFollowers();
		this.followings = newUser.getFollowings();
		this.comments = newUser.getComments();
		this.favorites = newUser.getFavorites();
		this.playlists = newUser.getPlaylists();
	}

	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public RoleType getRole() {
		return this.role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getprofilePhoto() {
		return this.profilePhoto;
	}

	public void setprofilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	public List<User> getFollowings() {
		return this.followings;
	}

	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}

	public List<User> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}


}
