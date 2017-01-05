package com.qsr.graduationpro.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.qsr.graduationpro.base
 * Author : qsr
 * Time : 2017/1/1 22:28
 * Description :Activity基类
 **************************************/
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(getLayoutId());
		initView(savedInstanceState);
		initData();
	}

	protected abstract int getLayoutId();

	protected abstract void initData();

	public void initView(Bundle savedInstanceState){}//需要初始化界面数据，恢复上一次数据

	@Override
	public void onClick(View v) {
		MyOnClick(v);
	}

	public void MyOnClick(View v) {
		//此处什么都不执行
	}


}
