package com.braingames.sdk.numbersflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.helpers.CountDowner;
import com.braingames.sdk.numbersflow.helpers.GameButtonsEffectsCreator;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;
import com.braingames.sdk.numbersflow.helpers.NumbersFactory;
import com.braingames.sdk.numbersflow.helpers.Score;
import com.braingames.sdk.numbersflow.helpers.ScoreDatabase;
import com.braingames.sdk.numbersflow.helpers.SoundPlayer;

public class ClassicMode extends GameMode {

	private GameButtonsEffectsCreator _buttonEffects;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
	}

	protected void initializeGame() {
		super.initializeGame();
		_buttonEffects = new GameButtonsEffectsCreator(_gameButtons);
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
			_buttonEffects.correctEffect(button);
			button.setText(Integer.toString(_numbersFactory.nextNumber()
					.intValue()));
			_countDowner.addSeconds(1);

		} else {
			_soundPlayer.playWrong();
			_buttonEffects.wrongEffect(button);
			_countDowner.removeSeconds(1);
		}
	}

	public void showFinalScore() {
		setContentView(R.layout.score_page);
		TextView score = (TextView) findViewById(R.id.counterTextView);
		_database.addContact(new Score(Integer.toString((_numbersFactory
				.getScore())),GameModesEnum.CLASSIC.toString()));
		score.setText("Number " + _numbersFactory.getScore());
		_gameButtons = null;
		_soundPlayer.finishSounds();
		populateScoresTable(_numbersFactory.getScore().intValue());

	}


	public void replayClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeButtonsList();
		initializeButtonsText();
		_buttonEffects.update(_gameButtons);
		_countDowner.restart((TextView) findViewById(R.id.counterTextView));
		_countDowner.reset();
	}

	public void onBackPressed() {
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
	}

}
