package com.example.wbdvsf19projectserverjava.models;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;

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

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}