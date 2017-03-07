package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Version;
import com.qsr.graduationpro.utils.AppUtil;
import com.qsr.graduationpro.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/3/5 15:26
 * Description : 本软件的版本更新信息校对
 **************************************/
public class VersionTool extends BaseTool{
	private static VersionTool versionTool;
	private Integer maxCode;

	//第一层同步
	public static synchronized VersionTool getInstance() {
		//第二层同步
		synchronized (VersionTool.class) {
			if(versionTool == null){
				versionTool = new VersionTool(App.mContext);
			}
		}
		return versionTool;
	}
	private VersionTool (Context context){
		this.context = context;
	}
	//去bmob后端云进行检查版本信息
	public void goCheck() {
		action.setEvent(Constants.eventString.EVENT_VERSION);
		LogUtil.MyLog_e("进来VersionTool中goCheck");
		BmobQuery<Version> query = new BmobQuery<Version>();
		//最大版本号获取
		maxCode = AppUtil.getVersionCode();
		LogUtil.MyLog_e("当前版本号是"+ AppUtil.getVersionCode());
		//查询比当前版本号更大的是否存在(设置查询条件)
		query.addWhereGreaterThan("versionCode",maxCode);
		//进行查询
		query.findObjects(App.mContext, new FindListener<Version>() {
			@Override
			public void onSuccess(List<Version> list) {
				LogUtil.MyLog_e("版本检测成功返回"+list.toString());
				action.setState(Constants.stateCode.STATE_SUCCESS);//设置状态码
				if(list.isEmpty()){
					LogUtil.MyLog_e("返回的数据是空的，没有更新版本");
				}else {
					for (Version v : list) {
						if (v.getVersionCode() > maxCode) {
							action.setResultData(v);//设置返回对象
						} else {
							action.setResultData(null);
						}
					}
				}
				bmobInterface.BmobCallBack(action);
			}

			@Override
			public void onError(int i, String s) {
				action.setState(Constants.stateCode.STATE_ERROR);//状态码设置为失败
				action.setDescribe(s);//设置错误返回信息
				LogUtil.MyLog_e("版本检测失败:"+s);//打印一下错误信息

				//使用接口返回数据
				bmobInterface.BmobCallBack(action);
			}
		});
	}

}
