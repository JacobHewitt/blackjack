package com.mycompany.blackjack.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class FBConnection{
	public static final String FB_APP_ID = "1845073082439875";
	public static final String FB_APP_SECRET = "d17e86f26ed1da7046efd1815acd89f4";
	public static final String REDIRECT_URI = "http://localhost:8080/blackjack/";
	private String accessToken = "";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
				+ FBConnection.FB_APP_ID + "&redirect_uri="
				+ FBConnection.REDIRECT_URI
				+ "&scope=email";
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
				+ FBConnection.REDIRECT_URI
				+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if(accessToken.equals("")){
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		accessToken = accessToken.substring(13,accessToken.lastIndexOf("&"));
		System.out.println("ACCESS TOKEN: "+ accessToken);
		return accessToken;
	}
}