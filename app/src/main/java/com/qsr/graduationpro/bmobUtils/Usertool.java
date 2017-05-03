package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
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
				doUserNode();
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
		final UserNode userNode = new UserNode(user, false, true);
		userNode.save(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("添加当前用户结点成功");
				action.setEvent(Constants.eventString.EVENT_REGISTER);
				action.setState(Constants.stateCode.STATE_SUCCESS);//注册成功
				action.setResultData(userNode);
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("添加用户结点失败"+s);
			}
		});
	}
	public void getUserNode(User user){
		final Action ac = new Action();
		BmobQuery<UserNode> query = new BmobQuery<UserNode>();
		LogUtil.MyLog_e("***"+user.toString()+"***");
		query.addWhereEqualTo("mySelf",user);
		query.include("mySelf");// 希望在查询结点信息的同时也把结点主人的信息查询出来
		query.findObjects(context,new FindListener<UserNode>(){
			@Override
			public void onSuccess(List<UserNode> list) {
				LogUtil.MyLog_e("查询到"+list.size()+"===="+list.toString());
				ac.setEvent(Constants.eventString.EVENT_USERNODE);
				ac.setState(Constants.stateCode.STATE_SUCCESS);
				ac.setResultData(list.get(0));
				bmobInterface.BmobCallBack(ac);
			}

			@Override
			public void onError(int i, String s) {
				ac.setEvent(Constants.eventString.EVENT_USERNODE);
				ac.setState(Constants.stateCode.STATE_ERROR);
				bmobInterface.BmobCallBack(ac);
			}
		});
	}

}
