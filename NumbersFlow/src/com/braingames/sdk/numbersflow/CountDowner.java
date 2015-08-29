package com.braingames.sdk.numbersflow;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDowner {

	private TextView _textView;

	private MainActivity _activity;

	private CountDownTimer _timer;

	private final int _start = 60;

	private int _current;
	
	private boolean _isRunning = false;

	public CountDowner(TextView textView, MainActivity activity) {
		_textView = textView;
		_activity = activity;
		
		_timer = new CountDownTimer(30000000, 1000) {
			public void onTick(long millisUntilFinished) {
				if (_current <= 0) {
					this.onFinish();
				} else {
					update();
					_current--;
				}
			}

			public void onFinish() {
				_activity.showFinalScore();
				_isRunning = false;
			}
		};
	}
	
	public void setTextView(TextView textView){
		_textView = textView;
	}

	public void start() {
		_current = _start;
		_isRunning = true;
		_timer.start();
	}
	
	public void restart(TextView textView){
		_textView = textView;
		_isRunning = true;
		start();
	}

	public void reset() {
		_timer.cancel();
		start();
	}

	private void update() {
		if (_current >= 0) {
			_textView.setText(Integer.toString(_current));
		} else {
			_textView.setText("0");
		}
	}

	public void addSeconds(int seconds) {
		_current += seconds;
		update();
	}

	public void removeSeconds(int seconds) {
		_current -= seconds;
		update();
	}

}
