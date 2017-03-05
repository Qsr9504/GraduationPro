package com.qsr.graduationpro.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.qsr.graduationpro.app.App;

/**************************************
 * FileName : com.qsr.utilsdemo.utils
 * Author : qsr
 * Time : 2017/1/6 18:24
 * Description : 当前应用信息工具类
 **************************************/
public class AppUtil {
	private static Context context = App.mContext;
	private AppUtil()
	{
        /* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * @return 获取应用程序名称 String
	 */
	public static String getAppName()
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return 获取应用程序 版本名称 String
	 */
	public static String getVersionName()
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return 获取当前应用 程序版本号 int
	 */
	public static int getVersionCode()
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionCode;

		} catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
