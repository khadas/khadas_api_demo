package com.wesion.khadas_api_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LedSettings extends Activity {
	private static final String TAG = LedSettings.class.getSimpleName();
	private RadioGroup whiteLed_group,redLed_Group;
	private RadioButton w_heartbeatButton,w_onButton,w_offButton,r_heartbeatButton,r_onButton,r_offButton;
	private TextView textView_whiteLed,textView_redLed;
	private static final int LED_WHITE = 0;
    private static final int LED_RED   = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledsettings_main);
		
        textView_whiteLed =(TextView)findViewById(R.id.textView_whiteLedId);      
        whiteLed_group =(RadioGroup)findViewById(R.id.whiteLed_GroupId);
        w_heartbeatButton =(RadioButton)findViewById(R.id.w_heartbeatButtonId);
        w_onButton=(RadioButton)findViewById(R.id.w_onButtonId);
		w_offButton=(RadioButton)findViewById(R.id.w_offButtonId);

        textView_redLed =(TextView)findViewById(R.id.textView_redLedId);        
        redLed_Group=(RadioGroup)findViewById(R.id.redLed_GroupId);
        r_heartbeatButton =(RadioButton)findViewById(R.id.r_heartbeatButtonId);
        r_onButton=(RadioButton)findViewById(R.id.r_onButtonId);
		r_offButton=(RadioButton)findViewById(R.id.r_offButtonId);
		
		if(0 == MainApp.getKhadasApi().getLedMode(LED_WHITE))
			w_heartbeatButton.setChecked(true);
		else if(1 == MainApp.getKhadasApi().getLedMode(LED_WHITE))
			w_onButton.setChecked(true);
		else if(2 == MainApp.getKhadasApi().getLedMode(LED_WHITE))
			w_offButton.setChecked(true);
 
		if(0 == MainApp.getKhadasApi().getLedMode(LED_RED))
			r_heartbeatButton.setChecked(true);
		else if(1 == MainApp.getKhadasApi().getLedMode(LED_RED))
			r_onButton.setChecked(true);
		else if(2 == MainApp.getKhadasApi().getLedMode(LED_RED))
			r_offButton.setChecked(true);
		
        RadioGroupListener listener =new RadioGroupListener();
        whiteLed_group.setOnCheckedChangeListener(listener);
        redLed_Group.setOnCheckedChangeListener(listener);
    }
    class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(group == whiteLed_group){
				if(checkedId == w_heartbeatButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_WHITE,0);
				}else if(checkedId == w_onButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_WHITE,1);
				}else if(checkedId == w_offButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_WHITE,2);
				}
			}else if(group == redLed_Group){
				if(checkedId == r_heartbeatButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_RED,0);
				}else if(checkedId == r_onButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_RED,1);
				}else if(checkedId == r_offButton.getId()){
					MainApp.getKhadasApi().setLedMode(LED_RED,2);
				}
			}
		}
    }
}
