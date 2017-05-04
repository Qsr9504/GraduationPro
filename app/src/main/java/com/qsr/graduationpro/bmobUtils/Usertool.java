package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.google.gson.Gson;
import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
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
	private Map map;
	private Gson gson = new Gson();
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
		map = new HashMap();
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
				doUserNode(list.get(0));
			}

			@Override
			public void onFailure(int i, String s) {
				LogUtil.MyLog_e(s);
				action.setState(Constants.stateCode.STATE_ERROR);//注册失败
				bmobInterface.BmobCallBack(action);
			}
		});
	}
	//添加结点，仅注册时调用
	private void doUserNode(final String username){
		final UserNode userNode = new UserNode(username, false, true);
		userNode.save(context, new SaveListener() {
			@Override
			public void onSuccess() {
				ToastUtil.showShort("添加当前用户结点成功");
				action.setEvent(Constants.eventString.EVENT_REGISTER);
				action.setState(Constants.stateCode.STATE_SUCCESS);//注册成功
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onFailure(int i, String s) {
				ToastUtil.showShort("添加用户结点失败"+s);
			}
		});
	}


	//查询结点
	public void getUserNode(final String username){
		BmobQuery<UserNode> query = new BmobQuery<UserNode>();
		query.addWhereEqualTo("mySelf",username);
		query.findObjects(context,new FindListener<UserNode>(){
			@Override
			public void onSuccess(List<UserNode> list) {
				LogUtil.MyLog_e("根据用户名称查询到该节点数量为："+list.size()+"节点内容是="+list.toString());
				if(list.size() == 0){
					getUserNode(username);
				}else {
					SPUtil.put(Constants.mySP.USERNODE,gson.toJson(list.get(0)));
					action.setEvent(Constants.enevtBus.BUS_MAIN_USERNODE);
					action.setResultData(list.get(0));
					EventBus.getDefault().post(action);
				}
			}

			@Override
			public void onError(int i, String s) {
				LogUtil.MyLog_e("登录获取结点发生错误"+s);
			}
		});
	}
	//查询用户
	public void getUser(final String username){
		LogUtil.MyLog_e("正在根据用户名查询当前用户信息");
		BmobQuery<User> query = new BmobQuery<User>();
		query.addWhereEqualTo("username", username);
		query.findObjects(context,new FindListener<User>() {
			@Override
			public void onSuccess(List<User> list) {
				action.setEvent(Constants.enevtBus.BUS_MAIN_USER);
				if(list.size() == 0)
					getUser(username);
				action.setResultData(list.get(0));
				EventBus.getDefault().post(action);
			}

			@Override
			public void onError(int i, String s) {
				LogUtil.MyLog_e("登录获取用户发生错误"+s);
			}
		});
	}
}
