package com.example.wbdvsf19projectserverjava.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;



@Service
public class SpotifyService {
	
	public Object requestAccessToken(String clientId, String clientSecret) throws IOException, OAuthSystemException, OAuthProblemException {
	    String POST_PARAMS = "grant_type=client_credentials";
	    String userCredentials = clientId + ":" + clientSecret;
	    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
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
