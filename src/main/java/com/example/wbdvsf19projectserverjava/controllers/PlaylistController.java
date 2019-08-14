package com.example.wbdvsf19projectserverjava.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.wbdvsf19projectserverjava.models.Playlist;
import com.example.wbdvsf19projectserverjava.models.Track;
import com.example.wbdvsf19projectserverjava.models.User;
import com.example.wbdvsf19projectserverjava.repositories.PlaylistRepository;
import com.example.wbdvsf19projectserverjava.repositories.TrackRepository;
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
	PlaylistRepository playlistRepository;	

    @Autowired
    UserRepository userRepository;	

    @Autowired
    TrackRepository trackRepository;	
    
    @PostMapping("/api/users/{uid}/playlists")
    public List<Playlist> createPlaylist(
            @PathVariable("uid") Integer userId,
            @RequestBody Playlist newPlaylist) {
        User user = userRepository.findUserById(userId);
        newPlaylist.setUser(user);
        playlistRepository.save(newPlaylist);
        return playlistRepository.findPlaylistsForUser(userId);
    }

    @GetMapping("/api/playlists")
    public List<Playlist> findAllPlaylists() {
        return playlistRepository.findAllPlaylists();
    }

    @GetMapping("/api/playlists/{pid}")
    public Playlist findPlaylistById(
            @PathVariable("pid") Integer pid) {
        return playlistRepository.findPlaylistById(pid);
    }

    @GetMapping("/api/users/{uid}/playlists")
    public List<Playlist> findPlaylistsForUser(
            @PathVariable("uid") Integer uid) {
        return playlistRepository.findPlaylistsForUser(uid);
    }

    @PutMapping("/api/playlists/{pid}")
    public Playlist updatePlaylist(
        @PathVariable("pid") Integer pid,
        @RequestBody Playlist Playlist) {
        Playlist playlist = playlistRepository.findPlaylistById(pid);
        playlist.set(Playlist);
        playlistRepository.save(playlist);
        return playlist;
    }

    @PutMapping("/api/playlists/{pid}/add")
    public Playlist addToPlaylist(
        @PathVariable("pid") Integer pid,
        @RequestBody Track track) {
        Playlist playlist = playlistRepository.findPlaylistById(pid);
        trackRepository.save(track);
        playlist.getTracks().add(track);
        playlistRepository.save(playlist);
        return playlist;
    }

    @PutMapping("/api/playlists/{pid}/delete")
    public Playlist deleteTrackFromPlaylist(
        @PathVariable("pid") Integer pid,
        @RequestBody Track track) {
        Playlist playlist = playlistRepository.findPlaylistById(pid);
        List<Track> tracks = playlist.getTracks();
        for (Track tra: tracks) {
            if (tra.getId().equals(track.getId())) {
                tracks.remove(tra);
            }
        }
        playlistRepository.save(playlist);
        return playlist;
    }

    @DeleteMapping("/api/playlists/{pid}")
    public List<Playlist> deletePlaylist(
            @PathVariable("pid") Integer pid) {
        playlistRepository.deleteById(pid);
        return playlistRepository.findAllPlaylists();
    }
}
