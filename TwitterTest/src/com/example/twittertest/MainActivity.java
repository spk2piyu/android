package com.example.twittertest;

import twitter4j.auth.RequestToken;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.twittertest.tasks.AuthAsyncTask;
import com.example.twittertest.tasks.FetchUserAsyncTask;
import com.example.twittertest.utils.Defines;
import com.example.twittertest.utils.SessionManager;
import com.example.twittertest.utils.Utils;

public class MainActivity extends FragmentActivity implements OnClickListener,
		TwitterListener {

	private WebView mAuthView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button login = (Button) findViewById(R.id.loginBtn);
		mAuthView = (WebView) findViewById(R.id.webView);
		login.setOnClickListener(this);

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.loginBtn) {
			
			if (!Utils.isNetworkAvailable(this)) {
				
				Toast.makeText(this, "Network not available.",
						Toast.LENGTH_SHORT).show();

			}else{
				
				AuthAsyncTask authTask = new AuthAsyncTask(this);
				authTask.execute();
			}
		}

	}

	private void fetchAuthDetails(RequestToken reqToken, String url) {

		mAuthView.setVisibility(View.GONE);

		String oauth_verifire = Uri.parse(url).getQueryParameter(
				Defines.OAUTH_VERIFIER);
		SessionManager.getInstance().setmAouthVerifier(oauth_verifire);

		System.out.println(oauth_verifire);

		FetchUserAsyncTask task = new FetchUserAsyncTask(getSupportFragmentManager(),this);
		task.execute(reqToken);
	}

	@Override
	public void onAuthSuccessToken(final RequestToken reqToken) {
		String url = reqToken.getAuthenticationURL();
		// startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
		mAuthView.setVisibility(View.VISIBLE);

		mAuthView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				System.out.println(":::" + url);
				if (url != null && url.startsWith("oauth://mytest")) {
					fetchAuthDetails(reqToken, url);
				}
				return super.shouldOverrideUrlLoading(view, url);
			}

		});

		mAuthView.loadUrl(url);

	}

	@Override
	public void onAuthFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDataChange(UserModel model) {

		Intent intent = new Intent();
		intent.setClass(this, UserTimeLineActivity.class);
		intent.putExtra(Defines.TIMELINE_DATA, model.gettList());
		startActivity(intent);
		
		finish();
	}
	
	

}
