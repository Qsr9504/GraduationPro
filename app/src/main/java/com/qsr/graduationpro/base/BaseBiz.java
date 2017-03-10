package com.qsr.graduationpro.base;

import android.media.Image;
import android.os.Handler;
import android.os.Message;

import com.qsr.graduationpro.bmobUtils.BmobInterface;
import com.qsr.graduationpro.mvp.model.biz.IBiz;
import com.qsr.graduationpro.mvp.model.data.Action;

/**************************************
 * FileName : com.qsr.graduationpro.base
 * Author : qsr
 * Time : 2017/3/7 11:11
 * Description :
 **************************************/
public abstract class BaseBiz implements BmobInterface{
	protected IBiz iBiz;
	protected Action action;

	private android.os.Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			iBiz.BizCallBack(action);//此处调用的是实例对象调用 接口中的函数
										// （实际上还是调用了实例对象实现的接口中的方法）
		}
	};

	public abstract void setAction(Action action);
	//此处注册了一个IBiz的接口，说明上文中 mListener 调用的是哪里的方法
	public void registerBizListener(IBiz listener){
		iBiz = listener;
	}
	public void bizCallBack(){
		handler.sendMessage(Message.obtain());
	}

	//接受bmob返回的数据
	@Override
	public void BmobCallBack(Action action) {
		//继续回调
		iBiz.BizCallBack(action);//这里下一个函数执行的是Base类中的函数
	}
}
