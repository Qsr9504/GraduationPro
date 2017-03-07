package com.qsr.graduationpro.mvp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.view.adapter.GuideAdapter;
import com.qsr.graduationpro.mvp.view.fragments.FristFragment;
import com.qsr.graduationpro.mvp.view.fragments.SecondFragment;
import com.qsr.graduationpro.mvp.view.fragments.ThirdFragment;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * viewpager嵌套三个fragment
 */
public class GuideActivity extends BaseActivity {
	@Bind(R.id.guide_viewpager)
	ViewPager guideViewpager;
	private FragmentTransaction ft = null;
	private FragmentManager fm = null;
	private List<Fragment> fragmentList = null;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_guide;
	}

	@Override
	protected void init() {
		ActivityManager.getInstance().addActivity(this);
		//将进入过一次引导页的进行记录，防止下次再次跳转到引导页
		SPUtil.put(Constants.mySP.IS_FRIST, false);
		initData();
	}

	@Override
	protected void notify(Action action) {

	}

	private void initData() {
		fm = getSupportFragmentManager();//获取fragment管理器
		ft = fm.beginTransaction();//从 管理器中 获取fragment翻译器
		fragmentList = new ArrayList<Fragment>();
		//添加三个Fragment进去
		fragmentList.add(new FristFragment());
		fragmentList.add(new SecondFragment());
		fragmentList.add(new ThirdFragment());
		//为viewpager设置适配器
		guideViewpager.setAdapter(new GuideAdapter(fm,fragmentList));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);

	}

	@Override
	public void MyOnClick(View v) {
		switch (v.getId()){
			case R.id.third_fragment:
				ActivityManager.getInstance().startAct(this,new LoginActivity());
				break;
		}
	}
}
