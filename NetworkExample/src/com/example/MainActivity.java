package com.example;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.network.request.BookLanguageRequest;
import com.example.response.BookLanguageResponse;
import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.NetworkRequest;

public class MainActivity extends Activity implements HttpResponseHandler, OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.reqBtn).setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onNetworkError(NetworkRequest arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {
		if (req instanceof BookLanguageRequest) {
                try {
					BookLanguageResponse response = new BookLanguageResponse(data); 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public void onNetworkResponseProgress(NetworkRequest arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkResponseStart(NetworkRequest arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkResponseStop(NetworkRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		NetworkHandler.getInstance();
		BookLanguageRequest langreq = new BookLanguageRequest(""/*Pass Book Id here*/);
		NetworkHandler.getInstance().addRequest(langreq);
		langreq.setNetworkResponseHandler(this);
		
	}

}
