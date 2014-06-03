package com.example.twittertest;

import twitter4j.auth.RequestToken;


public interface TwitterListener {

	public void onAuthSuccessToken(RequestToken authUrl);
	public void onAuthFailed();
	
	public void onDataChange(UserModel model);
	
}
