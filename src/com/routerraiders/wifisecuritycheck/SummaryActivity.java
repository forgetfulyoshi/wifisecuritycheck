package com.routerraiders.wifisecuritycheck;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class SummaryActivity extends ListActivity {

    private static final String TAG = "SummaryActivity";
    private ListView mListView;
    private SecurityInfoArrayAdapter mArrayAdapter;
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
	 * mArrayAdapter = new ArrayAdapter<String>(SummaryActivity.this,
	 * android.R.layout.simple_list_item_1, android.R.id.text1);
	 */
	mSecurityInfoList = new ArrayList<SecurityInfo>();
	mArrayAdapter = new SecurityInfoArrayAdapter(this, android.R.id.text1, mSecurityInfoList);
	mListView.setAdapter(mArrayAdapter);

    }

    @Override
    public void onResume() {
	super.onResume();

	new AsyncTask<Context, Void, ScanResult>() {
	    @Override
	    protected ScanResult doInBackground(Context... contexts) {
		// Get wifi info

		WifiManager manager = (WifiManager) contexts[0].getSystemService(WIFI_SERVICE);
		List<ScanResult> scanResults = null;

		if (manager.startScan()) {
		    while (scanResults == null || scanResults.isEmpty()) {
			scanResults = manager.getScanResults();
		    }

		    for (ScanResult result : scanResults) {
			if (manager.getConnectionInfo().getBSSID().equals(result.BSSID)) {
			    return result;
			}
		    }
		}

		return null;
	    }

	    @Override
	    protected void onPostExecute(ScanResult config) {

		mArrayAdapter.clear();

		if (config == null) {
		    Log.d(TAG, "got null for wificonfig");
		    mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.ERROR, SecurityInfo.Name.ERROR));
		    mArrayAdapter.notifyDataSetChanged();
		    return;
		}

		if (config.capabilities.contains("WPA")) {

		    if (config.capabilities.contains("WPA2")) {
			mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.WPA2, SecurityInfo.Name.WPA2));
		    } else {
			mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.WPA, SecurityInfo.Name.WPA));
		    }
		    
		    if (config.capabilities.contains("CCMP")) {
			// AES
			mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.AES, SecurityInfo.Name.AES));
		    }

		    if (config.capabilities.contains("TKIP")) {
			// TKIP
			mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.TKIP, SecurityInfo.Name.TKIP));
		    }

		    ((RelativeLayout) mListView.getParent()).setBackgroundResource(R.raw.green_lock);

		} else if (config.capabilities.contains("WEP")) {
		    mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.WEP, SecurityInfo.Name.WEP));

		    ((RelativeLayout) mListView.getParent()).setBackgroundResource(R.raw.yellow_lock);
		}

		if (mArrayAdapter.isEmpty()) {
		    mArrayAdapter.add(new SecurityInfo(SecurityInfo.Type.OPEN, SecurityInfo.Name.OPEN));
		    ((RelativeLayout) mListView.getParent()).setBackgroundResource(R.raw.red_lock);
		}

		mArrayAdapter.notifyDataSetChanged();

	    }

	}.execute(this);

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
	Integer security = (Integer) v.getTag();
	startActivity(new Intent(this, DetailsActivity.class).putExtra(DetailsActivity.SECURITY, security));
    }
}
