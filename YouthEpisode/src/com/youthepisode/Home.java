package com.youthepisode;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends ActionBarActivity {
	
	EditText etHome;
	ArrayList<String> titles;
	ArrayList<String> url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		etHome = (EditText) findViewById(R.id.etHome);
		Bundle	loadedPage = getIntent().getExtras();
		String page = loadedPage.getString("com.YouthEpisode.Home.result");
		Document doc = Jsoup.parse(page);
		Element e = doc.getElementById("content");
		
		for (int i=0; i<=14; i=i+2)
		{	String str = e.child(i).child(0).html();
		
			//Logic to find Title from parsed JSoup
			int aterm = str.indexOf(">");
			int titlei = str.indexOf("title");
			String  title= str.substring(titlei+20, aterm-1);
			
			
			//Logic to find permlink from parsed JSoup
			int hrefi = str.indexOf("href=");
			String link = str.substring(hrefi+6, titlei-2);
			
			titles.add(title);
			url.add(link);
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// // Handle presses on the action bar items
		switch (item.getItemId())	{
		case R.id.follow_us:
			startActivity (new Intent (this, Follow.class));
			return true;
		default:
		return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
