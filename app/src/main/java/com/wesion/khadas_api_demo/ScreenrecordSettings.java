package com.wesion.khadas_api_demo;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ScreenrecordSettings extends ListActivity {
	private static final String TAG = "ScreenrecordSettings";
	private Context mContext;
	private final static int KEY_1 = 0;
	private final int MSG_OK =  82;
	private final int MSG_ERROR = 83;
	public static int screenrecord_flag = 0;
	Handler mHandler = new InstallHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
		new Thread() {
			public void run() {
				screenrecord_Thread();
			}
		}.start();
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "screenrecord(\"/sdcard/demo.mp4\",6)",KEY_1);
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
				if(0 == screenrecord_flag) {
					Toast.makeText(mContext, "start screenrecord", Toast.LENGTH_SHORT).show();
					screenrecord_flag = 1;
				}else{
					Toast.makeText(mContext, "screenrecord busy", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
		}
	}

	public void screenrecord_Thread() {
		int ret = 0;
		while(true) {
			try {
				if (1 == screenrecord_flag) {
					ret = MainApp.getKhadasApi().screenrecord("/sdcard/demo.mp4",6);
				}
				if(0 != screenrecord_flag) {
					if (0 == ret)
						mHandler.sendEmptyMessage(MSG_OK);
					else
						mHandler.sendEmptyMessage(MSG_ERROR);
				}
				screenrecord_flag = 0;
				Thread.sleep(100);
			} catch (Exception localException1) {

			}
		}
	}

	class InstallHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case  MSG_ERROR:
					Toast.makeText(mContext, "screenrecord finish :" + "error", Toast.LENGTH_SHORT).show();
				break;
				case  MSG_OK:
					Toast.makeText(mContext, "screenrecord finish :" + "Success", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
}