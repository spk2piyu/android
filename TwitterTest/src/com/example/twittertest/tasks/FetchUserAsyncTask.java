package com.example.twittertest.tasks;

import java.util.List;

import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.example.twittertest.TwitterListener;
import com.example.twittertest.UserModel;
import com.example.twittertest.utils.SessionManager;

public class FetchUserAsyncTask extends AsyncTask<RequestToken, Void, UserModel>{
	
	private TwitterListener listener;
	private FragmentManager mFm;
	
	
	public FetchUserAsyncTask(FragmentManager fm,TwitterListener listener) {
		
		this.listener = listener;
		mFm = fm;
		if(listener== null){
			throw new IllegalAccessError();
		}
	}
	
	@Override
	protected void onPreExecute() {
		
		DialogFragment dfm = new DialogFragment();
		dfm.setCancelable(false);
		dfm.show(mFm, "busy");
		super.onPreExecute();
	}

	@Override
	protected UserModel doInBackground(RequestToken... params) {
	
		SessionManager sm = SessionManager.getInstance();
		 UserModel model = null;
		
		 try {
			AccessToken accToken = sm.getTwitterInstance().getOAuthAccessToken(
				params[0],sm.getmAouthVerifier()	
					);
			
		    List<twitter4j.Status> statuses = sm.getTwitterInstance().getUserTimeline();
		    
		     model = new UserModel();
		    
		    User user = sm.getTwitterInstance().showUser(accToken.getUserId());
		    
		    model.setDisplayName(user.getName());
		    
		    for (twitter4j.Status status : statuses) { 
		        
		        model.add(status.getText());
		    }
		
			
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
		return model;
	}

	@Override
	protected void onPostExecute(UserModel result) {
		
		((DialogFragment)mFm.findFragmentByTag("busy")).getDialog().dismiss();
		
		listener.onDataChange(result); 
		super.onPostExecute(result);
	}
	



	
	
}
