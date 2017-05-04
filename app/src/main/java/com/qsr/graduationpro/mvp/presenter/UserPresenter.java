package com.qsr.graduationpro.mvp.presenter;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BasePresenter;
import com.qsr.graduationpro.mvp.model.biz.UserBiz;
import com.qsr.graduationpro.mvp.model.data.Action;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.presenter
 * Author : qsr
 * Time : 2017/4/20 13:20
 * Description :
 **************************************/
public class UserPresenter extends BasePresenter{
	private UserBiz userBiz;
	@Override
	public void init() {
		if(userBiz == null)
			userBiz = new UserBiz();
		userBiz.registerBizListener(this);
	}

	@Override
	public void requestAction(Action action) {
		userBiz.setAction(action);
		if(Constants.eventString.EVENT_USERNODE_BY_USER.equals(action.getEvent())){
			//查询用户结点
			userBiz.getUserNodeInfo();
		}
	}
}
