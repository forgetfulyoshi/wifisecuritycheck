package com.routerraiders.wifisecuritycheck;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class DetailsActivity extends Activity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}
        
        WebView web = (WebView)this.findViewById(R.id.explanation_webview);
        web.loadUrl("file:///android_asset/warn_open_wifi.html");
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
