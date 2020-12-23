package com.wesion.khadas_api_demo;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
    }

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "execSuCmd", new Intent(this, ExecSuCmdSettings.class));
		addItem(myData, "Gpio", new Intent(this, GpioSettings.class));
		addItem(myData, "I2c", new Intent(this, I2cSettings.class));
		addItem(myData, "LOG", new Intent(this, LogSettings.class));
		addItem(myData, "Reboot", new Intent(this, RebootSettings.class));
		addItem(myData, "ShutDown", new Intent(this, ShutDownSettings.class));
		addItem(myData, "Sleep", new Intent(this, SleepSettings.class));
		addItem(myData, "SetTime", new Intent(this, SetTimeSettings.class));
		addItem(myData, "silent Install apk", new Intent(this, SilentInstallSettings.class));
		addItem(myData, "LED", new Intent(this, LedSettings.class));
		addItem(myData, "FAN", new Intent(this, FanSettings.class));
		addItem(myData, "RGB", new Intent(this, RgbSettings.class));
		addItem(myData, "WOL", new Intent(this, WolSettings.class));
		addItem(myData, "Language (System signature required)", new Intent(this, LanguageSettings.class));
		addItem(myData, "Hdmi", new Intent(this, HdmiSettings.class));
		addItem(myData, "SystemProperties", new Intent(this, SystemPropertiesSettings.class));
		addItem(myData, "Screenrecord", new Intent(this, ScreenrecordSettings.class));
		addItem(myData, "InstallPackage", new Intent(this, InstallPackageSettings.class));
		addItem(myData, "Uart", new Intent(this, UartSettings.class));
		return myData;
	} 

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}
}
