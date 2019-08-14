package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Notification;
import com.example.wbdvsf19projectserverjava.models.Message;
import com.example.wbdvsf19projectserverjava.models.ProductType;

import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.NotificationRepository;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class NotificationController {

	@Autowired
    NotificationRepository notificationRepository;	
    
    @Autowired
    UserRepository userRepository;	

	@PostMapping("/api/users/{uid}/notifications")
	public List<Notification> createNotification(
        @PathVariable("uid") Integer userId,
        @RequestBody Notification notification) {
        User user = userRepository.findUserById(userId);
        notification.setUser(user);
        notificationRepository.save(notification);
        return notificationRepository.findNotificationsForUser(userId);
    }

    @GetMapping("/api/notifications") 
    public List<Notification> findAllNotifications() {
        return notificationRepository.findAllNotifications();
    }

    @GetMapping("/api/notifications/{nid}") 
    public Notification findNotificationById(
            @PathVariable("nid") Integer nid) {
        return notificationRepository.findNotificationById(nid);
    }

    @GetMapping("/api/users/{uid}/notifications") 
    public List<Notification> findAllNotificationsForUser(
            @PathVariable("uid") Integer uid) {
        return notificationRepository.findNotificationsForUser(uid);
    }

    @GetMapping("/api/notifications/recent/{num}")
    public List<Notification> findMostRecentNotifications(
            @PathVariable("num") int num) {
        Page<Notification> pageable = notificationRepository.findMostRecentNotifications(PageRequest.of(0, num));
        List<Notification> notifications = pageable.getContent();
        return notifications;
    }

    @PutMapping("/api/notifications/{nid}")
    public Notification updateNotification(
            @PathVariable("nid") Integer nid,
            @RequestBody Notification notification){
        Notification oldNotification = notificationRepository.findNotificationById(nid);
        oldNotification.setContent(notification.getContent());
        notificationRepository.save(oldNotification);
        return oldNotification;
        
    }

    @DeleteMapping("/api/notifications/{nid}")
	public Message deleteNotification(
            @PathVariable("nid") Integer nid) {
        notificationRepository.deleteById(nid);
        return new Message("Notification deleted");
    }


}
