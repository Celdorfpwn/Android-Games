package com.braingames.sdk.numbersflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
	}

	public void classicModeClick(View viewButton) {
		startActivity(ClassicMode.class);
	}
	
	public void rainbowModeClick(View viewButton){
		startActivity(RainBowMode.class);
	}
	
	private void startActivity(Class<?> activtyClass){
		Intent myIntent = new Intent(this, activtyClass);
		startActivity(myIntent);
	}
	
	public void quitGameClick(View viewButton){
		onBackPressed();
	}

}
