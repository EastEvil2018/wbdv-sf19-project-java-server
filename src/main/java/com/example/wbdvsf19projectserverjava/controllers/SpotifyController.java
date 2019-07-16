package com.example.wbdvsf19projectserverjava.controllers;

import java.io.IOException;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvsf19projectserverjava.services.SpotifyService;


@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class SpotifyController {
	
	@Autowired
	SpotifyService spotifyService;
	
	@PostMapping("/api/token/{clientId}")
	public Object requestAccessToken
	(@PathVariable("clientId") String clientId,
	 @RequestBody String clientSecret) throws IOException, OAuthSystemException, OAuthProblemException {
		return spotifyService.requestAccessToken(clientId, clientSecret);
	}
}
