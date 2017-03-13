package com.qsr.graduationpro.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a.a.V;
import com.qsr.graduationpro.MainActivity;
import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.presenter.LoginPresenter;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.LoginUtil;
import com.qsr.graduationpro.utils.TextUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {


	@Bind(R.id.icon)
	ImageView icon;
	@Bind(R.id.login_account)
	EditText loginAccount;
	@Bind(R.id.login_password)
	EditText loginPassword;
	@Bind(R.id.loginLinea)
	LinearLayout loginLinea;
	@Bind(R.id.register_account)
	EditText registerAccount;
	@Bind(R.id.register_password1)
	EditText registerPassword1;
	@Bind(R.id.register_password2)
	EditText registerPassword2;
	@Bind(R.id.registerLinea)
	LinearLayout registerLinea;
	@Bind(R.id.login_btn)
	Button loginBtn;
	@Bind(R.id.register_btn)
	Button registerBtn;
	@Bind(R.id.login_btnGroup)
	LinearLayout loginBtnGroup;
	@Bind(R.id.confirm_btn)
	Button confirmBtn;
	@Bind(R.id.register_btnGroup)
	LinearLayout registerBtnGroup;

	private LoginPresenter loginPresenter;
	private User user = null;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_login;
	}

	@Override
	protected void init() {
		ActivityManager.getInstance().addActivity(this);
		//申请一个登录注册控制器
		loginPresenter = new LoginPresenter();
		//注册
		loginPresenter.registerPresenterListener(this);
	}

	@Override
	protected void notify(Action action) {
		if(Constants.stateCode.STATE_SUCCESS == action.getState()){
			if(Constants.eventString.EVENT_LOGIN == action.getEvent()){
				//登录
				//如果返回成功
				LogUtil.MyLog_e("登录界面收到登录成功消息");
			}else if(Constants.eventString.EVENT_REGISTER == action.getEvent()){
				//注册成功
				LogUtil.MyLog_e("注册界面收到注册成功消息");
			}else {
				LogUtil.MyLog_e("登录注册返回代码有误！检查bmob工具类");
			}
			//跳转到主界面
			ActivityManager.getInstance().startAct(LoginActivity.this, new MainActivity());
		}else {
			LogUtil.MyLog_e("LoginActivity收到消息  登录或者注册失败");
		}
	}

	@Override
	public void MyOnClick(View v) {
		switch (v.getId()){
			case R.id.login_btn://点击登录按钮
				doCheckLoginMes();
				break;
			case R.id.register_btn:
			case R.id.register_login:
				changeView();
				break;
			case R.id.confirm_btn://确认注册信息按钮
				doCheckRegisterMes();
				break;
		}
	}
	//登录注册之间切换
	private void changeView() {
		if(loginLinea.getVisibility() == View.VISIBLE){
			loginLinea.setVisibility(View.GONE);
			loginBtn.setVisibility(View.GONE);
			registerBtn.setVisibility(View.GONE);
			registerLinea.setVisibility(View.VISIBLE);
			registerBtnGroup.setVisibility(View.VISIBLE);
		}else {
			loginLinea.setVisibility(View.VISIBLE);
			loginBtn.setVisibility(View.VISIBLE);
			registerBtn.setVisibility(View.VISIBLE);
			registerLinea.setVisibility(View.GONE);
			registerBtnGroup.setVisibility(View.GONE);
		}
	}

	//检查注册输入的信息是否合法
	private void doCheckRegisterMes() {
		String account = registerAccount.getText().toString().trim();
		String psw1 = registerPassword1.getText().toString().trim();
		String psw2 = registerPassword2.getText().toString().trim();
		if(LoginUtil.doCheckRegisterMes(account,psw1,psw2)){
			//注册信息验证通过
			//如果以上情况都没有，就进行注册
			action = new Action(Constants.eventString.EVENT_REGISTER);
			action.setRequestData(new User(account,psw1));
			loginPresenter.requestAction(action);
		}
	}
	//检查登录输入的信息是否合法
	private void doCheckLoginMes() {
		String account = loginAccount.getText().toString().trim();
		String password = loginPassword.getText().toString().trim();
		if(LoginUtil.doCheckLoginMes(account,password)){//登录检测通过
			action = new Action(Constants.eventString.EVENT_LOGIN);
			action.setRequestData(new User(account,password));
			//执行登录操作
			loginPresenter.requestAction(action);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
