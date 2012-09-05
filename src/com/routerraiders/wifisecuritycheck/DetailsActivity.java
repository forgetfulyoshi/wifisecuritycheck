package com.routerraiders.wifisecuritycheck;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    public static final String SECURITY = "com.routerraiders.wifisecuritycheck.security";

    private TextView mBottomLine;
    private TextView mExplanation;
    private TextView mUses;
    private Intent mIncomingIntent;

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_details);

	if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	mBottomLine = (TextView) findViewById(R.id.detail_bl);
	mExplanation = (TextView) findViewById(R.id.detail_explain);
	mUses = (TextView) findViewById(R.id.detail_uses);
	mIncomingIntent = getIntent();
    }

    @Override
    public void onResume() {
	super.onResume();

	int security = mIncomingIntent.getIntExtra(SECURITY, SecurityInfo.Type.ERROR);
	int resource = 0;
	String name = "";

	switch (security) {
	case SecurityInfo.Type.OPEN:
	    resource = R.array.explain_open_wifi;
	    name = SecurityInfo.Name.OPEN;
	    break;
	case SecurityInfo.Type.WEP:
	    resource = R.array.explain_wep;
	    name = SecurityInfo.Name.WEP;
	    break;
	case SecurityInfo.Type.WPA:
	    resource = R.array.explain_wpa;
	    name = SecurityInfo.Name.WPA;
	    break;
	case SecurityInfo.Type.WPA2:
	    resource = R.array.explain_wpa;
	    name = SecurityInfo.Name.WPA2;
	    break;
	case SecurityInfo.Type.TKIP:
	    resource = R.array.explain_tkip;
	    name = SecurityInfo.Name.TKIP;
	    break;
	case SecurityInfo.Type.AES:
	    resource = R.array.explain_aes;
	    name = SecurityInfo.Name.AES;
	    break;
	case SecurityInfo.Type.WPS:
	    resource = R.array.explain_wps;
	    name = SecurityInfo.Name.WPS;
	    break;
	case SecurityInfo.Type.ERROR:
	    break;
	default:
	    resource = R.array.explain_open_wifi;
	    name = SecurityInfo.Name.OPEN;
	    break;
	}

	if (0 != resource) {
	 
	    setTitle(getTitle() + " of " + name);
	    
	    String[] infoStrings = getResources().getStringArray(resource);
	    mBottomLine.setText(Html.fromHtml(infoStrings[0]));
	    mExplanation.setText(Html.fromHtml(infoStrings[1]));
	    mUses.setText(Html.fromHtml(infoStrings[2]));
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_explanation, menu);
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
