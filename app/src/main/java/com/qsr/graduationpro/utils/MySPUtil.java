package com.qsr.graduationpro.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.qsr.graduationpro.app.App;

/**************************************
 * FileName : com.qsr.graduationpro.utils
 * Author : qsr
 * Time : 2017/1/3 20:41
 * Description : 内部配置存储
 **************************************/
public class MySPUtil {
	private MySPUtil mySPUtil = null;
	private static SharedPreferences sp = null;
	private static SharedPreferences.Editor editor = null;
	/**
	 * 将sp放入App 全局中实例化
	 */
	public static SharedPreferences getInstance() {
		if (sp == null) {
			sp = App.mContext.getSharedPreferences("gp_config", Context.MODE_PRIVATE);
			editor = sp.edit();
		}
		return sp;
	}
	/**
	 * 清除方法
	 */
	public static boolean clear() {
		editor.clear();
		return editor.commit();
	}
	//判断包括
	public static boolean contains(String key){
		return sp.contains(key);
	}
	//字符串存取
	public static boolean save(String key, String value){
		editor.putString(key,value);
		return editor.commit();
	}
	public static String getString(String key){
		return sp.getString(key,"no string");
	}


}
