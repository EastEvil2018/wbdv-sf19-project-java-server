package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.Track;

public interface TrackRepository 
	extends CrudRepository<Track, Integer> {
	
	@Query("select track from Track track")
	public List<Track> findAllTracks();

	@Query("select track from Track track where track.id=:pid")
	public Track findTrackById(@Param("pid") String id);
    
}