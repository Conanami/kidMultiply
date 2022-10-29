package com.example.kidmultiply;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;

public class Splash extends Activity {

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//Intent ourIntent = new Intent("com.example.kidmultiply.MAINACTIVITY");
		Intent ourIntent = new Intent("com.example.kidmultiply.testScrollActivity");
		ourIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(ourIntent);
		
		return super.onTouchEvent(event);
	}



	MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		
		ourSong = MediaPlayer.create(Splash.this, R.raw.happysuper);
		ourSong.setLooping(true);
		if( savedInstanceState != null ){
            ourSong.seekTo(savedInstanceState.getInt("currPos"));
        }
        ourSong.start();
		
		
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		
		int myPos;
		//myPos=ourSong.getCurrentPosition();
		myPos=20000;
		outState.putInt("currPos", myPos);
		
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		ourSong.release();
	}


}
