package com.braingames.sdk.numbersflow.helpers;

import java.util.ArrayList;

public class BonusCalculator {
	
	public ArrayList<Integer> _combos;
	
	public BonusCalculator(){
		_combos = new ArrayList<Integer>();
	}
	
	
	
	public int calculateBonus(int combo){
		_combos.add(combo);
		return combo/3 + combo;
	}
}
