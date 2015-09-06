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
	
	private LayoutRainbowEffect _layoutEffect;
	
	public ButtonsRainbowEffects(View layout) {
		_effects = new ArrayList<RainbowEffect>();
		_layoutEffect = new LayoutRainbowEffect(layout);
	}
	


	public void start(ArrayList<View> buttons) {
		for(View button : buttons){
			RainbowEffect effect = new RainbowEffect(button);
			_effects.add(effect);
			effect.start();
		}
		
		_layoutEffect.start();
	}

	public void stop() {
		for(RainbowEffect effect : _effects){
			effect.stop();
		}
		_layoutEffect.stop();
	}

	public void restart(ArrayList<View> buttons,View layout) {
		for(int index = 0; index<buttons.size();index++){
			RainbowEffect effect = _effects.get(index);
			effect.update(buttons.get(index));
			effect.start();
		}
		_layoutEffect.update(layout);
		_layoutEffect.start();
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
	
	protected class LayoutRainbowEffect {

		private View _layout;

		private CountDownTimer _timer;
		
		private Random _random;

		public LayoutRainbowEffect(View layout) {
			_layout = layout;
			_random = new Random();
			_timer = new CountDownTimer(300000, 50) {
				@Override
				public void onTick(long arg0) {
					int color = Color.argb(255, _random.nextInt(256), _random.nextInt(256), _random.nextInt(256));   
					_layout.setBackgroundColor(color);
				}

				@Override
				public void onFinish() {
					_layout.setBackgroundResource(R.drawable.background);
				}
			};
		}
		
		public void start(){
			_layout.setBackgroundResource(0);
			_timer.start();
		}
		
		public void stop(){
			_timer.cancel();
		}

		public void update(View button) {
			_layout = button;
		}
	}
}
