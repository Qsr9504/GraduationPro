package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.mvp.model.data.Action;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/3/5 17:49
 * Description :
 **************************************/
public class BaseTool{
	protected BmobInterface bmobInterface;
	protected Action action = new Action();
	protected Context context = App.mContext;
	//设置bmob监听器
	public void registerBmobInterface(BmobInterface bmobInterface){
		this.bmobInterface = bmobInterface;
	}

}
