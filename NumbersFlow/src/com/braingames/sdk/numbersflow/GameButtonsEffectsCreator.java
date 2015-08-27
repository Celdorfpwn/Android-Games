package com.braingames.sdk.numbersflow;

import android.os.CountDownTimer;
import android.widget.Button;

public class GameButtonsEffectsCreator {
	
	
	
	public void correctEffect(Button button){
		new CorrectEffect(button);
	}
	
	public void wrongEffect(Button button){
		new WrongEffect(button);
	}
	
	protected class CorrectEffect
	{
		private Button _button;
		
		public CorrectEffect(Button button){
			button.setBackgroundResource(R.color.Green);
			_button = button;
			new CountDownTimer(700, 50) {

		        @Override
		        public void onTick(long arg0) {
		            // TODO Auto-generated method stub

		        }
		        @Override
		        public void onFinish() {
		        	_button.setBackgroundResource(R.color.Black);
		                    }
		    }.start();
		}
	}
	
	
	protected class WrongEffect
	{
		private Button _button;
		
		public WrongEffect(Button button){
			button.setBackgroundResource(R.color.Red);
			_button = button;
			new CountDownTimer(700, 50) {

		        @Override
		        public void onTick(long arg0) {
		            // TODO Auto-generated method stub

		        }
		        @Override
		        public void onFinish() {
		        	_button.setBackgroundResource(R.color.Black);
		                    }
		    }.start();
		}
	}
}
