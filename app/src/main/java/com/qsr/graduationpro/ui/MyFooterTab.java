package com.qsr.graduationpro.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.utils.LogUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**************************************
 * FileName : com.qsr.graduationpro.ui_footer_tab
 * Author : qsr
 * Time : 2017/1/4 22:38
 * Description : 底部导航栏的一个按钮（图片加文字）
 **************************************/
public class MyFooterTab extends LinearLayout{
	private IconFontTextView tabPic;
	private TextView tabText;
	private String pic;
	private String text;
	private int color;
	private View view = null;

	private boolean selected = false;
	public MyFooterTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context,attrs);
	}

	public MyFooterTab(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setClickable(false);
		initView(context,attrs);
	}

	private void initView(Context context, AttributeSet attrs) {
		if (view == null) {
			view = View.inflate(context, R.layout.ui_footer_tab, null);
			this.setClickable(true);
			addView(view);
		}
		tabPic = (IconFontTextView) view.findViewById(R.id.tab_pic);
		tabText = (TextView) view.findViewById(R.id.tab_text);
		//找到自定义的属性文件
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyFooterTab);
		//取出相应信息
		pic = ta.getString(R.styleable.MyFooterTab_tabPic);
		text = ta.getString(R.styleable.MyFooterTab_tabText);
		LogUtil.MyLog_e(context,pic+"----"+text);
		color = ta.getColor(R.styleable.MyFooterTab_tabColor,getResources().getColor(R.color.backgroundGray));
		//适配对应属性
		tabPic.setIcon(pic);
		tabText.setText(text);
//		this.setBackgroundColor(getResources().getColor(R.color.white));
		//回收ta
		ta.recycle();
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
	private void setData(String tabpic){
		tabpic.replace("&#x","\\u");
		tabPic.setText(tabpic);
	}
}
