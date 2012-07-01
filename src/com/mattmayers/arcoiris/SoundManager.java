package com.mattmayers.arcoiris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;



public class SoundManager {
	private final Random random = new Random();
	
	private  SoundPool mSoundPool; 
	private int[][] mRedList = {
			{R.raw.e, R.raw.e2},
			{R.raw.d, R.raw.d2},
			{R.raw.eb, R.raw.eb2}
	};
	private int[][] mBlueList = {
			{R.raw.g, R.raw.g2},
			{R.raw.fs, R.raw.fs2}
	};
	private int[] mGreenList = {
			{R.raw.b, R.raw.b2},
			{R.raw.c, R.raw.c2}
	};

	private int Vez = 0;
	private AudioManager  mAudioManager;
	private Context mContext;
	
	public SoundManager(Context context)
	{
		mContext = context;
	    mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0); 
	    
	    for (int i = 0; i <= mRedList.length; ++i){
	    	for (int j = 0; j <= mRedList[i].length; ++j) {
	    		mRedList[i][j] = mSoundPool.load(mContext, mRedList[i][j], 1);
	    	}
	    }
	    for (int i = 0; i <= mBlueList.length; ++i){
	    	for (int j = 0; j <= mBlueList[i].length; ++j) {
	    		mBlueList[i][j] = mSoundPool.load(mContext, mBlueList[i][j], 1);
	    	}
	    }
	    for (int i = 0; i <= mGreenList.length; ++i){
	    	for (int j = 0; j <= mGreenList[i].length; ++j) {
	    		mGreenList[i][j] = mSoundPool.load(mContext, mGreenList[i][j], 1);
	    	}
	    }
	    mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE); 	 
	}
	
	public void addSound(int SoundID)
	{
		mRedList.add(mSoundPool.load(mContext, SoundID, 1));
	}
	
	private int random(int[] list) {
		return list[random.nextInt(list.length)];
	}
	
	public void playSound(int color) {
		if (color == Color.RED){
		     int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		     if (SoundManager.Vez == 0) {
		    	 mSoundPool.play(random(mRedList[0]), streamVolume, streamVolume, 1, 0, 1f);
		    	 }
		     if (SoundManager.Vez == 1) {
		    	 mSoundPool.play(random(mRedList[1]), streamVolume, streamVolume, 1, 0, 1f);
		    	 }
		     if (SoundManager.Vez == 2) {
		    	 mSoundPool.play(random(mRedList[2]), streamVolume, streamVolume, 1, 0, 1f);
		    	 }
		     if (SoundManager.Vez == 3) {
		    	 mSoundPool.play(random(mRedList[3]), streamVolume, streamVolume, 1, 0, 1f);
		    	 }
		}
		
		if (color == Color.BLUE){
		     int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		     if (SoundManager.Vez == 0) {
		    	 mSoundPool.play(random(mBlueList[0]), streamVolume, streamVolume, 1, 0, 1f); 
		     }
		     if (SoundManager.Vez == 1) {
		    	 mSoundPool.play(random(mBlueList[1]), streamVolume, streamVolume, 1, 0, 1f); 
		     }
		     if (SoundManager.Vez == 2) {
		    	 mSoundPool.play(random(mBlueList[2]), streamVolume, streamVolume, 1, 0, 1f); 
		     }
		     if (SoundManager.Vez == 3) {
		    	 mSoundPool.play(mrandom(mBlueList[3]), streamVolume, streamVolume, 1, 0, 1f); 
		     }
		}
		
		if (color == Color.GREEN){
		     int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
		     if (SoundManager.Vez == 0) {
		    	 mSoundPool.play(random(mGreenList[0]), streamVolume, streamVolume, 1, 0, 1f); 
		    	 SoundManager.Vez = 1;
		     }
		     if (SoundManager.Vez == 1) {
		    	 mSoundPool.play(random(mGreenList[1]), streamVolume, streamVolume, 1, 0, 1f); 
		    	 SoundManager.Vez = 2;
		     }
		     if (SoundManager.Vez == 2) {
		    	 mSoundPool.play(random(mGreenList[2]), streamVolume, streamVolume, 1, 0, 1f); 
		    	 SoundManager.Vez = 3;
		     }
		     if (SoundManager.Vez == 3) {
		    	 mSoundPool.play(random(mGreenList[3]), streamVolume, streamVolume, 1, 0, 1f); 
		    	 SoundManager.Vez = 0;
		     }
		}

	}
	
}