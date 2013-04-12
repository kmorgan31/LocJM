package com.example.hotellocator;

import android.R.color;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	private Context context;
	static String[] parentList;
	static String[][] childList;

	public MyAdapter(Context context, Hotel[] hotels, Attraction[] attractions) {
		// TODO Auto-generated constructor stub
		this.context = context;
		parentList = new String[hotels.length+attractions.length];
		childList = new String[hotels.length+attractions.length][2];
		
		for(int i=0;i<parentList.length;i++){
			if(i<hotels.length)
				parentList[i] = hotels[i].getHotelName();
			else
				parentList[i] = attractions[i-hotels.length].getName();
		}
		for(int i=0;i<childList.length;i++){
			childList[i][0] = "More Info";
			childList[i][1] = "Contact";
		}
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		TextView tv = new TextView(context);
		tv.setText(childList[groupPosition][childPosition]);
		tv.setPadding(30, 0, 0, 0);
		tv.setTextSize(20);
		tv.setBackgroundColor(color.holo_blue_bright);
		return tv;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childList[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentList.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(context);
		tv.setText(parentList[groupPosition]);
		tv.setTextSize(25);
		tv.setPadding(30, 20, 10, 20);
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
