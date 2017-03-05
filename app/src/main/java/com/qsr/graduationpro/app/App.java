package com.qsr.graduationpro.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.graphics.drawable.animated.BuildConfig;

import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.Bmob;

/**************************************
 * FileName : com.qsr.graduationpro.app
 * Author : qsr
 * Time : 2017/1/1 23:14
 * Description :
 **************************************/
public class App extends Application {
	public static Context mContext = null;
	public static Handler handler = null;
	public static Thread mainThread = null;
	public static int mainThreadId = 0;//主线程id
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		handler = new Handler();
		mainThread = Thread.currentThread();
		mainThreadId = android.os.Process.myPid();
		initUtils();
	}
	/**
	 * 各种工具类的初始化
	 */
	private void initUtils() {
		Bmob.initialize(this, "cbb65ad58b533580a39ffe7f879b388d");
		EventBus eventBus = EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).build();
		//SharedPreferences初始化
		SPUtil.init(mContext,"Gpro_config");
		//log信息是否打印
		LogUtil.openLog(true);
		//全局异常捕获处理器
//		AppCrashHandle.getInstance().init(this);
		//网络切换监听器
	}


}
