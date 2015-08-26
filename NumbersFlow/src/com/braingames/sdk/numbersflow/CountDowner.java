package com.braingames.sdk.numbersflow;

import android.widget.TextView;

public class CountDowner {
	
	private TextView _textView;
	
	private MainActivity _activity; 
	
	private final int _start = 60;
	
	private int _current;
	
	public CountDowner(TextView textView,MainActivity activity){
		_textView = textView;
		_activity = activity;
	}
	
	public void start(){
		_current = _start;
	}
	
	
	private void update(){
		_textView.setText(Integer.toString(_start));
	}
	
	public void addSeconds(int seconds){
		_current += seconds;
	}
	
	public void removeSeconds(int seconds){
		_current -= seconds;
	}
	
	
}
