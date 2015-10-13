package com.braingames.sdk.numbersflow;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.helpers.Animations;
import com.braingames.sdk.numbersflow.helpers.GameButtonsEffectsCreator;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;

public class SlotsMode extends GameMode {

	private GameButtonsEffectsCreator _buttonEffects;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
	}

	protected void initializeGame() {
		super.initializeGame();
		_buttonEffects = new GameButtonsEffectsCreator(_gameButtons,this);
		addAnimation();
	}

	private void addAnimation() {
		TableLayout table = (TableLayout) findViewById(R.id.game_table);
		for (int index = 0; index < table.getChildCount(); index++) {
			TableRow row = (TableRow) table.getChildAt(index);
			if (index % 2 == 0) {
				Animations.rightInLeftOut(row);
			} else {
				Animations.leftInRightOut(row);
			}
		}

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
			_buttonEffects.correctEffect(button,
					Integer.toString(_numbersFactory.nextNumber().intValue()));
			_countDowner.addSeconds(1);

		} else {
			_soundPlayer.playWrong();
			_buttonEffects.wrongEffect(button);
			_countDowner.removeSeconds(1);
		}
	}

	public void showLastNumber() {

	}

	protected void replayGame() {
		_buttonEffects.update(_gameButtons);
	}

	public void replayClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeButtonsList();
		initializeButtonsText();
		_countDowner.restart((TextView) findViewById(R.id.counterTextView));
		_countDowner.reset();
	}

	protected void setGameModeEnum() {
		_gameMode = GameModesEnum.SLOTS;
	}

}
