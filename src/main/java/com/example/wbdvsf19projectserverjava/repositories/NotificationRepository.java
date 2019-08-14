package com.example.wbdvsf19projectserverjava.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Notification;
import com.example.wbdvsf19projectserverjava.models.ProductType;

public interface NotificationRepository 
	extends CrudRepository<Notification, Integer> {
	
	@Query("select notification from Notification notification")
	public List<Notification> findAllNotifications();

	@Query("select notification from Notification notification where notification.id=:pid")
	public Notification findNotificationById(@Param("pid") Integer id);
    
    @Query("select notification from Notification notification where notification.user.id=:uid")
    public List<Notification> findNotificationsForUser(@Param("uid") Integer uid);

	@Query("select notification from Notification notification order by notification.createTime desc")
	public Page<Notification> findMostRecentNotifications(PageRequest pageable);
}