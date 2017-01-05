package com.qsr.graduationpro.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.App;

import butterknife.Bind;
import butterknife.OnClick;

/**************************************
 * FileName : com.qsr.graduationpro.ui_footer_tab
 * Author : qsr
 * Time : 2017/1/4 22:38
 * Description : 底部导航栏的一个按钮（图片加文字）
 **************************************/
public class MyFooterTab extends LinearLayout implements View.OnClickListener{
	@Bind(R.id.tab_pic)
	TextView tabPic;
	@Bind(R.id.tab_text)
	TextView tabText;
	private View view = null;
	private boolean selected = false;
	public MyFooterTab(Context context) {
		this(context, null);
	}

	public MyFooterTab(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyFooterTab(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setClickable(false);
		initView(context, attrs, defStyleAttr);
	}

	private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
		if (view == null) {
			view = View.inflate(context, R.layout.ui_footer_tab, null);
			this.setClickable(true);
		}
	}

	@Override
	public void onClick(View v) {
		changeTab();
	}
	public void chooseTab(){
		selected = true;
	}
	private void changeTab() {
		if(selected == false){
			tabPic.setTextColor(App.mContext.getResources().getColor(R.color.footerGray));
			tabText.setTextColor(App.mContext.getResources().getColor(R.color.footerGray));
			selected = true;
		}else {
			tabPic.setTextColor(App.mContext.getResources().getColor(R.color.gold));
			tabText.setTextColor(App.mContext.getResources().getColor(R.color.gold));
		}
	}
}
