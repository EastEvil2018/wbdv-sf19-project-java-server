package com.example.wbdvsf19projectserverjava.models;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createTime;

    @ManyToOne
    @JsonIgnore
    private User user;

	@Transient
    public String getUsername() {
        return user.getUsername();
	}
	
	@Transient
    public int getUserId() {
        return user.getId();
	}

    private String objectId;
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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