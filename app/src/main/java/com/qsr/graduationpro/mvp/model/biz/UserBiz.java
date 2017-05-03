package com.qsr.graduationpro.mvp.model.biz;

import com.qsr.graduationpro.base.BaseBiz;
import com.qsr.graduationpro.bmobUtils.UserTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.biz
 * Author : qsr
 * Time : 2017/4/20 13:21
 * Description :
 **************************************/
public class UserBiz extends BaseBiz{
	@Override
	public void setAction(Action action) {
		super.action = action;
	}
	//根据当前用户获取当前结点
	public void getUserNodeInfo(){
		//注册当前的监听接口，让父类方法进行回调
		UserTool.getInstance().registerBmobInterface(UserBiz.this);

		UserTool.getInstance().getUserNode((User) action.getRequestData());
	}
}
