package com.routerraiders.wifisecuritycheck;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SecurityTypeArrayAdapter extends ArrayAdapter<SecurityInfo> {

    private ArrayList<SecurityInfo> mItems;
    private Context mContext;
    
    public SecurityTypeArrayAdapter(Context context, int textViewResourceId, ArrayList<SecurityInfo> objects) {
	super(context, textViewResourceId, objects);
	mContext = context;
    }
    
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	if (convertView == null) {
	    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
	}
	
	SecurityInfo item = getItem(position);
	if (item != null) {
	    TextView itemView = (TextView) convertView.findViewById(android.R.id.text1);
	    if (itemView != null) {
		itemView.setText(item.name);
		itemView.setTag(item.type);
	    }
	}
	
	return convertView;
    }

}
