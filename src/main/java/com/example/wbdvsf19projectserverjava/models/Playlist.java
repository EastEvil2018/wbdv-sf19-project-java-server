package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private List<Track> tracks;

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

	public List<Track> getTrackIds() {
		return this.tracks;
	}

	public void setTrackIds(List<Track> tracks) {
		this.tracks = tracks;
	}

	public void set(Playlist newPlaylist) {
        this.name = newPlaylist.name;
	}

}
