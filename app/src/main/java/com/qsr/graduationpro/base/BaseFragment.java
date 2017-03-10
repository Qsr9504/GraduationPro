package com.qsr.graduationpro.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**************************************
 * FileName : com.qsr.graduationpro.base
 * Author : qsr
 * Time : 2017/3/6 22:29
 * Description : Fragment 基类
 **************************************/
public abstract class BaseFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(getResourcesId(),null);
		init();
		return view;
	}

	protected abstract int getResourcesId();

	protected abstract void init();
}
