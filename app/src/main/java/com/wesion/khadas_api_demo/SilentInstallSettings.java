package com.wesion.khadas_api_demo;

import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SilentInstallSettings extends ListActivity {
	private static final String TAG = "SilentInstallSettings";
	private Context mContext;
	private final static int KEY_1 = 0;
	private final static int KEY_2 = 1;
	private final int MSG_OK =  82;
	private final int MSG_ERROR = 83;
	public static int install_flag = 0;
	private static final String apkPath = "/storage/0ADF-0FE2/uc.apk";

	Handler mHandler = new InstallHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
		new Thread() {
			public void run() {
				install_Thread();
			}
		}.start();
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "install: /storage/0ADF-0FE2/uc.apk",KEY_1);
		addItem(myData, "UnInstall: com.UCMobile",KEY_2);
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
				File file=new File(apkPath);
				if(!file.exists())
				{
					Toast.makeText(mContext, "no such file", Toast.LENGTH_SHORT).show();
				}
				else {
					install_flag = 1;
					Toast.makeText(mContext, "start install", Toast.LENGTH_SHORT).show();
				}
				break;
			case KEY_2:
				install_flag = 2;
				Toast.makeText(mContext, "start UnInstall :", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}

	public void install_Thread() {
		boolean ret = false;
		while(true) {
			try {
				if (1 == install_flag) {
					ret = MainApp.getKhadasApi().silentInstall(apkPath);
				} else if (2 == install_flag) {
					ret = MainApp.getKhadasApi().silentUnInstall("com.UCMobile");
				}
				if(0 != install_flag) {
					if (ret)
						mHandler.sendEmptyMessage(MSG_OK);
					else
						mHandler.sendEmptyMessage(MSG_ERROR);
				}
				install_flag = 0;
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
					Toast.makeText(mContext, "install finish :" + "error", Toast.LENGTH_SHORT).show();
				break;
				case  MSG_OK:
					Toast.makeText(mContext, "install finish :" + "Success", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
}