package com.braingames.sdk.numbersflow;

import com.braingames.sdk.numbersflow.helpers.SoundPlayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public abstract class GameMode extends Activity {
	
	protected SoundPlayer _soundPlayer;
	
	public abstract void showFinalScore();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_page);
		_soundPlayer = new SoundPlayer(this,(ImageButton)findViewById(R.id.soundButton));		
	}
	
	public void soundClick(View button){
		_soundPlayer.updateSoundSettings();
	}
}
