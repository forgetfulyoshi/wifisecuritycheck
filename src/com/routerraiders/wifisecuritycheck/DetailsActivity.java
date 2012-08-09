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
    public static final int WEP = 0;
    public static final int WPA = 1;
    public static final int WPA2 = 2;
    public static final int OPEN = 3;
    public static final int TKIP = 4;
    public static final int AES = 5;
    public static final int WPS = 6;

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

	int security = mIncomingIntent.getIntExtra(SECURITY, OPEN);
	int resource = 0;

	switch (security) {
	case OPEN:
	    resource = R.array.explain_open_wifi;
	    break;
	case WEP:
	    resource = R.array.explain_wep;
	    break;
	case WPA:
	    resource = R.array.explain_wpa;
	    break;
	case WPA2:
	    resource = R.array.explain_wpa;
	    break;
	case TKIP:
	    resource = R.array.explain_tkip;
	    break;
	case AES:
	    resource = R.array.explain_aes;
	    break;
	case WPS:
	    resource = R.array.explain_wps;
	    break;
	default:
	    resource = R.array.explain_open_wifi;
	    break;
	}

	String[] infoStrings = getResources().getStringArray(resource);

	mBottomLine.setText(Html.fromHtml(infoStrings[0]));
	mExplanation.setText(Html.fromHtml(infoStrings[1]));
	mUses.setText(Html.fromHtml(infoStrings[2]));
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
