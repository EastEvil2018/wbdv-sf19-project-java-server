package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Playlist;

public interface PlaylistRepository 
	extends CrudRepository<Playlist, Integer> {
	
	@Query("select playlist from Playlist playlist")
	public List<Playlist> findAllPlaylists();

	@Query("select playlist from Playlist playlist where playlist.id=:pid")
	public Playlist findPlaylistById(@Param("pid") Integer id);
    
    @Query("select playlist from Playlist playlist where playlist.user.id=:uid")
    public List<Playlist> findPlaylistsForUser(@Param("uid") Integer uid);
}