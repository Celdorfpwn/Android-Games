package com.braingames.sdk.numbersflow;

import android.media.MediaPlayer;

public class SoundPlayer {
	private MediaPlayer _rightPlayer;
	private MediaPlayer _wrongPlayer;

	public SoundPlayer(MainActivity context) {
		_rightPlayer = MediaPlayer.create(context, R.raw.correct);
		_wrongPlayer = MediaPlayer.create(context, R.raw.wrong);
	}

	public void playRight() {
		if (_rightPlayer.isPlaying()) {
			_rightPlayer.pause();
			_rightPlayer.seekTo(0);
		}
		_rightPlayer.start();
	}

	public void playWrong() {
		if (_wrongPlayer.isPlaying()) {
			_wrongPlayer.pause();
			_wrongPlayer.seekTo(0);
		}
		_wrongPlayer.start();
	}
	
	public void finishSounds(){
		if (_rightPlayer.isPlaying()) {
			_rightPlayer.pause();
			_rightPlayer.seekTo(0);
		}
		if (_wrongPlayer.isPlaying()) {
			_wrongPlayer.pause();
			_wrongPlayer.seekTo(0);
		}
	}
}
