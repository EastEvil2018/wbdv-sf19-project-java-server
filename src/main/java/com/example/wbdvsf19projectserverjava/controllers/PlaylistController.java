package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Playlist;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.services.UserService;
import com.example.wbdvsf19projectserverjava.repositories.PlaylistRepository;
import com.example.wbdvsf19projectserverjava.repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class PlaylistController {

	@Autowired
	PlaylistRepository repository;	

    @Autowired
    UserRepository userRepository;	
    
    @PostMapping("/api/users/{uid}/playlists")
    public List<Playlist> createPlaylist(
            @PathVariable("uid") Integer userId,
            @RequestBody Playlist newPlaylist) {
        User user = userRepository.findUserById(userId);
        newPlaylist.setUser(user);
        repository.save(newPlaylist);
        return repository.findPlaylistsForUser(userId);
    }

    @GetMapping("/api/playlists")
    public List<Playlist> findAllPlaylists() {
        return repository.findAllPlaylists();
    }

    @GetMapping("/api/playlists/{pid}")
    public Playlist findPlaylistById(
            @PathVariable("pid") Integer pid) {
        return repository.findPlaylistById(pid);
    }

    @GetMapping("/api/users/{uid}/playlists")
    public List<Playlist> findPlaylistsForUser(
            @PathVariable("uid") Integer uid) {
        return repository.findPlaylistsForUser(uid);
    }

    @PutMapping("/api/playlists/{pid}")
    public Playlist updatePlaylist(
        @PathVariable("pid") Integer pid,
        @RequestBody Playlist Playlist) {
        Playlist playlist = repository.findPlaylistById(pid);
        playlist.set(Playlist);
        repository.save(playlist);
        return playlist;
    }

    @DeleteMapping("/api/playlists/{pid}")
    public List<Playlist> deletePlaylist(
        @PathVariable("pid") Integer pid) {
        repository.deleteById(pid);
        return repository.findAllPlaylists();
    }
}
