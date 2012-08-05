package com.routerraiders.wifisecuritycheck;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class SummaryActivity extends ListActivity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_summary);
	
	if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}

    }

    @Override
    public void onResume() {
	super.onResume();

	new AsyncTask<Context, Void, WifiConfiguration>() {

	    @Override
	    protected WifiConfiguration doInBackground(Context... contexts) {
		// Get wifi info

		WifiManager manager = (WifiManager) contexts[0].getSystemService(WIFI_SERVICE);

		for (WifiConfiguration config : manager.getConfiguredNetworks()) {
		    if (WifiConfiguration.Status.CURRENT == config.status) {
			return config;
		    }
		}

		return null;
	    }

	    @Override
	    protected void onPostExecute(WifiConfiguration config) {
		// Add rows to list
	    }

	}.execute(this.getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_summary, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    NavUtils.navigateUpFromSameTask(this);
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }
}
