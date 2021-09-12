package com.jayqqaa12.abase.util;


import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;


/**
 * 有关 handler  msg 的 工具 
* @author jayqqaa12 
* @date 2013-6-8
 */
public class MsgUtil 
{
	public static final int MSG_ADD = 1;
	public static final int MSG_REMOVE = 2;
	public static final int MSG_SAVE = 3;
	public static final int MSG_DELETE = 4;
	public static final int MSG_UPDATE = 5;
	public static final int MSG_SERACH = 6;
	public static final int MSG_FLASH = 7;
	public static final int MSG_CREATE = 8;
	public static final int MSG_FAIL = 9;
	public static final int MSG_SUCCESS = 10;
	public static final int MSG_FINISH = 12;
	public static final int MSG_STOP = 13;
	public static final int MSG_PAUSE = 14;
	public static final int MSG_DESTORY = 15;
	public static final int MSG_UP = 16;
	public static final int MSG_DOWN = 17;
	public static final int MSG_RIGHT = 18;
	public static final int MSG_LEFT = 19;
	public static final int MSG_START = 20;
	public static final int MSG_FIND = 21;
	public static final int MSG_LOAD=22;
	public static final int MSG_REFRESH=23;
	public static final int MSG_INIT=24;
	
	
	public static void sendMessage(Callback callback, int what,Object obj){
		
		
		Message msg = new Message();
		msg.what = what;
		msg.obj =obj;
		callback.handleMessage(msg);
		
	}
	

	public static void sendMessage(Handler callback, int what)
	{
		Message msg = new Message();
		msg.what = what;
		msg.setTarget(callback);
		callback.sendMessage(msg);

	}


	public static void sendMessage(Handler callback, int what,int arg1)
	{
		Message msg = new Message();
		msg.what = what;
		msg.arg1=arg1;
		msg.setTarget(callback);
		callback.sendMessage(msg);

	}

	

	public static void sendMessage(Handler callback, int what, Object obj)
	{

		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		msg.setTarget(callback);
		callback.sendMessage(msg);

	}

}
