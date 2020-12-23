package com.wesion.khadas_api_demo;

import android.os.Bundle;

import java.io.File;
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

public class InstallPackageSettings extends ListActivity {
	private static final String TAG = "InstallPackageSettings";
	private Context mContext;
	private final static int KEY_1 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "InstallPackage",KEY_1);
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
			case KEY_1:
				String path_name = "/data/update.zip";
				File file=new File(path_name);
				if(!file.exists())
				{
					Toast.makeText(mContext, "no such file", Toast.LENGTH_SHORT).show();
				}
				else {
					MainApp.getKhadasApi().installPackage(path_name);
				}
				break;
			default:
				break;
		}
	}
}