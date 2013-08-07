package com.youthepisode;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Article extends ActionBarActivity {
	
	TextView tvArticle, tvAuthor;
	ProgressBar pbArticle;
	String ArticleTitle;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		Bundle	loadedPage = getIntent().getExtras();
		String url = loadedPage.getString("com.YouthEpisode.Article.URL");
		
		actionBar = getSupportActionBar();
		tvArticle = (TextView) findViewById (R.id.tvArticle);	
		tvAuthor = (TextView) findViewById (R.id.tvAuthor);
		pbArticle = (ProgressBar) findViewById (R.id.pbArticle);
		
		new DownloadWebPage().execute(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.article, menu);
		return true;
	}
	
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.finish();
	}



	private class DownloadWebPage extends AsyncTask<String, Void, String> {
    	protected void onPostExecute(String result) {
    		//Parsing article here
    		
    		pbArticle.setVisibility(View.GONE);
    		Document test = Jsoup.parse(result);
			Element e = test.getElementById("content");
			int l=test.title().indexOf('|');
			ArticleTitle = test.title().substring(0, l);
			actionBar.setTitle(ArticleTitle);
			
			String author = e.child(0).getElementsByClass("no-padding-left").text();
			tvAuthor.setText (author);
			
			String test0 = e.child(0).html();
			int textStart = e.child(0).html().indexOf("</p>")+4;
			int textEnd = e.child(0).html().indexOf("<div class=\"bottomcontainerBox\"");
			String text0 = test0.substring(textStart, textEnd);
			String text1 = text0.replaceAll("<p>", "");
			String textf = Jsoup.parse(text1).text();
			//String textf = text1.replaceAll("</p>", "");
			tvArticle.setText(textf);
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

}
