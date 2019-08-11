package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String productId;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createTime;
	private String productName;
	
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
    
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
    
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
    }
    
	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}