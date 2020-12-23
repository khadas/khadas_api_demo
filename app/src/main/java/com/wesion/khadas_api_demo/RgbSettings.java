package com.wesion.khadas_api_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class RgbSettings extends Activity {
	private static final String TAG = RgbSettings.class.getSimpleName();
	private RadioGroup rgb_group;
	private RadioButton off_Button,standard_Button,warm_Button,cold_Button;
	private TextView textView_rgb;
	
    private static final int INDEX_OFF = 0;
    private static final int INDEX_STANDARD = 1;
    private static final int INDEX_WARM = 2;
    private static final int INDEX_COLD = 3;	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb_main);
		
        textView_rgb =(TextView)findViewById(R.id.textView_rgbId);    
		rgb_group =(RadioGroup)findViewById(R.id.rgb_GroupId);
		
        standard_Button =(RadioButton)findViewById(R.id.standard_ButtonId);
        warm_Button=(RadioButton)findViewById(R.id.warm_ButtonId);
		cold_Button=(RadioButton)findViewById(R.id.cold_ButtonId);
		off_Button=(RadioButton)findViewById(R.id.off_ButtonId);
		
		if(INDEX_STANDARD == MainApp.getKhadasApi().getRgbColor())
			standard_Button.setChecked(true);
		else if(INDEX_WARM == MainApp.getKhadasApi().getRgbColor())
			warm_Button.setChecked(true);
 		else if(INDEX_COLD == MainApp.getKhadasApi().getRgbColor())
			cold_Button.setChecked(true);
 		else
			off_Button.setChecked(true);
		
        RadioGroupListener listener =new RadioGroupListener();
        rgb_group.setOnCheckedChangeListener(listener);
    }
    class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			if(checkedId == off_Button.getId()){
				MainApp.getKhadasApi().setRgbColor(INDEX_OFF);
			}else if(checkedId == standard_Button.getId()){
				MainApp.getKhadasApi().setRgbColor(INDEX_STANDARD);
			}else if(checkedId == warm_Button.getId()){
				MainApp.getKhadasApi().setRgbColor(INDEX_WARM);
			}else if(checkedId == cold_Button.getId()){
				MainApp.getKhadasApi().setRgbColor(INDEX_COLD);
			}

		}
    }
}
