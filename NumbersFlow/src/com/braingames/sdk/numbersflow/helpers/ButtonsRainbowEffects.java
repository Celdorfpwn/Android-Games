package com.braingames.sdk.numbersflow.helpers;

import java.util.ArrayList;
import java.util.Random;

import com.braingames.sdk.numbersflow.R;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class ButtonsRainbowEffects {

	private ArrayList<RainbowEffect> _effects;
	
	public ButtonsRainbowEffects() {
		_effects = new ArrayList<RainbowEffect>();
	}
	


	public void start(ArrayList<View> buttons) {
		for(View button : buttons){
			RainbowEffect effect = new RainbowEffect(button);
			_effects.add(effect);
			effect.start();
		}
	}

	public void stop() {
		for(RainbowEffect effect : _effects){
			effect.stop();
		}
	}

	public void restart(ArrayList<View> buttons) {
		for(int index = 0; index<buttons.size();index++){
			RainbowEffect effect = _effects.get(index);
			effect.update(buttons.get(index));
			effect.start();
		}
	}

	protected class RainbowEffect {

		private View _button;

		private CountDownTimer _timer;
		
		private Random _random;

		public RainbowEffect(View button) {
			_button = button;
			_random = new Random();
			_timer = new CountDownTimer(300000, 50) {

				@Override
				public void onTick(long arg0) {
					int color = Color.argb(255, _random.nextInt(256), _random.nextInt(256), _random.nextInt(256));   
					int fontColor = Color.argb(255, _random.nextInt(256), _random.nextInt(256), _random.nextInt(256)); 
					_button.setBackgroundColor(color);
					Button button = (Button)_button;
					button.setTextColor(fontColor);
				}

				@Override
				public void onFinish() {
					_button.setBackgroundResource(R.color.Black);
				}
			};
		}
		
		public void start(){
			_timer.start();
		}
		
		public void stop(){
			_timer.cancel();
		}

		public void update(View button) {
			_button = button;
		}
	}
}
