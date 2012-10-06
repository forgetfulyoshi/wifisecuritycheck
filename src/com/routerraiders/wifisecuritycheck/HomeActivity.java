package com.routerraiders.wifisecuritycheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends Activity {

    private static final int DIALOG_ACTIVATE_WIFI = 0;
    private static final int ABOUT = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
    }

    @SuppressWarnings("deprecation")
    public void onStartButtonClick(View view) {

	WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	if (!manager.isWifiEnabled() || !wifiInfo.isConnected()) {
	    showDialog(DIALOG_ACTIVATE_WIFI);
	}

	if (wifiInfo.isConnected()) {
	    this.startActivity(new Intent(this, SummaryActivity.class));
	}
    }

    @Override
    protected Dialog onCreateDialog(int id) {
	Dialog dialog;
	switch (id) {
	case DIALOG_ACTIVATE_WIFI:
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage(R.string.dialog_activate_wifi);
	    builder.setCancelable(false);
	    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
		    HomeActivity.this.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
		}
	    });

	    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
		    dialog.cancel();
		}
	    });

	    dialog = builder.create();
	    break;

	default:
	    dialog = null;
	}
	return dialog;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, ABOUT, 0, "About");
	return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case ABOUT:
	    AboutDialog about = new AboutDialog(this);
	    about.show();

	    break;

	}
	return true;
    }

}
