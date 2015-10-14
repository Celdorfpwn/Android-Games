package com.braingames.sdk.numbersflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braingames.sdk.numbersflow.helpers.GameButtonsEffectsCreator;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;


public class ClassicMode extends GameMode {

	private GameButtonsEffectsCreator _buttonEffects;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
	}

	protected void initializeGame() {
		super.initializeGame();
		_buttonEffects = new GameButtonsEffectsCreator(_gameButtons,this);
	}

	public void resetGame(View viewButton) {
		initializeButtonsText();
		_countDowner.reset();
	}

	public void numberClick(View viewButton) {
		Button button = (Button) viewButton;
		Integer number = Integer.parseInt(button.getText().toString());
		if (_numbersFactory.validateNumber(number)) {
			_soundPlayer.playRight();
			_buttonEffects.correctEffect(button, Integer.toString(_numbersFactory.nextNumber().intValue()));
			_countDowner.addSeconds(1);

		} else {
			_soundPlayer.playWrong();
			_buttonEffects.wrongEffect(button);
			_countDowner.removeSeconds(1);
		}
	}

	public void showLastNumber() {
		Integer lastNumber = _numbersFactory.getLastNumber();
		_buttonEffects.EndGameEffect(lastNumber);
	}

	protected void replayGame() {
		_buttonEffects.update(_gameButtons);
	}

	protected void setGameModeEnum() {
		_gameMode = GameModesEnum.CLASSIC;
	}

}
