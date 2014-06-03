package com.example.twittertest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DisplayDialog extends DialogFragment {
	    
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		 View v = inflater.inflate(R.layout.loading, container, false);
		 
		return v;
	}
	    
	    
	  
	}
