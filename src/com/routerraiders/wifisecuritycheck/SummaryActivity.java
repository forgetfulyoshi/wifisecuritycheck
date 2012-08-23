package com.routerraiders.wifisecuritycheck;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;



public class SummaryActivity extends ListActivity {

    protected static final String AES = "AES/CCMP";
    protected static final String TKIP = "TKIP";
    protected static final String WEP = "WEP";
    protected static final String OPEN = "Open Network";
    protected static final String WPA = "WPA/WPA2";
    protected static final String ERROR = "Scan Error";
    private ListView mListView;
    private SecurityTypeArrayAdapter mArrayAdapter;
    private ArrayList<SecurityInfo> mSecurityInfoList;
    
    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_summary);

	if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	mListView = this.getListView();
	
	ProgressBar spinner = (ProgressBar) findViewById(R.id.summary_progressBar);
	mListView.setEmptyView(spinner);
	
	/*
	mArrayAdapter = new ArrayAdapter<String>(SummaryActivity.this, android.R.layout.simple_list_item_1,
		android.R.id.text1);
	*/
	mSecurityInfoList = new ArrayList<SecurityInfo>();
	mArrayAdapter = new SecurityTypeArrayAdapter(this, android.R.id.text1, mSecurityInfoList);
	mListView.setAdapter(mArrayAdapter);
	

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
		
		mArrayAdapter.clear();
		
		if (config == null) {
		    mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.ERROR, SecurityInfo.Name.ERROR));
		    mArrayAdapter.notifyDataSetChanged();
		    return;
		}
		
		/*
		if (config.allowedProtocols.get(WifiConfiguration.Protocol.RSN)) {
		    mArrayAdapter.add("WPA2");
		}
		
		if (config.allowedProtocols.get(WifiConfiguration.Protocol.WPA)) {
		}
		
		if (config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.OPEN)) {
		    // Used for WPA/WPA2 networks
		    mArrayAdapter.add(WPA);
		}
		
		if (config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.SHARED)){
		    // WEP shared key authentication
		}
		
		if (config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.LEAP)){
		    // LEAP authentication
		}
		
		if (config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.CCMP)) {
		    // AES
		    mArrayAdapter.add(AES);
		}
		
		if (config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.TKIP)) {
		    // TKIP
		    mArrayAdapter.add(TKIP);
		}
		
		if (config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP104) || config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP40)) {
		    mArrayAdapter.add(WEP);
		}
		    
		if (mArrayAdapter.isEmpty()) {
		    mArrayAdapter.add(OPEN);
		}
		
		mArrayAdapter.notifyDataSetChanged();
	    	*/
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
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	Integer security = 0;
	
	startActivity(new Intent(this, DetailsActivity.class).putExtra(DetailsActivity.SECURITY, security));
    }
}
