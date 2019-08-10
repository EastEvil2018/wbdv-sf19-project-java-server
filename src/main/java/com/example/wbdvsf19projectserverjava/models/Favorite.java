package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

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
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}