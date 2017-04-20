package com.example.testroundpb;

import java.util.Timer;
import java.util.TimerTask;

import view.RoundProgressBar;
import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	RoundProgressBar mRoundProgressBar;
	Button mBtn_add,mBtn_loop;
	Timer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}
	private void initView() {
		mRoundProgressBar=(RoundProgressBar) findViewById(R.id.pb);
		mBtn_add=(Button) findViewById(R.id.btn);
		mBtn_add.setOnClickListener(this);
		mBtn_loop=(Button) findViewById(R.id.btn_loop);
		mBtn_loop.setOnClickListener(this);
		mRoundProgressBar.setProgress(6);
		timer=new Timer();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			int newProgress=mRoundProgressBar.getProgress()+5;
			if (newProgress>100) {
				newProgress=0;
			}
			mRoundProgressBar.setProgress(newProgress);
			break;
		case R.id.btn_loop:
			 loop();
			
			break;
		default:
			break;
		}
	}
	
	private void loop() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				int newProgress2=mRoundProgressBar.getProgress()+3;
				if (newProgress2>100) {
					newProgress2=0;
				}
				mRoundProgressBar.setProgress(newProgress2);
				loop();
			}
		}, 100);
	}
}