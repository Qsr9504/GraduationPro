package com.qsr.graduationpro.mvp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.bmobUtils.VersionTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.Version;
import com.qsr.graduationpro.mvp.presenter.SplashPresenter;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**************************************
 * FileName : com.qsr.graduationpro.activities
 * Author : qsr
 * Time : 2017/1/1 22:47
 * Description : 欢迎页面
 * 要求功能：
 * 1.沉浸式标题栏  √
 * 2.首次打开app时，需要进入引导页。之后跳转loginActivity  √
 * 3.开启版本检查时，检查版本信息跳出dialog   √
 * 4.关闭版本检查时，默认三秒后自动进入LoginActivity，点击屏幕立即进入LoginActivity √
 * 5.跳转下页面 ，一个翻书 一样的动画效果
 **************************************/
public class SplashActivity extends BaseActivity {
	@Bind(R.id.splash_image)
	ImageView splashImage;
	private boolean isFrist = true;//是否是第一次进入app
	private final int ENTER_HOME = 101;
	private final int DELAY_TIME = 3000;//延迟加载进入页面时间
	private boolean hasEnter = true;//是否已经进行跳转，防止重复跳转出现bug
	private Version version;
	private SplashPresenter splashPresenter;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ENTER_HOME:
					//进入应用程序主界面,activity跳转过程
					enter();
					break;
			}
		}
	};

	@Override
	protected int getLayoutId() {
		return R.layout.activity_spash;
	}

	@Override
	protected void init() {
		ActivityManager.getInstance().addActivity(this);
		//检测版本信息
		LogUtil.MyLog_e("准备版本检测");
		splashImage.setClickable(false);//设置为不可点击
		//初始化一个控制器
		splashPresenter = new SplashPresenter();
		//注册
		splashPresenter.registerPresenterListener(this);
		//执行访问
		splashPresenter.requestAction(Constants.eventString.EVENT_VERSION);
	}

	//接受返回数据刷新界面
	@Override
	protected void notify(Action action) {
		if (Constants.eventString.EVENT_VERSION.equals(action.getEvent())) {
			//检测版本  返回信息
			LogUtil.MyLog_e("界面收到版本消息");
			if (action.getState() == Constants.stateCode.STATE_SUCCESS) {
				if (action.getResultData() != null){
					//有更新的版本
					version = (Version) action.getResultData();
					showDialog();
				}else {
					//已经是最新版本
					ToastUtil.showShort("已经是最新版本");
					splashImage.setClickable(true);//设置为可以点击
					handler.sendEmptyMessageDelayed(ENTER_HOME, DELAY_TIME);
				}
			} else{
				if (action.getState() == Constants.stateCode.STATE_ERROR) {
					ToastUtil.showLong("检测版本返回数据出错"+action.getDescribe());
					enter();
				}
			}
		} else {//就没有接收到versionTool的返回数据
			ToastUtil.showLong("检测版本失败");
			enter();
		}
	}

	/**
	 * 跳转至登陆注册界面 或引导页
	 * 1.判断执行是否是第一次进入app
	 */
	private void enter() {
		if(hasEnter){
			isFrist = (boolean) SPUtil.get(Constants.mySP.IS_FRIST, true);
			if (isFrist) {
				//进入引导页
				ActivityManager.getInstance().startAct(this, new GuideActivity());
			} else {
				//跳转进入登录注册
				ActivityManager.getInstance().startAct(this, new LoginActivity());
			}
			hasEnter = false;//防止重复启动新界面，出现bug
		}
	}

	//弹出更新对话框
	private void showDialog() {
		//对话框,是依赖于activity存在的
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		setFinishOnTouchOutside(false);
		//设置左上角图标
		builder.setIcon(R.mipmap.icon);
		builder.setTitle("检测到新版本: " + version.getVersionName());
		//设置描述内容
		builder.setMessage(version.getVersionDesc());
		//确定按钮,立即更新
		builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//下载apk,apk链接地址,downloadUrl
				downloadApk(version.getVersionUrl());
			}
		});

		builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				LogUtil.MyLog_e("点击了dialog 以后再说");
				//取消对话框,进入主界面
				enter();
			}
		});
		//点击取消事件监听
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				LogUtil.MyLog_e(SplashActivity.this, "点击了dialog 取消按钮");
				dialog.dismiss();
				enter();
			}
		});
		if ((Boolean) SPUtil.get(Constants.mySP.OPEN_CHECK, true))//默认打开
			builder.show();
		else {
			splashImage.setClickable(true);
			//延迟三秒登录注册界面
			handler.sendEmptyMessageDelayed(ENTER_HOME, DELAY_TIME);
		}
	}
	//*********************
	//*********************
	//*******下载新版本****
	//********************
	//********************
	private void downloadApk(String versionUrl) {

	}
	@OnClick(R.id.splash_image)
	public void splashClick(View view){
		enter();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
