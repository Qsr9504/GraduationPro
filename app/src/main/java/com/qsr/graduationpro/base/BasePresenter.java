package com.qsr.graduationpro.base;

import com.qsr.graduationpro.mvp.model.biz.IBiz;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.presenter.IPresenter;

/**************************************
 * FileName : com.qsr.graduationpro.base
 * Author : qsr
 * Time : 2017/3/7 11:36
 * Description :
 **************************************/
public abstract class BasePresenter implements IBiz{
	protected IPresenter iPresenter;
	//控制器的初始化
	protected BasePresenter(){
		init();
	}

	public abstract void init();

	//注册控制器，初始化
	public void registerPresenterListener(IPresenter ipresenter){
		iPresenter = ipresenter;
	}

	public abstract void requestAction(Action action);

	@Override
	public void BizCallBack(Action action) {
		//继续回调
		iPresenter.presenterCallBack(action);//调用的是实例对象的具体方法函数
	}
}
