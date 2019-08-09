package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

enum RoleType {
	USER, ADMIN;
}

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName; 
	@Enumerated(EnumType.STRING)
	private RoleType role;
	private String profilePhoto;

	@ManyToMany
	@JoinTable(name="following", 
		joinColumns=@JoinColumn(name="followeeId", referencedColumnName = "id"),
		inverseJoinColumns =@JoinColumn(name="followerId", referencedColumnName = "id"))
	private List<User> followings;

	@ManyToMany(mappedBy = "followings")
	@JsonIgnore
	private List<User> followers;

	@OneToMany(mappedBy = "user")
	private List<Playlist> playlists;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	public void set(User newUser) {
		this.username = newUser.username;
		this.password = newUser.password;
		this.firstName = newUser.firstName;
		this.lastName = newUser.lastName;
		this.role = newUser.role;
		this.profilePhoto = newUser.profilePhoto;
	}

	
	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
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

	
	public RoleType getRole() {
		return this.role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getProfilePhoto() {
		return this.profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
