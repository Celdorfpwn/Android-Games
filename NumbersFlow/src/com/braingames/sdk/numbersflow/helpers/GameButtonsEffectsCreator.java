package com.braingames.sdk.numbersflow.helpers;

import java.util.ArrayList;

import com.braingames.sdk.numbersflow.GameMode;
import com.braingames.sdk.numbersflow.R;

import android.R.color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class GameButtonsEffectsCreator {

	private ArrayList<View> _buttons;

	private ArrayList<CorrectEffect> _correctEffects;

	private ArrayList<ButtonEffect> _wrongEffects;

	private Button _lastButton;

	private GameMode _gameMode;

	public GameButtonsEffectsCreator(ArrayList<View> buttons, GameMode gameMode) {
		_buttons = buttons;
		_gameMode = gameMode;
		_correctEffects = new ArrayList<CorrectEffect>();
		_wrongEffects = new ArrayList<ButtonEffect>();
		for (View button : _buttons) {
			_correctEffects.add(new CorrectEffect(button));
			_wrongEffects.add(new WrongEffect(button));
		}
	}

	public void EndGameEffect(Integer lastNumber) {
		if (_buttons != null) {
			for (View viewButton : _buttons) {
				if (viewButton != null) {
					Button button = (Button) viewButton;
					Integer value = Integer.parseInt(button.getText().toString());

					if (value.intValue() == lastNumber.intValue()) {
						_lastButton = button;
					}
					viewButton.setEnabled(false);
				}
			}

			new CountDownTimer(2000, 200) {
				public void onTick(long millisUntilFinished) {
					if (_lastButton != null) {
						_lastButton.setBackgroundResource(R.drawable.last_button);
					}
				}

				public void onFinish() {
					_gameMode.showFinalScore();
				}
			}.start();
		}

	}

	public void update(ArrayList<View> buttons) {
		_buttons = buttons;
		for (int index = 0; index < buttons.size(); index++) {
			_correctEffects.get(index).Update(buttons.get(index));
			_wrongEffects.get(index).Update(buttons.get(index));
		}
	}

	public void correctEffect(View button, String text) {
		int index = _buttons.indexOf(button);

		_correctEffects.get(index).start(text);
	}

	public void wrongEffect(View button) {
		int index = _buttons.indexOf(button);

		_wrongEffects.get(index).start();
	}

	protected abstract class ButtonEffect {
		protected View _button;

		protected boolean _isRunning = false;

		protected CountDownTimer _timer;

		public ButtonEffect() {
		}

		public ButtonEffect(View button) {
			_button = button;
			_timer = new CountDownTimer(700, 50) {

				@Override
				public void onTick(long arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					_button.setBackgroundResource(R.drawable.buttonsimplu);
					_isRunning = false;
				}
			};
		}

		public void Update(View button) {
			_button = button;
		}

		protected abstract int setEffectBackground();

		public void start() {
			_button.setBackgroundResource(setEffectBackground());
			if (_isRunning) {
				_timer.cancel();
				_timer.start();
			} else {
				_isRunning = true;
				_timer.start();
			}
		}
	}

	protected class CorrectEffect extends ButtonEffect {

		protected String _text;

		public CorrectEffect(View button) {
			_button = button;
			_timer = new CountDownTimer(700, 50) {

				@Override
				public void onTick(long arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					((Button) _button).setText(_text);
					_button.setBackgroundResource(R.drawable.buttonsimplu);
					_isRunning = false;
				}
			};

		}

		protected int setEffectBackground() {
			return R.drawable.buttoncorrect;
		}

		public void start(String text) {
			_text = text;
			super.start();
		}

	}

	protected class WrongEffect extends ButtonEffect {

		public WrongEffect(View button) {
			super(button);
		}

		protected int setEffectBackground() {
			return R.drawable.buttongresit;
		}

	}
}
