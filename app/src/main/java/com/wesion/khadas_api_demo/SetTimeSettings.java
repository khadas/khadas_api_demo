package com.wesion.khadas_api_demo;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SetTimeSettings extends ListActivity {
	private static final String TAG = "SetTimeSettings";
	private Context mContext;
	private final static int KEY_TIME1 = 0;
	private final static int KEY_TIME2 = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "setTime: 2020-10-12, 15:30",KEY_TIME1);
		addItem(myData, "setTime: 2021-3-8, 9:00",KEY_TIME2);
		return myData;
	} 

	protected void addItem(List<Map<String, Object>> data, String name,int key) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("key", key);
		data.add(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		int key = (int) map.get("key");
		Log.v("sjf","key:"+key);
		switch (key) {
			case KEY_TIME1:
				MainApp.getKhadasApi().setTime(2020, 10, 12, 15, 30, 17);
				break;
			case KEY_TIME2:
				MainApp.getKhadasApi().setTime(2021, 3, 8, 9, 0, 45);
				break;
			default:
				break;
		}
	}
}