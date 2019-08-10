package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name="playlists")
public class Playlist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToOne
    @JsonIgnore
	private User user;

	@ManyToMany
	@JoinTable(name="AddToPlaylist", 
		joinColumns=@JoinColumn(name="playlistId", referencedColumnName = "id"),
		inverseJoinColumns =@JoinColumn(name="trackId", referencedColumnName = "id"))
	private List<Track> tracks;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createTime;
	
	@Transient
    public String getUsername() {
        return user.getUsername();
	}
	
	@Transient
    public int getUserId() {
        return user.getId();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Track> getTracks() {
		return this.tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public void set(Playlist newPlaylist) {
        this.name = newPlaylist.name;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
