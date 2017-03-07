package com.qsr.graduationpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;

public class MainActivity extends BaseActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void init() {

	}

	@Override
	protected void notify(Action action) {

	}
}
