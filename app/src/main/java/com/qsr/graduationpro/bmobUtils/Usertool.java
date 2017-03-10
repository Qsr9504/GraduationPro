package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/3/5 14:47
 * Description : 用户登录注册  连接bmob后端云的封装
 **************************************/
public class UserTool extends BaseTool{
	private BmobUser bmobUser;
	private static UserTool userTool;
	//双层同步
	public static synchronized UserTool getInstance(){
		synchronized (VersionTool.class) {
			if (userTool == null) {
				userTool = new UserTool(App.mContext);
			}
		}
		return userTool;
	}
	private UserTool(Context context){
		this.context = context;
		bmobUser = new BmobUser();
	}

	public void doLogin(final Action action){
		User user = (User) action.getRequestData();
		bmobUser.setUsername(user.getUsername());
		bmobUser.setPassword(user.getPassword());
		bmobUser.login(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("登陆成功");
				action.setState(Constants.stateCode.STATE_SUCCESS);//登陆成功
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("登录失败！\n" + s);
				bmobInterface.BmobCallBack(action);
			}
		});
	}
}
