package com.braingames.sdk.numbersflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class Logo extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				startMain();
				
			}
		}.start();
	}
	
	
	private void startMain(){
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
		this.finish();
	}
}
