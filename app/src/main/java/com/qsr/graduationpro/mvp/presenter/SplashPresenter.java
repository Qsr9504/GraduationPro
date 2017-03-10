package com.qsr.graduationpro.mvp.presenter;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BasePresenter;
import com.qsr.graduationpro.mvp.model.biz.SplashBiz;
import com.qsr.graduationpro.mvp.model.data.Action;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.presenter
 * Author : qsr
 * Time : 2017/3/7 11:43
 * Description :
 **************************************/
public class SplashPresenter extends BasePresenter {
	private SplashBiz splashBiz;
	@Override
	public void init() {
		splashBiz = new SplashBiz();
		//注册
		splashBiz.registerBizListener(this);
	}

	@Override
	public void requestAction(Action action) {
		//给biz层设置事件
		splashBiz.setAction(action);
		//判断意图为什么操作，交予那个biz执行
		if(Constants.eventString.EVENT_VERSION.equals(action.getEvent())){
			//版本检测操作
			splashBiz.checkVersion();
		}
	}
}
