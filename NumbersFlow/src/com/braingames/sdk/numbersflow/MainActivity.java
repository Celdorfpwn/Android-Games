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
	
	public void scoresTableClick(View viewButton){
		startActivity(ScoresTable.class);
	}
	
	public void slotsModeClick(View viewButton){
		startActivity(SlotsMode.class);
	}
	
	public void howtoplayModeClick(View viewButton){
		startActivity(HowToPlayMode.class);
	}
	
	private void startActivity(Class<?> activtyClass){
		Intent myIntent = new Intent(this, activtyClass);
		startActivity(myIntent);
		super.onBackPressed();
	}
	
	public void quitGameClick(View viewButton){
		super.onBackPressed();
	}

}
