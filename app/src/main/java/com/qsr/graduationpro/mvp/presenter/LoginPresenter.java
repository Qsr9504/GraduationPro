package com.qsr.graduationpro.mvp.presenter;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BasePresenter;
import com.qsr.graduationpro.mvp.model.biz.LoginBiz;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.utils.LogUtil;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.presenter
 * Author : qsr
 * Time : 2017/3/7 22:22
 * Description : 登录注册界面的控制器
 **************************************/
public class LoginPresenter extends BasePresenter{
//	private LoginBiz loginBiz = null; 与下边那个语句是不一样的！！！！为什么！
	private LoginBiz loginBiz;
	@Override
	public void init() {
		LogUtil.MyLog_e("来过");
		//声明一个biz
		if(loginBiz == null){
			loginBiz = new LoginBiz();
			LogUtil.MyLog_e("login实例化");
		}
		//注册
		loginBiz.registerBizListener(this);
	}

	@Override
	public void requestAction(Action action) {
		LogUtil.MyLog_e("执行LoginPresenter中requestAction");
		if(loginBiz == null){
			LogUtil.MyLog_e("操！这里是LoginPresenter");
		}
		loginBiz.setAction(action);//设置action信息
		if(Constants.eventString.EVENT_LOGIN.equals(action.getEvent())){
			loginBiz.doLogin();//执行登录操作
		}else if(Constants.eventString.EVENT_REGISTER.equals(action.getEvent())){
			loginBiz.doRegister();
		}
	}
}
