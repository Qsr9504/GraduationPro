package com.qsr.graduationpro;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

	@Bind(R.id.mainFrame)
	FrameLayout mainFrame;
	@Bind(R.id.slidingAvatar)
	ImageView slidingAvatar;
	@Bind(R.id.myPhone)
	TextView myPhone;
	@Bind(R.id.myCV)
	TextView myCV;
	@Bind(R.id.myFamily)
	TextView myFamily;
	@Bind(R.id.myFamous)
	TextView myFamous;
	@Bind(R.id.drawer_ll)
	LinearLayout drawerLl;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		//初始化主界面
		View view = View.inflate(this,R.layout.main_family,null);
		mainFrame.addView(view);
	}

	@Override
	protected void AfterInitView() {
		initNode();
	}
	//初始化当前用户结点
	private void initNode() {

	}

	@Override
	protected void notify(Action action) {

	}

	@Override
	public void MyOnClick(View v) {
		switch (v.getId()) {
			case R.id.mainDidi://点击弟弟
				ToastUtil.showShort("点击弟弟");
				break;
			case R.id.mainBor://点击哥哥
				ToastUtil.showShort("点击哥哥");
				break;
			case R.id.mainFather://点击父亲
				ToastUtil.showShort("点击父亲");
				break;
			case R.id.mainChild://点击孩子
				ToastUtil.showShort("点击孩子");
				break;
		}
	}

}
