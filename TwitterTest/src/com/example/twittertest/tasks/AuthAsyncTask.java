package com.example.twittertest.tasks;

import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;

import com.example.twittertest.TwitterListener;
import com.example.twittertest.utils.SessionManager;

public class AuthAsyncTask extends AsyncTask<Void, Void, RequestToken>{
	
	private TwitterListener listener;
	
	public AuthAsyncTask(TwitterListener listener) {
		
		this.listener = listener;
		
		if(listener== null){
			throw new IllegalAccessError();
		}
	}
	

	@Override
	protected RequestToken doInBackground(Void... params) {
		
	/*	ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(Defines.oAuthAccessToken);
		builder.setOAuthConsumerSecret(Defines.OAuthAccessTokenSecret);
		
		Configuration config = builder.build();
		
		TwitterFactory factory = new TwitterFactory(config);
		Twitter twt = factory.getInstance();*/
		
		RequestToken reqToken = null;
		try {
			 reqToken = SessionManager.getInstance().getTwitterInstance().getOAuthRequestToken("oauth://mytest");
			
		} catch (TwitterException e) {
			
			e.printStackTrace();
			
			listener.onAuthFailed();
		}
		
		return reqToken; 
	}

	@Override
	protected void onPostExecute(RequestToken rt) {
		
		listener.onAuthSuccessToken(rt);
		
		super.onPostExecute(rt);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	
	
}
