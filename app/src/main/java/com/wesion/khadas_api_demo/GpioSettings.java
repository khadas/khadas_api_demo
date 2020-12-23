package com.wesion.khadas_api_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GpioSettings extends Activity{
	private static final String TAG = "GpioSettings";
	private Context mContext;

	Button read_gpio_btn;
	TextView read_gpio_tv;

	Button write_gpio_1_btn,write_gpio_0_btn;
	TextView write_gpio_tv;
	
	EditText gpio_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpio_main);
		mContext = this;
		gpio_text = (EditText)findViewById(R.id.gpio_text);

		read_gpio_btn = (Button)findViewById(R.id.read_gpio_btn);
		read_gpio_tv = (TextView)findViewById(R.id.read_gpio_tv);

		write_gpio_0_btn = (Button)findViewById(R.id.write_gpio_0_btn);
		write_gpio_1_btn = (Button)findViewById(R.id.write_gpio_1_btn);
		write_gpio_tv = (TextView)findViewById(R.id.write_gpio_tv);		
		
		read_gpio_btn.setOnClickListener(ocl);
		write_gpio_0_btn.setOnClickListener(ocl);
		write_gpio_1_btn.setOnClickListener(ocl);
	}

	OnClickListener ocl =new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.read_gpio_btn:
				read_gpio_tv.setText(MainApp.getKhadasApi().gpioRead(gpio_text.getText().toString())+"");
				break;
			case R.id.write_gpio_0_btn:
				write_gpio_tv.setText(MainApp.getKhadasApi().gpioCrtl(gpio_text.getText().toString(),"out",0)?"ok":"error");
				read_gpio_tv.setText(MainApp.getKhadasApi().gpioRead(gpio_text.getText().toString())+"");
				break;
			case R.id.write_gpio_1_btn:
				write_gpio_tv.setText(MainApp.getKhadasApi().gpioCrtl(gpio_text.getText().toString(),"out",1)?"ok":"error");
				read_gpio_tv.setText(MainApp.getKhadasApi().gpioRead(gpio_text.getText().toString())+"");
				break;
			default: 
				break;
			}
		}
	};
}
