package com.youthepisode;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

public class Article extends ActionBarActivity {
	
	TextView tvArticle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		Bundle	loadedPage = getIntent().getExtras();
		String url = loadedPage.getString("com.YouthEpisode.Article.URL");
		ActionBar actionBar = getSupportActionBar();
		tvArticle = (TextView) findViewById (R.id.tvArticle);	
		
		tvArticle.setText(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article, menu);
		return true;
	}

}
