package com.jayqqaa12.abase.core.activity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import com.jayqqaa12.abase.core.Abus;

import android.app.Activity;
import android.os.Bundle;

@EActivity
public class AbaseHttpActivity extends Activity
{
	@Bean
	Abus bus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		bus.register(this);
	}
	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		bus.unregister(this);
	}
	
	
	@AfterInject
	protected void afterInject(){
		
	}
 
	
	@AfterViews
	protected void afterView()
	{
		init();
		connect();
	}
	
	protected void init(){
	}
	protected void connect()
	{
	}

	

	

}
