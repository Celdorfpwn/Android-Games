package com.braingames.sdk.numbersflow;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayList<Button> _gameButtons;
	
	private NumbersFactory _numbersFactory;
	
	private CountDowner _countDowner;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
	}
	
	public void playClick(View viewButton){
		setContentView(R.layout.game_page);
		initializeGame();
	}
	
	
	private void initializeGame(){
		_numbersFactory = new NumbersFactory();
		_countDowner = new CountDowner((TextView)findViewById(R.id.counterTextView),this);
		initializeButtonsList();
		initializeButtonsText();
	}
	
	private void initializeButtonsText(){
		_numbersFactory.initialize();
		
		for(Button button : _gameButtons){
			button.setText(Integer.toString(_numbersFactory.nextNumber().intValue()));
		}
	}
	
	private void initializeButtonsList(){
		TableLayout gameTable = (TableLayout)findViewById(R.id.game_table);
		_gameButtons = new ArrayList<Button>();
		
		for(int rowIndex = 0;rowIndex < gameTable.getChildCount();rowIndex++){
			TableRow row = (TableRow)gameTable.getChildAt(rowIndex);
			
			for(int buttonIndex = 0;buttonIndex < row.getChildCount();buttonIndex++){
				Button button = (Button)row.getChildAt(buttonIndex);
				_gameButtons.add(button);
			}
		}
		
	}
	
	public void resetGame(View viewButton){
		initializeButtonsText();
	}
	
	public void numberClick(View viewButton){
		Button button = (Button)viewButton;
		
		Integer number = Integer.parseInt(button.getText().toString());
		
		if(_numbersFactory.validateNumber(number)){
			button.setText(Integer.toString(_numbersFactory.nextNumber().intValue()));
			_countDowner.addSeconds(1);
		}
		else{
			_countDowner.removeSeconds(1);
		}
	}
}
