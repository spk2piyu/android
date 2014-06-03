package com.example.twittertest.utils;


import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class SessionManager {

	private static SessionManager Instance;
	private Twitter mTwitter;
	private String mAouthVerifier;

	public String getmAouthVerifier() {
		return mAouthVerifier;
	}

	public void setmAouthVerifier(String mAouthVerifier) {
		this.mAouthVerifier = mAouthVerifier;
	}

	private SessionManager() {
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(Defines.oAuthAccessToken);
		builder.setOAuthConsumerSecret(Defines.OAuthAccessTokenSecret);
		Configuration config = builder.build();
		
		TwitterFactory factory = new TwitterFactory(config);
		mTwitter = factory.getInstance();
	}
	
	public static SessionManager getInstance(){
		if(Instance == null){
			Instance = new SessionManager();
		}
		return Instance;
	}

	public Twitter getTwitterInstance(){
		
		return mTwitter;
	}
	
	
}
