package com.wesion.khadas_api_demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
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

import androidx.core.app.ActivityCompat;

import com.wesion.khadasapi.logcathelper.LogcatHelper;

public class LogSettings extends ListActivity {
	private static final String TAG = "LogSettings";
	private Context mContext;
	private final static int KEY_1 = 0;
	private final static int KEY_2 = 1;
	public static int Log_flag = 0;
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
		verifyStoragePermissions(this);
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "start Log",KEY_1);
		addItem(myData, "close Log(Quit catch log by suicide)",KEY_2);
		return myData;
	} 

	protected void addItem(List<Map<String, Object>> data, String name,int key) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("key", key);
		data.add(temp);
	}



	public static void verifyStoragePermissions(Activity activity) {
		int permission = ActivityCompat.checkSelfPermission(activity,
				Manifest.permission.ACCESS_FINE_LOCATION);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
					REQUEST_EXTERNAL_STORAGE);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		int key = (int) map.get("key");
		Log.v("sjf","key:"+key);
		switch (key) {
			case KEY_1:
				if(0 == Log_flag) {
					Toast.makeText(mContext, "start Log", Toast.LENGTH_SHORT).show();
					Log_flag = 1;
					LogcatHelper.getInstance(mContext).start("/mnt/sdcard/123.log");
				}else{
					Toast.makeText(mContext, "Log busy", Toast.LENGTH_SHORT).show();
				}
				break;
			case KEY_2:
				android.os.Process.killProcess(android.os.Process.myPid());
				break;
			default:
				break;
		}
	}
}