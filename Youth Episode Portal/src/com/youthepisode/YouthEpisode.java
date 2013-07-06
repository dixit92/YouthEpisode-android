package com.youthepisode;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class YouthEpisode extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth_episode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_youth_episode, menu);
        return true;
    }
}
