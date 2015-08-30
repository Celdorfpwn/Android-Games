package com.braingames.sdk.numbersflow.helpers;

public class Score implements Comparable<Score> {
	
	public int _id;
	public String _score;
	public String _mode;
	
	public Score(String score,String date){
		_score = score;
		_mode = date;
	}
	
	public Score(){}

	
	public boolean equalsScore(Score another){
		int score1 = Integer.parseInt(another._score);
		int score2 = Integer.parseInt(this._score);
		
		return score1 == score2;
	}
	@Override
	public int compareTo(Score another) {
		int score1 = Integer.parseInt(another._score);
		int score2 = Integer.parseInt(this._score);
		
		if(score1 > score2)
			return 1;
		else if( score1 < score2)
		return -1;
				
		return 0;
	}
}
