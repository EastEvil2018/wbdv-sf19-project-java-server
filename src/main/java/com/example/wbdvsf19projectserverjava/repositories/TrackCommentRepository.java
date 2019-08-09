package com.example.wbdvsf19projectserverjava.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsf19projectserverjava.models.TrackComment;

public interface TrackCommentRepository 
	extends CrudRepository<TrackComment, Integer> {
	
	@Query("select trackComment from TrackComment trackComment")
	public List<TrackComment> findAllTrackComments();

	@Query("select trackComment from TrackComment trackComment where trackComment.id=:pid")
	public TrackComment findTrackCommentById(@Param("pid") Integer id);
    
    @Query("select trackComment from TrackComment trackComment where trackComment.user.id=:uid")
    public List<TrackComment> findTrackCommentsForUser(@Param("uid") Integer uid);

    @Query("select trackComment from TrackComment trackComment where trackComment.track.id=:tid")
    public List<TrackComment> findTrackCommentsForTrack(@Param("tid") Integer tid);
}