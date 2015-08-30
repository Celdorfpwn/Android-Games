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
	private ArrayList<View> _gameButtons;

	private NumbersFactory _numbersFactory;

	private CountDowner _countDowner;

	private ButtonsRainbowEffects _buttonEffects;

	private ScoreDatabase _database = new ScoreDatabase(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeGame();
	}

	private void initializeGame() {
		_numbersFactory = new NumbersFactory();
		initializeButtonsList();
		initializeButtonsText();
		_buttonEffects = new ButtonsRainbowEffects();
		_countDowner = new CountDowner(
				(TextView) findViewById(R.id.counterTextView), this);
		_countDowner.start();
		_buttonEffects.start(_gameButtons);
		_soundPlayer.playRainbow();
	}

	private void initializeButtonsText() {
		_numbersFactory.initialize();

		for (View viewButton : _gameButtons) {
			Button button = (Button) viewButton;
			button.setText(Integer.toString(_numbersFactory.nextNumber()
					.intValue()));
		}
	}

	private void initializeButtonsList() {
		TableLayout gameTable = (TableLayout) findViewById(R.id.game_table);
		_gameButtons = new ArrayList<View>();

		for (int rowIndex = 0; rowIndex < gameTable.getChildCount(); rowIndex++) {
			TableRow row = (TableRow) gameTable.getChildAt(rowIndex);

			for (int buttonIndex = 0; buttonIndex < row.getChildCount(); buttonIndex++) {
				_gameButtons.add(row.getChildAt(buttonIndex));
			}
		}
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

	public void showFinalScore() {
		_buttonEffects.stop();
		_soundPlayer.stopRainbow();
		setContentView(R.layout.score_page);
		TextView score = (TextView) findViewById(R.id.counterTextView);
		_database.addContact(new Score(Integer.toString((_numbersFactory
				.getScore())), GameModesEnum.RAINBOW.toString()));
		score.setText("Number " + _numbersFactory.getScore());
		_gameButtons = null;
		_soundPlayer.finishSounds();
		populateScoresTable(_numbersFactory.getScore().intValue());

	}

	private void populateScoresTable(int score) {

		ArrayList<TextView> textViews = new ArrayList<TextView>();
		textViews.add((TextView) findViewById(R.id.score1));
		textViews.add((TextView) findViewById(R.id.score2));
		textViews.add((TextView) findViewById(R.id.score3));
		textViews.add((TextView) findViewById(R.id.score4));
		textViews.add((TextView) findViewById(R.id.score5));

		TableLayout table = (TableLayout) findViewById(R.id.table_scores);

		List<Score> scores = _database
				.getFirstFiveScores(GameModesEnum.RAINBOW);
		int index = 0;
		if (scores.size() > 0) {
			for (TextView text : textViews) {
				if (index < scores.size()) {
					int parsedScore = Integer
							.parseInt(scores.get(index)._score);

					if (parsedScore == score) {
						TableRow row = (TableRow) text.getParent();
						row.setBackgroundResource(R.color.Orange);
					}

					text.setText(scores.get(index)._score);
					index++;
				}
			}
		}
	}

	public void replayClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeButtonsList();
		initializeButtonsText();
		_buttonEffects.restart(_gameButtons);
		_countDowner.restart((TextView) findViewById(R.id.counterTextView));
		_countDowner.reset();
		_soundPlayer.playRainbow();
	}

	public void onBackPressed() {
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
	}
}
