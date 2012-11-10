package com.routerraiders.wifisecuritycheck;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SecurityInfoArrayAdapter extends ArrayAdapter<SecurityInfo> {

    private Context mContext;
    private Integer mLayoutResourceId;

    public SecurityInfoArrayAdapter(Context context, int textViewResourceId, ArrayList<SecurityInfo> objects) {
	super(context, textViewResourceId, objects);
	mContext = context;
	mLayoutResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;

	if (row == null) {
	    LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
	    row = inflater.inflate(mLayoutResourceId, parent, false);
	}

	SecurityInfo item = getItem(position);
	if (item != null) {
	    TextView itemView = (TextView) row.findViewById(R.id.summary_entry_text);
	    if (itemView != null) {
		itemView.setText(item.name);
	    }
	    row.setTag(item.type);
	}

	return row;
    }

}
