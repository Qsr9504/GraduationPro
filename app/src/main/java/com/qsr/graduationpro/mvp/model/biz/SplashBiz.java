package com.qsr.graduationpro.mvp.model.biz;

import com.qsr.graduationpro.base.BaseBiz;
import com.qsr.graduationpro.bmobUtils.BmobInterface;
import com.qsr.graduationpro.bmobUtils.VersionTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.Version;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.biz
 * Author : qsr
 * Time : 2017/3/7 11:46
 * Description :
 **************************************/
public class SplashBiz extends BaseBiz{
	@Override
	public void setAction(Action action) {
		action = action;
	}

	public void checkVersion(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				//先设置监听接口
				VersionTool.getInstance().registerBmobInterface(SplashBiz.this);
				//执行方法
				VersionTool.getInstance().goCheck();
			}
		}).start();
	}

	/**
	 * 下载apk文件的biz
	 */
	public void downLoadApk(){
	}
}
