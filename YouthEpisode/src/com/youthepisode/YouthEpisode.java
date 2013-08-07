package com.youthepisode;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class YouthEpisode extends ActionBarActivity {
	TextView status, status2;
	ProgressBar initial; 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_episode);
        
        String YouthEpisodeURL = "http://www.youthepisode.com";
        status = (TextView) findViewById(R.id.status);
        status2 = (TextView) findViewById(R.id.status2);
        initial = (ProgressBar) findViewById (R.id.progressBarHome);
        
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);    
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
            	status.setText ("Network Found. \nDownloading Data...");
            	new DownloadWebPage().execute(YouthEpisodeURL);
            	
            } else {
            	status.setText ("Can't Connect to the Internet");
            }
    }

    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.finish();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.youth_episode, menu);
        return true;
    }
    
    private class DownloadWebPage extends AsyncTask<String, Void, String> {
    	protected void onPostExecute(String result) {
    		
    		status2.setText("Page Retrieved");
            initial.setVisibility(View.GONE);
            Intent home = new Intent (YouthEpisode.this, Home.class);
            home.putExtra("com.YouthEpisode.Home.result", result);
            startActivity (home);
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
		        
		       //Jsoup parsing
		       
		        Document soupDoc = Jsoup.connect(myurl).maxBodySize(0).timeout(5000).get();
		        return soupDoc.html();
		        
		    // Makes sure that the InputStream is closed after the app is
		    // finished using it.
		    } finally {
		        	
		        } 
		    }
		}
    }