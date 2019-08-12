package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tracks")
public class Track {
	@Id
	private String id;
    private String name;
    
    @ManyToMany(mappedBy = "tracks", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
    private List<Playlist> playlists;

    // @OneToMany(mappedBy = "track")
    // private List<TrackComment> trackComments;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
	public void set(Track newTrack) {
        this.id = newTrack.id;
        this.name = newTrack.name;
    }
    
	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
    }
    
	// public List<TrackComment> getTrackComments() {
	// 	return this.trackComments;
	// }

	// public void setTrackComments(List<TrackComment> trackComments) {
	// 	this.trackComments = trackComments;
	// }


    

}
