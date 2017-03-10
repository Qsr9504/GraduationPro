package com.qsr.graduationpro.mvp.model.biz;

import com.qsr.graduationpro.base.BaseBiz;
import com.qsr.graduationpro.bmobUtils.BmobInterface;
import com.qsr.graduationpro.bmobUtils.UserTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.utils.LogUtil;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.biz
 * Author : qsr
 * Time : 2017/3/7 22:28
 * Description : 登录注册使用
 **************************************/
public class LoginBiz extends BaseBiz{
	@Override
	public void setAction(Action action) {
		super.action = action;
	}
	public void doLogin(){
		User user = (User) action.getRequestData();
		LogUtil.MyLog_e("用户名" + user.getUsername().toString());
		LogUtil.MyLog_e("密码" + user.getPassword().toString());
		//转交给bmob后端云
		UserTool.getInstance().doLogin(action);
		//注册当前的监听接口，让父类方法进行回调
		UserTool.getInstance().registerBmobInterface(LoginBiz.this);
	}
}