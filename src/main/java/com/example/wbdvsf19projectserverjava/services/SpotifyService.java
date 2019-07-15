package com.example.wbdvsf19projectserverjava.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {
	
	public Object requestAccessToken(String clientId, String clientSecret) throws IOException {
	    String POST_PARAMS = "grant_type=client_credentials";
	    String userCredentials = clientId + ":" + clientSecret;
	    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
	    URL obj = new URL("https://accounts.spotify.com/api/token");
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Type", 
	    		"application/x-www-form-urlencoded");
	    con.setRequestProperty("Authorization",
	            "Basic " + basicAuth);
	    con.setRequestProperty("Accept",
	            "application/json");

	    // For POST only - START
	    con.setDoOutput(true);
	    OutputStream os = con.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    // For POST only - END

	    int responseCode = con.getResponseCode();
	    System.out.println("POST Response Code :: " + responseCode);

	    if (responseCode == HttpURLConnection.HTTP_OK) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	        // print result
	        System.out.println(response.toString());
	        return response;
	    } else {
	        System.out.println("POST request not worked");
	        return con;
	    }
	}
}
