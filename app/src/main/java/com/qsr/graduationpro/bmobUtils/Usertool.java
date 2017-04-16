package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import java.util.List;

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
	private static List<String> list;
	//双层同步
	public static synchronized UserTool getInstance(){
		synchronized (UserTool.class) {
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
		list = (List<String>) action.getRequestData();
		bmobUser.setUsername(list.get(0));
		bmobUser.setPassword(list.get(1));
		bmobUser.login(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("登陆成功");
				action.setEvent(Constants.eventString.EVENT_LOGIN);
				action.setState(Constants.stateCode.STATE_SUCCESS);//登陆成功
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("登录失败！\n" + s);
				action.setState(Constants.stateCode.STATE_ERROR);//登录失败
				bmobInterface.BmobCallBack(action);
			}
		});
	}
	public void doRegister(final Action action){
		list = (List<String>) action.getRequestData();
		bmobUser.setUsername(list.get(0));
		bmobUser.setPassword(list.get(1));
		LogUtil.MyLog_e("bmob工具类注册这里收到:"+ list.get(0) + " - " +list.get(1));
		bmobUser.signUp(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("注册成功");
				action.setEvent(Constants.eventString.EVENT_REGISTER);
				action.setState(Constants.stateCode.STATE_SUCCESS);//注册成功
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("注册失败"+s);
				action.setState(Constants.stateCode.STATE_ERROR);//注册失败
				bmobInterface.BmobCallBack(action);
			}
		});
	}
	public void doUserNode(){
		User user = BmobUser.getCurrentUser(context,User.class);
		UserNode userNode = new UserNode(user,false,true);
		userNode.save(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("添加当前用户结点成功");
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("添加用户结点失败"+s);
			}
		});
	}
}
