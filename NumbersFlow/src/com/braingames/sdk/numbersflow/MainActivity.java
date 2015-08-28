package com.braingames.sdk.numbersflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayList<Button> _gameButtons;

	private NumbersFactory _numbersFactory;

	private CountDowner _countDowner;
	
	private GameButtonsEffectsCreator _buttonEffects;
	
	private ScoreDatabase _database = new ScoreDatabase(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_page);
	}

	public void playClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeGame();
	}

	private void initializeGame() {
		_numbersFactory = new NumbersFactory();
		_buttonEffects = new GameButtonsEffectsCreator();
		initializeButtonsList();
		initializeButtonsText();
		_countDowner = new CountDowner((TextView) findViewById(R.id.counterTextView), this);
		_countDowner.start();
	}

	private void initializeButtonsText() {
		_numbersFactory.initialize();

		for (Button button : _gameButtons) {
			button.setText(Integer.toString(_numbersFactory.nextNumber().intValue()));
		}
	}

	private void initializeButtonsList() {
		TableLayout gameTable = (TableLayout) findViewById(R.id.game_table);
		_gameButtons = new ArrayList<Button>();

		for (int rowIndex = 0; rowIndex < gameTable.getChildCount(); rowIndex++) {
			TableRow row = (TableRow) gameTable.getChildAt(rowIndex);

			for (int buttonIndex = 0; buttonIndex < row.getChildCount(); buttonIndex++) {
				Button button = (Button) row.getChildAt(buttonIndex);
				_gameButtons.add(button);
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
			_buttonEffects.correctEffect(button);
			button.setText(Integer.toString(_numbersFactory.nextNumber().intValue()));
			_countDowner.addSeconds(1);
		} else {
			_buttonEffects.wrongEffect(button);
			_countDowner.removeSeconds(1);
		}
	}

	public void showFinalScore(){
		setContentView(R.layout.score_page);
		TextView score = (TextView)findViewById(R.id.counterTextView);
		_database.addContact(new Score(Integer.toString((_numbersFactory.getScore())),new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		score.setText("Number "+ _numbersFactory.getScore());
		_gameButtons = null;
		populateScoresTable(_numbersFactory.getScore().intValue());
	}
	
	private void populateScoresTable(int score){
		
		ArrayList<TextView> textViews = new ArrayList<TextView>();
		textViews.add((TextView)findViewById(R.id.score1));
		textViews.add((TextView)findViewById(R.id.score2));
		textViews.add((TextView)findViewById(R.id.score3));
		textViews.add((TextView)findViewById(R.id.score4));
		textViews.add((TextView)findViewById(R.id.score5));
		
		TableLayout table = (TableLayout)findViewById(R.id.table_scores);
		
		List<Score> scores = _database.getFirstFiveScores();
		int index = 0;
		for(TextView text : textViews){
			if(index < scores.size()){
				int parsedScore = Integer.parseInt(scores.get(index)._score);
				
				if(parsedScore == score){
					TableRow row = (TableRow)text.getParent();
					row.setBackgroundResource(R.color.Orange);
				}
				
				text.setText(scores.get(index)._score);
				index++;
			}
		}
	}

	public void replayClick(View viewButton) {
		setContentView(R.layout.game_page);
		initializeButtonsList();
		initializeButtonsText();
		_countDowner.setTextView((TextView) findViewById(R.id.counterTextView));
		_countDowner.start();
	}

}
