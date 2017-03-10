package com.qsr.graduationpro.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.view.adapter
 * Author : qsr
 * Time : 2017/3/6 23:30
 * Description : 引导页viewpager的适配器
 **************************************/
public class GuideAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;
	public GuideAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.fragmentList = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}
}
