package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="trackComments")
public class TrackComment extends Comment{
    // @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
	// private int id;

	// private String comment;

	@ManyToOne
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JsonIgnore
	private Track track;

	// public int getId() {
	// 	return this.id;
	// }

	// public void setId(int id) {
	// 	this.id = id;
	// }

	// public String getComment() {
	// 	return this.comment;
	// }

	// public void setComment(String comment) {
	// 	this.comment = comment;
	// }

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Track getTrack() {
		return this.track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public TrackComment(Comment comment) {
		this.setId(comment.getId());
		this.setComment(comment.getComment());
	}

}