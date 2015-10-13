package com.braingames.sdk.numbersflow;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.helpers.ButtonsRainbowEffects;
import com.braingames.sdk.numbersflow.helpers.CountDowner;
import com.braingames.sdk.numbersflow.helpers.GameButtonsEffectsCreator;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;
import com.braingames.sdk.numbersflow.helpers.NumbersFactory;
import com.braingames.sdk.numbersflow.helpers.Score;
import com.braingames.sdk.numbersflow.helpers.ScoreDatabase;
import com.braingames.sdk.numbersflow.helpers.SoundPlayer;

public class RainBowMode extends GameMode{


	private ButtonsRainbowEffects _buttonEffects;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
	}

	protected void initializeGame() {
		super.initializeGame();
		_buttonEffects = new ButtonsRainbowEffects(findViewById(R.id.game_layout));
		_buttonEffects.start(_gameButtons);
		_soundPlayer.playRainbow();
	}


	public void resetGame(View viewButton) {
		initializeButtonsText();
		_countDowner.reset();
		_soundPlayer.playRainbow();
	}

	public void numberClick(View viewButton) {
		Button button = (Button) viewButton;
		Integer number = Integer.parseInt(button.getText().toString());
		if (_numbersFactory.validateNumber(number)) {
			_soundPlayer.playRight();
			button.setText(Integer.toString(_numbersFactory.nextNumber()
					.intValue()));
			_countDowner.addSeconds(1);

		} else {
			_soundPlayer.playWrong();
			_countDowner.removeSeconds(1);
		}
	}
	
	public void showLastNumber(){
		_buttonEffects.stop();
		_soundPlayer.stopRainbow();
	}
	
    protected void replayGame(){
    	_buttonEffects.restart(_gameButtons,findViewById(R.id.game_layout));
    	_soundPlayer.playRainbow();
    }
	

	
	public void onBackPressed() {
		super.onBackPressed();
		_soundPlayer.stopRainbow();
	}

	protected void setGameModeEnum() {
		_gameMode = GameModesEnum.RAINBOW;
	}

}
