package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

enum ObjectType {
    TRACK, ARTIST, ALBUM;
}
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String objectId;
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
    
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
    }
    
	public ObjectType getObjectType() {
		return this.objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
    }

}