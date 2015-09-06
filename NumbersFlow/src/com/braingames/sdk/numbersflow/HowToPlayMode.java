package com.braingames.sdk.numbersflow;

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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
		_buttonEffects = new GameButtonsEffectsCreator(_gameButtons);
		startTutorial();
	}

	private void startTutorial() {
		_timer = new CountDownTimer(300000, 1000) {

			@Override
			public void onTick(long arg0) {

				View button = getButton(_number);
				if (button != null) {
					_number++;
					fakeNumberClick(button);
				}

			}

			@Override
			public void onFinish() {
				//endGame();
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
	protected void endGame() {
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
			_buttonEffects.correctEffect(button,
					Integer.toString(_numbersFactory.nextNumber().intValue()));
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
