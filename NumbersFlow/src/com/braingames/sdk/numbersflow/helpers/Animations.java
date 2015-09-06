package com.braingames.sdk.numbersflow.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class Animations {

	private static final long _duration = 2500;

	public static Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(_duration);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		inFromRight.setRepeatCount(Animation.INFINITE);
		return inFromRight;
	}

	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(_duration);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		outtoLeft.setRepeatCount(Animation.INFINITE);
		return outtoLeft;
	}

	public static Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(_duration);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		inFromLeft.setRepeatCount(Animation.INFINITE);
		return inFromLeft;
	}

	public static void rightInLeftOut(Object view){
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "x",500f,  0f);
		fadeOut.setDuration(_duration);
		//fadeOut.setRepeatCount(Animation.INFINITE);
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "x", 0f,-500f);
		fadeIn.setDuration(_duration);
		//fadeIn.setRepeatCount(Animation.INFINITE);
		final AnimatorSet mAnimationSet = new AnimatorSet();
		mAnimationSet.play(fadeIn).before(fadeOut);//.after(fadeOut);
		mAnimationSet.addListener(new AnimatorListenerAdapter() {
		    @Override
		    public void onAnimationEnd(Animator animation) {
		        super.onAnimationEnd(animation);
		        mAnimationSet.start();
		    }
		});
		mAnimationSet.start();
	}
	
	public static void leftInRightOut(Object view) {
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "x",0f,  500f);
		fadeOut.setDuration(_duration);
		//fadeOut.setRepeatCount(Animation.INFINITE);
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "x", -500f,0f);
		fadeIn.setDuration(_duration);
		//fadeIn.setRepeatCount(Animation.INFINITE);
		final AnimatorSet mAnimationSet = new AnimatorSet();
		mAnimationSet.play(fadeIn).before(fadeOut);//.after(fadeOut);
		mAnimationSet.addListener(new AnimatorListenerAdapter() {
		    @Override
		    public void onAnimationEnd(Animator animation) {
		        super.onAnimationEnd(animation);
		        mAnimationSet.start();
		    }
		});
		mAnimationSet.start();
	}

	public static AnimationSet leftToRightWheel() {
		AnimationSet s = new AnimationSet(false);
		s.addAnimation(Animations.inFromRightAnimation());
		s.addAnimation(Animations.outToLeftAnimation());
		return s;
	}

	public static Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(_duration);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		outtoRight.setRepeatCount(Animation.INFINITE);
		return outtoRight;
	}

}
