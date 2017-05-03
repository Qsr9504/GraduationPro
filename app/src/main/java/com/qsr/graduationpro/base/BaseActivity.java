package com.qsr.graduationpro.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.presenter.IPresenter;
import com.qsr.graduationpro.utils.ActivityManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.qsr.graduationpro.base
 * Author : qsr
 * Time : 2017/1/1 22:28
 * Description :Activity基类
 **************************************/
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener,IPresenter{
	protected Message message;
	protected Action action = new Action();
	protected User user = new User();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//透到状态栏
		getWindow().addFlags(67108864);//沉浸式开发
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(getLayoutId());
		ButterKnife.bind(this);//注入式框架butterknife
		ActivityManager.getInstance().addActivity(this);
		initView(savedInstanceState);
		AfterInitView();
	}

	protected abstract int getLayoutId();

	protected abstract void AfterInitView();

	protected abstract void notify(Action action);

	public void initView(Bundle savedInstanceState){}//需要初始化界面数据，恢复上一次数据

	@Override
	public void onClick(View v) {
		MyOnClick(v);
	}

	public void MyOnClick(View v) {
		//此处什么都不执行,需要的让子类去实现
	}

	@Override
	public void presenterCallBack(Action action) {
		notify(action);//将回调信息返回给notify函数处理
	}
}
