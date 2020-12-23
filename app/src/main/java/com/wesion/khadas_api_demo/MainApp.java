package com.wesion.khadas_api_demo;

import android.app.Application;
import com.wesion.KhadasApi;

public class MainApp extends Application {

	private static KhadasApi mKhadasApi;
	public static KhadasApi getKhadasApi() {
		return mKhadasApi;
	}
	
 	@Override
	public void onCreate() {
		super.onCreate();
		mKhadasApi = new KhadasApi(this);
	}
}
