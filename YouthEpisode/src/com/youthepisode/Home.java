package com.youthepisode;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Home extends ActionBarActivity {
	

	ArrayList<String> titles;
	ArrayList<String> url;
	ListView lv;
	Button prev;
	int pgno;
	String oldPage;
	
	public void previous (View view) {
	    
	    Intent prevHome = new Intent (Home.this, Home.class);
	    prevHome.putExtra("com.YouthEpisode.Home.result", oldPage);
	    prevHome.putExtra("com.YouthEpisode.Home.pgno", pgno);
	    startActivity(prevHome);
	}
	
	//recursive calling of Home Activity for browsing older posts
	//See: nextURL format below
	private class DownloadWebPage extends AsyncTask<String, Void, String> {
    	protected void onPostExecute(String result) {
    		oldPage=result;
    		prev.setClickable(true);
    		prev.setText("Previous Posts");
    		
    	}

		@Override
		protected String doInBackground(String... urls) {
			try {
				return downloadUrl(urls[0]);
			}	catch (IOException e)	{
				return e.getMessage();
			}
		}	
		
		private String downloadUrl(String myurl) throws IOException {    
		    try {
		        
		       //Jsoup connect
		       
		        Document soupDoc = Jsoup.connect(myurl).maxBodySize(0).timeout(5000).get();
		        return soupDoc.html();
		        
		    } finally {
		        	
		        } 
		    }
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Bundle	loadedPage = getIntent().getExtras();
		String page = loadedPage.getString("com.YouthEpisode.Home.result");
		pgno = loadedPage.getInt("com.YouthEpisode.Home.pgno");
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Home - " +pgno);
		
		
		lv = (ListView) findViewById (R.id.list);
		prev = (Button) findViewById (R.id.bBack);
		titles = new ArrayList<String>();
		url = new ArrayList<String>();
		prev.setClickable(false);
		
				
		//Start loading next page
		pgno++;
		String nextURL = "http://www.youthepisode.com/page/" +pgno +"/";
		new DownloadWebPage().execute(nextURL);
		
		//Parsing begins from loaded page
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
		
		//Output listview
		 ArrayAdapter<String> arrayAdapter =      
		 new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles);
	     lv.setAdapter(arrayAdapter); 
	     
	     //Select action 
	     lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	 public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
	    		 Intent i = new Intent (Home.this, Article.class);
	    		 i.putExtra("com.YouthEpisode.Article.URL", url.get(position));
	    		 startActivity (i);
	    	 }
		});
	}
	
	//Adapter Click Listener
	
	
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
