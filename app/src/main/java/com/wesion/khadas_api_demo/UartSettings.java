package com.wesion.khadas_api_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wesion.khadasapi.serialport.SerialPortConfig;
import com.wesion.khadasapi.serialport.SerialPortHelper;
import com.wesion.khadasapi.serialport.SphCmdEntity;
import com.wesion.khadasapi.serialport.SphResultCallback;

public class UartSettings extends Activity {
	private static final String TAG = "UartSettings";
	private boolean isOpen;
	private static final String SEND_CMD="112233445566";
	private SerialPortHelper serialPortHelper;


	Button mOpenBtn;
	Button mCloseBtn;
	Button mSendMsgBtn;
	TextView mInfoView;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uart_main);

		mOpenBtn = (Button)findViewById(R.id.open_serial_port);
		mCloseBtn = (Button)findViewById(R.id.close_serial_port);
		mSendMsgBtn = (Button)findViewById(R.id.send_msg);
		mInfoView = (TextView)findViewById(R.id.testinfo);

		mOpenBtn.setOnClickListener(ocl);
		mCloseBtn.setOnClickListener(ocl);
		mSendMsgBtn.setOnClickListener(ocl);
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.open_serial_port:
				mInfoView.setText("");
				boolean open = openSerialPort("/dev/ttyS2",9600);
				mInfoView.append(open?"open success\n":"open fail\n");
				break;
			case R.id.send_msg:
				mInfoView.append("\nsend CMD=123456789abcdef0\n");
				String sendTxt = "123456789abcdef0";
				serialPortHelper.addCommands(sendTxt);
				break;
			case R.id.close_serial_port:
				if(serialPortHelper != null) {
					serialPortHelper.closeDevice();
					mInfoView.append("close");
				}
				serialPortHelper = null;
				break;
			default:
				break;
			} 

		}
	};

	private boolean openSerialPort(String path,int baudrate){
		SerialPortConfig serialPortConfig = new SerialPortConfig();
		serialPortConfig.mode = 0;
		serialPortConfig.path = path;
		serialPortConfig.baudRate = baudrate;
		serialPortConfig.dataBits = 8;
		serialPortConfig.parity   = 'N';
		serialPortConfig.stopBits = 1;

		// Initialization serial port
		serialPortHelper = new SerialPortHelper(16);
		// Setting serial port parameters
		serialPortHelper.setConfigInfo(serialPortConfig);
		// Open serial port
		isOpen = serialPortHelper.openDevice();
		if(!isOpen){
			return false;
		}
		serialPortHelper.setSphResultCallback(new SphResultCallback() {
			@Override
			public void onSendData(SphCmdEntity sendCom) {
				Log.d(TAG, "send command:" + sendCom.commandsHex);
			}

			@Override
			public void onReceiveData(SphCmdEntity data) {
				final String result = data.commandsHex;
				Log.d(TAG, "onDataReceived:"+data.commandsHex);
				if(data.commandsHex != null && data.commandsHex.trim().length() > 0)
					mInfoView.post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							mInfoView.append(result);
						}
					});
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "finish");
			}
		});

		return true;
	}
}
