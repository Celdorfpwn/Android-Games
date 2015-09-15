package com.braingames.sdk.numbersflow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.helpers.CountDowner;
import com.braingames.sdk.numbersflow.helpers.GameModesEnum;
import com.braingames.sdk.numbersflow.helpers.NumbersFactory;
import com.braingames.sdk.numbersflow.helpers.Score;
import com.braingames.sdk.numbersflow.helpers.ScoreDatabase;
import com.braingames.sdk.numbersflow.helpers.SoundPlayer;

public abstract class GameMode extends Activity {
	
	protected ArrayList<View> _gameButtons;

	protected NumbersFactory _numbersFactory;
	
	protected CountDowner _countDowner;
	
	protected SoundPlayer _soundPlayer;
	
	protected GameModesEnum _gameMode;
	
	protected ScoreDatabase _database = new ScoreDatabase(this);
	
	
	protected abstract void setGameModeEnum();
	
	protected abstract void endGame();
	
	
	protected abstract void numberClick(View viewButton);
	
	protected abstract void replayGame();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_page);
		_soundPlayer = new SoundPlayer(this,(ImageButton)findViewById(R.id.soundButton));		
	}
	
	protected void initializeGame() {
		_numbersFactory = new NumbersFactory();
		setGameModeEnum();
		initializeButtonsList();
		initializeButtonsText();
		_countDowner = new CountDowner(
				(TextView) findViewById(R.id.counterTextView), this);
		_countDowner.start(); 
	}
	
	public void replayClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeButtonsList();
		initializeButtonsText();
		replayGame();
		_countDowner.restart((TextView) findViewById(R.id.counterTextView));
		_countDowner.reset();
	}

	
	public void showFinalScore() {
		endGame();
		_countDowner.end();
		setContentView(R.layout.score_page);
		TextView score = (TextView) findViewById(R.id.counterTextView);
		_database.addContact(new Score(Integer.toString((_numbersFactory
				.getScore())), _gameMode.toString()));
		score.setText("Number " + _numbersFactory.getScore());
		_gameButtons = null;
		_soundPlayer.finishSounds();
		populateScoresTable(_numbersFactory.getScore().intValue());
	}
	
	
	public void soundClick(View button){
		_soundPlayer.updateSoundSettings();
	}
	
	protected void initializeButtonsText() {
		_numbersFactory.initialize();

		for (View viewButton : _gameButtons) {
			Button button = (Button) viewButton;
			button.setText(Integer.toString(_numbersFactory.nextNumber()
					.intValue()));
		}
	}

	protected void initializeButtonsList() {
		TableLayout gameTable = (TableLayout) findViewById(R.id.game_table);
		_gameButtons = new ArrayList<View>();
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		for (int rowIndex = 0; rowIndex < gameTable.getChildCount(); rowIndex++) {
			TableRow row = (TableRow) gameTable.getChildAt(rowIndex);

			for (int buttonIndex = 0; buttonIndex < row.getChildCount(); buttonIndex++) {
				Button button = (Button)row.getChildAt(buttonIndex);
				button.setTextSize(height/40);
				_gameButtons.add(row.getChildAt(buttonIndex));
			}
		}
	}
	
	protected void populateScoresTable(int score) {

		ArrayList<TextView> textViews = new ArrayList<TextView>();
		textViews.add((TextView) findViewById(R.id.score1));
		textViews.add((TextView) findViewById(R.id.score2));
		textViews.add((TextView) findViewById(R.id.score3));
		textViews.add((TextView) findViewById(R.id.score4));
		textViews.add((TextView) findViewById(R.id.score5));

		TableLayout table = (TableLayout) findViewById(R.id.table_scores);

		List<Score> scores = _database.getFirstFiveScores(_gameMode);
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
	
	public void onBackPressed() {
		_countDowner.end();
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
		super.onBackPressed();
	}
}
