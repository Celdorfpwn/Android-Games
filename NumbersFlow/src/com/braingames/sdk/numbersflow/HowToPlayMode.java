package com.braingames.sdk.numbersflow;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.helpers.GameButtonsEffectsCreator;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;

public class HowToPlayMode extends GameMode {

	private GameButtonsEffectsCreator _buttonEffects;

	private CountDownTimer _timer;

	private int _number = 1;
	
	private int _randomCombo = 7;
	
	private Random _random;
	
	private int _countPause = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
		_random = new Random();
		_buttonEffects = new GameButtonsEffectsCreator(_gameButtons,this);
		startTutorial();
	}

	private void startTutorial() {
		_timer = new CountDownTimer(300000, 500) {

			@Override
			public void onTick(long arg0) {

				View button = getButton(_number);
				if (button != null) {

					
					if (_number % _randomCombo == 0 && _countPause < 4) {
						_countPause ++;
					}else{
						if(_countPause == 4)
						{
							_randomCombo = _random.nextInt(4)+4;
						}
						
						_countPause = 0;
						fakeNumberClick(button);
						_number++;
					}
					
				}

			}

			@Override
			public void onFinish() {
				// endGame();
			}
		};
		_timer.start();
	}

	private View getButton(int text) {
		for (View viewButton : _gameButtons) {
			Button button = (Button) viewButton;
			Integer number = Integer.parseInt(button.getText().toString());

			if (number.intValue() == text) {
				return viewButton;
			}
		}

		return null;
	}

	public void resetGame(View viewButton) {
		_number = 1;
		initializeButtonsText();
		_countDowner.reset();
	}

	@Override
	protected void setGameModeEnum() {
		_gameMode = GameModesEnum.HOWTOPLAY;
	}

	@Override
	public void showLastNumber() {
		_timer.cancel();
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
	}

	@Override
	public void numberClick(View viewButton) {
	}

	private void fakeNumberClick(View viewButton) {
		Button button = (Button) viewButton;
		Integer number = Integer.parseInt(button.getText().toString());
		if (_numbersFactory.validateNumber(number)) {
			_soundPlayer.playRight();
			_buttonEffects.correctEffect(button,Integer.toString(_numbersFactory.nextNumber()
					.intValue()));
			_countDowner.addSeconds(1);

		} else {
			_soundPlayer.playWrong();
			_buttonEffects.wrongEffect(button);
			_countDowner.removeSeconds(1);
		}
	}

	@Override
	protected void replayGame() {

	}

	public void onBackPressed() {
		_timer.cancel();
		super.onBackPressed();
	}

}
