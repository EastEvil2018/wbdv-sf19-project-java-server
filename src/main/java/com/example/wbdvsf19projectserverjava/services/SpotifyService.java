package com.example.wbdvsf19projectserverjava.services;

import org.springframework.stereotype.Service;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

@Service
public class SpotifyService {
	
	public Object requestAccessToken(String clientId, String clientSecret) throws OAuthSystemException, OAuthProblemException {
	    String URL = "https://accounts.spotify.com/api/token";
	    OAuthClient client = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest request =
                OAuthClientRequest.tokenLocation(URL)
                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .buildBodyMessage();
      
        String token = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class).getBody();
        System.out.println(token.toString());  
        return token;
	}
}
