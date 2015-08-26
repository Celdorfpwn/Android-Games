package com.braingames.sdk.numbersflow;

import java.util.ArrayList;
import java.util.Random;

public class NumbersFactory {

	private ArrayList<Integer> _currentList;

	private Integer _nextNumber;

	private int _start;

	private int _limit;

	public void initialize() {
		_nextNumber = Integer.valueOf(1);
		_currentList = new ArrayList<Integer>();
		_start = 1;
		_limit = 25;
	}

	private ArrayList<Integer> get25Numbers(int start, int limit) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (int i = start; i <= limit; i++) {
			numbers.add(Integer.valueOf(i));
		}

		return numbers;
	}

	public Integer nextNumber() {
		if (_currentList.isEmpty()) {
			updateCurrentList();
		}
		Integer number = _currentList.get(0);
		_currentList.remove(0);
		return number;
	}

	private void updateCurrentList() {
		ArrayList<Integer> orderedNumbers = get25Numbers(_start, _limit);
		_start += 25;
		_limit += 25;
		Random rand = new Random();
		while (!orderedNumbers.isEmpty()) {
			int randomIndex = 0;

			if (orderedNumbers.size() > 1) {
				randomIndex = rand.nextInt(orderedNumbers.size() - 1);
			}
			else
			{
				randomIndex = 0;
			}
			Integer number = orderedNumbers.get(randomIndex);
			_currentList.add(number);
			orderedNumbers.remove(randomIndex);
		}
	}

	public boolean validateNumber(Integer number) {
		if (number.intValue() == _nextNumber.intValue()) {
			_nextNumber = Integer.valueOf(_nextNumber.intValue()+1);
			return true;
		} else {
			return false;
		}
	}
}
