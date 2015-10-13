package com.braingames.sdk.numbersflow.helpers;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.braingames.sdk.numbersflow.GameMode;

public class CountDowner {

	private TextView _textView;

	private GameMode _activity;

	private CountDownTimer _timer;

	private final int _start = 60;

	private int _current;

	private boolean _isRunning = false;

	public boolean _comboFlag = false;

	private int _combo = 0;

	private BonusCalculator _bonusCalculator;

	public CountDowner(TextView textView, GameMode activity) {
		_textView = textView;
		_activity = activity;
		_bonusCalculator = new BonusCalculator();
		_timer = new CountDownTimer(30000000, 1100) {
			public void onTick(long millisUntilFinished) {
				if (_isRunning) {
					if (_current <= 0) {
						_textView.setText("Game Over");
						this.onFinish();
					} else {

						if (_comboFlag) {
							update();
						} else if (_combo > 3) {
							updateCombo();
						} else {
							update();
							_combo = 0;
						}
						_comboFlag = false;
						_current--;
					}
				}
			}

			public void onFinish() {
				_activity.showLastNumber();
				_isRunning = false;
			}
		};
	}

	public void setTextView(TextView textView) {
		_textView = textView;
	}

	public void start() {
		_current = _start;
		_comboFlag = false;
		_isRunning = true;
		_timer.start();
	}

	public void restart(TextView textView) {
		_textView = textView;
		_isRunning = true;
		start();
	}

	public void end() {
		_timer.cancel();
		_comboFlag = false;
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

	private void updateCombo() {
		int bonus = _bonusCalculator.calculateBonus(_combo);
		_textView.setText(_combo + "X Combo! added " + bonus + " seconds");
		_activity.playComboSound();
		_current += bonus;
		_comboFlag = false;
		_combo = 0;
	}

	public void addSeconds(int seconds) {
		_current += seconds;
		_comboFlag = true;
		_combo++;
		update();
	}

	public void removeSeconds(int seconds) {
		_current -= seconds;
		_comboFlag = false;
		_combo = 0;
		update();
	}

}
