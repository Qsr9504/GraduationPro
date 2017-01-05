package com.qsr.graduationpro.activities;

import android.os.Bundle;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.ui.MyFooterTab;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.qsr.graduationpro.activities
 * Author : qsr
 * Time : 2017/1/1 22:47
 * Description : 欢迎页面
 **************************************/
public class SplashActivity extends BaseActivity {
	@Bind(R.id.first)
	MyFooterTab first;
	@Bind(R.id.first1)
	MyFooterTab first1;
	@Bind(R.id.first2)
	MyFooterTab first2;
	@Bind(R.id.first3)
	MyFooterTab first3;

	@Override
	protected int getLayoutId() {
		ButterKnife.bind(this);
		return R.layout.activity_spash;
	}

	@Override
	protected void initData() {
		first.chooseTab();
	}
}
