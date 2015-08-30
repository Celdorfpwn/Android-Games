package com.braingames.sdk.numbersflow.helpers;

import android.media.MediaPlayer;
import android.widget.ImageButton;

import com.braingames.sdk.numbersflow.GameMode;
import com.braingames.sdk.numbersflow.R;

public class SoundPlayer {
	private MediaPlayer _rightPlayer;
	private MediaPlayer _wrongPlayer;
	private MediaPlayer _rainbowPlayer;

	private ImageButton _soundButton;

	private GameMode _context;

	private boolean _soundEnabled;
	
	private boolean _rainbowPlaying = false;

	public SoundPlayer(GameMode context, ImageButton soundButton) {
		_context = context;
		_soundButton = soundButton;
		_rightPlayer = MediaPlayer.create(context, R.raw.correct);
		_wrongPlayer = MediaPlayer.create(context, R.raw.wrong);
		_rainbowPlayer = MediaPlayer.create(context, R.raw.rainbowsong);
		_rainbowPlayer.setVolume(0.3f, 0.4f);
		updateSoundSettings();
	}

	public void updateSoundSettings() {
		boolean soundSettings = ApplicationSettings.getSoundStatus(_context);

		if (soundSettings) {
			ApplicationSettings.setSoundStatus(_context, false);
			_soundButton.setImageResource(R.drawable.disabled);
			disableSounds();
			_soundEnabled = false;
		} else {
			ApplicationSettings.setSoundStatus(_context, true);
			_soundButton.setImageResource(R.drawable.enabled);
			_soundEnabled = true;
			if(_rainbowPlaying){
				play(_rainbowPlayer);
			}
		}
	}

	private void disableSounds() {
		stop(_rainbowPlayer);
	}

	private void play(MediaPlayer player) {
		if (_soundEnabled) {
			stop(player);
			player.start();
		}
	}

	private void stop(MediaPlayer player) {
		if (player.isPlaying()) {
			player.pause();
			player.seekTo(0);
		}
	}

	public void playRight() {
		play(_rightPlayer);
	}

	public void playWrong() {
		play(_wrongPlayer);
	}

	public void playRainbow() {
		_rainbowPlaying = true;
		play(_rainbowPlayer);
	}

	public void stopRainbow() {
		stop(_rainbowPlayer);
	}

	public void finishSounds() {
		stop(_rightPlayer);
		stop(_wrongPlayer);
	}
}
