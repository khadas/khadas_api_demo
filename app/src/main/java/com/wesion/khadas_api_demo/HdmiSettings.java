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
import android.widget.Toast;

public class HdmiSettings extends ListActivity {
	private static final String TAG = "HdmiSettings";
	private Context mContext;
	private final static int KEY_1 = 0;
	private final static int KEY_2 = 1;
	private final static int KEY_3 = 3;
	private final static int KEY_4 = 4;
	private boolean enable = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "Set display resolution",KEY_1);
		addItem(myData, "Get display resolution",KEY_2);
		addItem(myData, "switch best resolution onoff",KEY_3);
		addItem(myData, "Get the valid display resolution",KEY_4);
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
		boolean ret = false;

		switch (key) {
			case KEY_1:
				MainApp.getKhadasApi().setResolution("1080p60hz");
				//Toast.makeText(mContext, "start install", Toast.LENGTH_SHORT).show();
				break;
			case KEY_2:
				Toast.makeText(mContext, MainApp.getKhadasApi().getCurrentResolution(), Toast.LENGTH_SHORT).show();
				break;
			case KEY_3:
				enable = !enable;
				if(enable)
					Toast.makeText(mContext, "on", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(mContext, "off", Toast.LENGTH_SHORT).show();
				MainApp.getKhadasApi().switchBestResolution(enable);
				break;
			case KEY_4:
				Toast.makeText(mContext, MainApp.getKhadasApi().getValidResolution(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}

}