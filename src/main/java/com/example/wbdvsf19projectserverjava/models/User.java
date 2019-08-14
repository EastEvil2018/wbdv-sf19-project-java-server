package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

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
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName; 
	@Enumerated(EnumType.STRING)
	private RoleType role;
	private String intro;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] profilePhoto;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createTime;

	@ManyToMany
	@JoinTable(name="following", 
		joinColumns=@JoinColumn(name="followeeId", referencedColumnName = "id"),
		inverseJoinColumns =@JoinColumn(name="followerId", referencedColumnName = "id"))
	private List<User> followings;

	@ManyToMany(mappedBy = "followings")
	@JsonIgnore
	private List<User> followers;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Playlist> playlists;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Favorite> favorites;

	// @PreRemove
	// private void removeFollowingsFromUsers() {
	// 	for (User u: users) {

	// 	}
	// }
	public User() {

	}
	public User(RawUser rawUser) {
		this.username = rawUser.getUsername();
		this.password = rawUser.getPassword();
		this.firstName = rawUser.getFirstName();
		this.lastName = rawUser.getLastName();
		this.role = rawUser.getRole();
		this.intro = rawUser.getIntro();
		byte[] decodedString = rawUser.getprofilePhoto().getBytes();
		this.profilePhoto = decodedString;
	}

	public void set(User newUser) {
		this.username = newUser.username;
		this.password = newUser.password;
		this.firstName = newUser.firstName;
		this.lastName = newUser.lastName;
		this.role = newUser.role;
		this.profilePhoto = newUser.profilePhoto;
	}

	public void set(RawUser rawUser) {
		this.username = rawUser.getUsername();
		this.password = rawUser.getPassword();
		this.firstName = rawUser.getFirstName();
		this.lastName = rawUser.getLastName();
		this.role = rawUser.getRole();
		this.intro = rawUser.getIntro();
		byte[] decodedString = rawUser.getprofilePhoto().getBytes();
		this.profilePhoto = decodedString;
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

	public byte[] getProfilePhoto() {
		return this.profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
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

	
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
