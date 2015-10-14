package com.braingames.sdk.numbersflow;

import java.util.ArrayList;
import java.util.List;

import com.braingames.sdk.numbersflow.helpers.GameModesEnum;
import com.braingames.sdk.numbersflow.helpers.Score;
import com.braingames.sdk.numbersflow.helpers.ScoreDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoresTable extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores_table);
		populateScoresTable();
	}

	protected void populateScoresTable() {

		ArrayList<TextView> textViews = new ArrayList<TextView>();
		textViews.add((TextView) findViewById(R.id.score1));
		textViews.add((TextView) findViewById(R.id.score2));
		textViews.add((TextView) findViewById(R.id.score3));
		textViews.add((TextView) findViewById(R.id.score4));
		textViews.add((TextView) findViewById(R.id.score5));

		ScoreDatabase _database = new ScoreDatabase(this);

		List<Score> scores = _database
				.getFirstFiveScores(GameModesEnum.CLASSIC);
		int index = 0;
		if (scores.size() > 0) {
			for (TextView text : textViews) {
				if (index < scores.size()) {

					TableRow row = (TableRow) text.getParent();
					row.setBackgroundResource(R.color.Orange);

					text.setText(scores.get(index)._score);
					index++;
				}
			}
		}
	}

	public void onBackPressed() {
		Intent myIntent = new Intent(this, MainActivity.class);
		startActivity(myIntent);
		super.onBackPressed();
	}
}
