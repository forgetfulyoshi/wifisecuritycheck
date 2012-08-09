package com.routerraiders.wifisecuritycheck;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    private TextView mBottomLine;
    private TextView mExplanation;
    private TextView mUses;
    
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
        
        String[] infoStrings = getResources().getStringArray(R.array.explain_open_wifi);
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
