package com.example.kidmultiply;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

public class Congrat extends Activity  {
	
	RotateAnimation mRotateAnimation;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Intent ourIntent = new Intent("com.example.kidmultiply.MAINACTIVITY");
		ourIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(ourIntent);
		
		return super.onTouchEvent(event);
	}


	
	MediaPlayer ourSong;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.congrat);
		LinearLayout backpic;
		Random rand = new Random();
		backpic=(LinearLayout)findViewById(R.id.layout_congrat);
		int picarray[]={R.drawable.shangxin,R.drawable.shangxin2,R.drawable.shangxin3};
		backpic.setBackgroundResource(picarray[rand.nextInt(3)]);
		
		ourSong = MediaPlayer.create(Congrat.this, R.raw.congrat);
		ourSong.setLooping(true);
	    ourSong.seekTo(14000);
        ourSong.start();
        
//        mRotateAnimation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT, 0.5f);
//        mRotateAnimation.setAnimationListener(this);
//        mRotateAnimation.setDuration(3000);
//        mRotateAnimation.startNow();
        
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		ourSong.release();
	}

	

	

}
