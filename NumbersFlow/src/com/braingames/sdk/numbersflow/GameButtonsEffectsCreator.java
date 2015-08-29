package com.braingames.sdk.numbersflow;

import java.util.ArrayList;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class GameButtonsEffectsCreator {

	private ArrayList<View> _buttons;

	private ArrayList<ButtonEffect> _correctEffects;

	private ArrayList<ButtonEffect> _wrongEffects;

	public GameButtonsEffectsCreator(ArrayList<View> buttons) {
		_buttons = buttons;
		_correctEffects = new ArrayList<ButtonEffect>();
		_wrongEffects = new ArrayList<ButtonEffect>();
		for (View button : _buttons) {
			_correctEffects.add(new CorrectEffect(button));
			_wrongEffects.add(new WrongEffect(button));
		}
	}
	
	public void update(ArrayList<View> buttons){
		_buttons = buttons;
		for(int index = 0;index < buttons.size();index++){
			_correctEffects.get(index).Update(buttons.get(index));
			_wrongEffects.get(index).Update(buttons.get(index));
		}
	}

	public void correctEffect(View button) {
		int index = _buttons.indexOf(button);
		
		_correctEffects.get(index).start();
	}

	public void wrongEffect(View button) {
		int index = _buttons.indexOf(button);
		
		_wrongEffects.get(index).start();
	}

	
	protected abstract class ButtonEffect {
		private View _button;

		private boolean _isRunning = false;

		private CountDownTimer _timer;

		public ButtonEffect(View button) {
			_button = button;
			_timer = new CountDownTimer(700, 50) {

				@Override
				public void onTick(long arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					_button.setBackgroundResource(R.color.Black);
					_isRunning = false;
				}
			};
		}
		
		public void Update(View button){
			_button = button;
		}
		
		protected abstract int setEffectBackground();
		
		public void start(){
			_button.setBackgroundResource(setEffectBackground());
			if(_isRunning){
				_timer.cancel();
				_timer.start();
			}else{
				_isRunning = true;
				_timer.start();
			}
		}
	}
	
	protected class CorrectEffect extends ButtonEffect {

		public CorrectEffect(View button) {
			super(button);
		}

		
		protected int setEffectBackground() {
			return R.color.Green;	
		}
		
	}

	protected class WrongEffect extends ButtonEffect {
		

		public WrongEffect(View button) {
			super(button);
		}

		protected int setEffectBackground() {
			return R.color.Red;
		}
		
	}
}
