package com.braingames.sdk.numbersflow.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationSettings {

	private static final String SETTINGS = "settings";
	private static final String SOUNDSTATUS = "soundStatus";
	
	
	public static boolean getSoundStatus(Activity context){
		SharedPreferences prefs = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return prefs.getBoolean(SOUNDSTATUS, true);
	}
	
	public static void setSoundStatus(Activity context,boolean value){
		SharedPreferences prefs = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(SOUNDSTATUS, value).apply();
	}
}
