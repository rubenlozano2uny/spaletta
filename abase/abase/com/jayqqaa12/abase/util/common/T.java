package com.jayqqaa12.abase.util.common;

import android.widget.Toast;

import com.jayqqaa12.abase.core.Abase;


/**
 * 
 * Toast
 * 
 * @author  jayqqaa12
 *
 */
public class T  
{
	public static void show(String msg ,int time)
	{
		Toast.makeText(Abase.getContext(),msg,time).show();
	}
	
	public static void ShortToast(String msg)
	{
		Toast.makeText(Abase.getContext(),msg,Toast.LENGTH_SHORT).show();
	}
	
	
	public static void ShortToast(int msgId)
	{
		Toast.makeText(Abase.getContext(),Abase.getContext().getText(msgId),Toast.LENGTH_SHORT).show();
	}
	
	
	
	public static void LongToast( String msg)
	{
		
		Toast.makeText(Abase.getContext(),msg,Toast.LENGTH_LONG).show();
	}
	

	
	
	


}
