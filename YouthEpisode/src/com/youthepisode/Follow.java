package com.youthepisode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class Follow extends ActionBarActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_follow);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(false);
		
		
	}
	public void sendMessageFB(View view) {
	    Uri uri = Uri.parse("https://www.facebook.com/youthepisode");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);
	}
	public void sendMessageLN(View view) {
		Uri uri = Uri.parse("http://www.linkedin.com/company/youthepisode-com");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);    
	}
	public void sendMessageTW(View view) {
		Uri uri = Uri.parse("https://twitter.com/youthepisode");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);    
	}
	public void sendMessagePT(View view) {
		Uri uri = Uri.parse("http://pinterest.com/youthepisode");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);    
	}
	public void sendMessageYT(View view) {
		Uri uri = Uri.parse("http://www.youtube.com/");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);    
	}
	public void sendMessageTL(View view) {
		Uri uri = Uri.parse("http://youthepisode.tumblr.com/");
	    Intent intent = new Intent (Intent.ACTION_VIEW, uri);
	    startActivity(intent);    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.follow, menu);
		return true;
	}
}
