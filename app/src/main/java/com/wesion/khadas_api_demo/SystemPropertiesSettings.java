package com.wesion.khadas_api_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class SystemPropertiesSettings extends Activity{
	private static final String TAG = "SystemPropertiesSettings";
	private Context mContext;

	Button read_systemproperties_int_btn,read_systemproperties_string_btn;
	TextView read_systemproperties_int_tv,read_systemproperties_string_tv;

	Button write_systemproperties_string_btn,write_systemproperties_int_btn;
	TextView write_systemproperties_tv;
	
	EditText systemproperties_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.systemproperties_main);
		mContext = this;
		systemproperties_text = (EditText)findViewById(R.id.systemproperties_text);

		read_systemproperties_int_btn = (Button)findViewById(R.id.read_systemproperties_int_btn);
		read_systemproperties_int_tv = (TextView)findViewById(R.id.read_systemproperties_int_tv);
		read_systemproperties_string_btn = (Button)findViewById(R.id.read_systemproperties_string_btn);
		read_systemproperties_string_tv = (TextView)findViewById(R.id.read_systemproperties_string_tv);
		
		write_systemproperties_int_btn = (Button)findViewById(R.id.write_systemproperties_int_btn);
		write_systemproperties_string_btn = (Button)findViewById(R.id.write_systemproperties_string_btn);
		write_systemproperties_tv = (TextView)findViewById(R.id.write_systemproperties_tv);		
		
		read_systemproperties_int_btn.setOnClickListener(ocl);
		read_systemproperties_string_btn.setOnClickListener(ocl);
		write_systemproperties_int_btn.setOnClickListener(ocl);
		write_systemproperties_string_btn.setOnClickListener(ocl);
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	OnClickListener ocl =new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.read_systemproperties_int_btn:
				read_systemproperties_int_tv.setText(String.valueOf(MainApp.getKhadasApi().getSystemProperties_int("persist.sys.test1", 0)));
				break;
			case R.id.read_systemproperties_string_btn:
				read_systemproperties_string_tv.setText(MainApp.getKhadasApi().getSystemProperties("persist.sys.test2", "UNKNOWN"));
				break;				
			case R.id.write_systemproperties_int_btn:
				if(isInteger(systemproperties_text.getText().toString())) {
					MainApp.getKhadasApi().setSystemProperties_int("persist.sys.test1", Integer.parseInt(systemproperties_text.getText().toString()));
					read_systemproperties_int_tv.setText(String.valueOf(MainApp.getKhadasApi().getSystemProperties_int("persist.sys.test1", 0)));
				}else{
					read_systemproperties_int_tv.setText("not int");
				}
				break;
			case R.id.write_systemproperties_string_btn:
				MainApp.getKhadasApi().setSystemProperties("persist.sys.test2", systemproperties_text.getText().toString());
				read_systemproperties_string_tv.setText(MainApp.getKhadasApi().getSystemProperties("persist.sys.test2", "UNKNOWN"));
				break;
			default: 
				break;
			}
		}
	};
}
