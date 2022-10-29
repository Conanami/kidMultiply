package com.example.kidmultiply;

import java.util.Random;

import com.example.kidmultiply.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	public java.util.Random rand;
	public TextView num1;
	public TextView num2;
	public TextView multi_mark;
	public TextView equal_mark;
	public EditText answer;
	public TextView comments;
	public LinearLayout backpic;
	public Button submit;
	public Integer x,y;
	public Integer intAnswer;
	public Integer counter,rightCnt,totalCnt;
	public MediaPlayer mp;
	public Boolean dingzhen_flag;
	private Chronometer timer;
	static float mysize;
	private String congratcount;
	boolean congratflag;
	
	
	
	
	
	public String soundarray[]={"shuxueshen","zhenbang","youduile","lianduisan",			
			"xiaotiancai","zuocuole","cuole","zaixiang"};
	int picarray[]={R.drawable.cuxin,R.drawable.huaxin,R.drawable.kaixin,R.drawable.xiaoxin};
	
	public static int adjustFontSize(int ScreenW , int ScreenH)
	{
		if ( ScreenH > ScreenW )
		{
			if (ScreenW<=240)
			{
				return 18;
			}
			else if (ScreenW <= 320 )
			{
				return 25;
			}
			else if ( ScreenW <= 480 )
			{
				return 30;
			}
			else if ( ScreenW <= 560 )
			{
				return 40;
			}
			else if ( ScreenW <= 800 )
			{
				return 60;
			}
			else
			{
				return 70;
			}
		}else
		{
			if (ScreenW<=240)
			{
				return 9;
			}
			else if (ScreenW <= 320 )
			{
				return 12;
			}
			else if ( ScreenW <= 480 )
			{
				return 15;
			}
			else if ( ScreenW <= 560 )
			{
				return 20;
			}
			else if ( ScreenW <= 800 )
			{
				return 30;
			}
			else
			{
				return 35;
			}
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int screenWidth,screenHeight;
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		mysize=adjustFontSize(screenWidth,screenHeight);
		
		
		
		
		
		counter = 0;   //连对次数
		rightCnt = 0;  //总共对几题
		totalCnt = 0;  //全部题数
		
		submit=(Button)findViewById(R.id.btSummit);
		comments=(TextView)findViewById(R.id.textView5);
		
		answer=(EditText)findViewById(R.id.editText1);
		
		timer=(Chronometer)findViewById(R.id.chronometer1);
		
		
		timer.setTextSize(mysize);
		timer.setBase(SystemClock.elapsedRealtime());
		timer.start();
		
		newQuestion();
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String tmpAnswer;
				SharedPreferences getPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				congratcount = getPref.getString("list", "10");
				congratflag = getPref.getBoolean("checkbox", true);
				
				try
				{
					tmpAnswer= answer.getText().toString();
					intAnswer= Integer.parseInt(tmpAnswer);
					
					if (intAnswer == x*y)
					{
						//comments.setText("正确！你太聪明了！！");
						counter ++;
						if(dingzhen_flag==false)
		                {
		                	rightCnt++;
		                }
						if (rightCnt >= Integer.parseInt(congratcount)) 
						{
							Intent ourIntent = new Intent("com.example.kidmultiply.CONGRAT");
							ourIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(ourIntent);
						}
						
							int mp3Resource = getResources().getIdentifier(soundarray[counter%5], "raw" , "com.example.kidmultiply");
							mp = MediaPlayer.create(MainActivity.this, mp3Resource );
							if(congratflag)
								mp.start();
		                    newQuestion();
					}
		            
					else
					{
					
						int wrong_num=rand.nextInt(3);
						int mp3Resource = getResources().getIdentifier(soundarray[5+wrong_num], "raw" , "com.example.kidmultiply");
		                mp = MediaPlayer.create(MainActivity.this, mp3Resource );
		                if(congratflag)
		                	mp.start();
						counter = 0;
						dingzhen_flag=true;
						submit.setText("订正");
						answer.setText("");
						
					}	
					comments.setText("共"+(totalCnt-1)+"题，做对了"+rightCnt+"题");
					//mp.release();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		
		
	}

	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
			submit.setText("转转!");
		else if (this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)
			submit.setText("再转转!");
		
	}



	public void newQuestion()
	{
		
		
		rand = new Random();
		x=rand.nextInt(8)+2;
		y=rand.nextInt(8)+2;
		int pic=rand.nextInt(4);
		String showX,showY;
		
		
		backpic=(LinearLayout)findViewById(R.id.layout1);
		backpic.setBackgroundResource(picarray[totalCnt%4]);
		
		num1=(TextView)findViewById(R.id.textView1);
		num2=(TextView)findViewById(R.id.textView3);
		multi_mark=(TextView)findViewById(R.id.textView2);
		equal_mark=(TextView)findViewById(R.id.textView4);
		answer.setText("");
		answer.setFocusable(true);
		
		showX=String.valueOf(x);
		showY=String.valueOf(y);

		num1.setText(showX);
		num1.setTextSize(mysize);
		
		num2.setText(showY);
		num2.setTextSize(mysize);
		
		multi_mark.setTextSize(mysize);
		equal_mark.setTextSize(mysize);
		answer.setTextSize(mysize);
		submit.setText("好了");
		submit.setTextSize(mysize);
		totalCnt++;
		dingzhen_flag=false;
		comments.setTextSize(mysize);
		
	
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
		case R.id.preferences:
			Intent i = new Intent("com.example.kidmultiply.PREFS");
			startActivity(i);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return true;
	}
	
	
	


	


}
