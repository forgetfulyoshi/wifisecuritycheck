package com.routerraiders.wifisecuritycheck;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends Activity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_summary);
	getActionBar().setDisplayHomeAsUpEnabled(true);

	TextView textView = (TextView) findViewById(R.id.textView1);
	textView.setText(Html.fromHtml(getString(R.string.warn_open_wifi)));
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
    
    public void onTestClick(View v) {
	this.startActivity(new Intent(this, ExplanationActivity.class));
    }

}
