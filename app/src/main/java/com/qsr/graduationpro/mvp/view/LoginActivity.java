package com.qsr.graduationpro.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a.a.V;
import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.presenter.LoginPresenter;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
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
			//如果返回成功
			LogUtil.MyLog_e("登录界面收到登录成功消息");
		}else {
			LogUtil.MyLog_e("界面收到消息  登录失败");
		}
	}

	@Override
	public void MyOnClick(View v) {
		switch (v.getId()){
			case R.id.login_btn://点击登录按钮
				doCheckLoginMes();
				break;
			case R.id.register_btn://点击注册按钮
				loginLinea.setVisibility(View.GONE);
				loginBtn.setVisibility(View.GONE);
				registerLinea.setVisibility(View.VISIBLE);
				registerBtnGroup.setVisibility(View.VISIBLE);
				break;
			case R.id.confirm_btn://确认注册信息按钮
				doCheckRegisterMes();
				break;
		}
	}
	//检查注册输入的信息是否合法
	private void doCheckRegisterMes() {

	}
	//检查登录输入的信息是否合法
	private void doCheckLoginMes() {
		String account = loginAccount.getText().toString().trim();
		String password = loginPassword.getText().toString().trim();
		//检查是否为空字符串
		if(!TextUtil.isEmpty(account,password)){
			//生成事件
			user = new User();
			user.setUsername(account);
			user.setPassword(password);
			action = new Action(Constants.eventString.EVENT_LOGIN);
			action.setRequestData(user);
			//执行登录操作
			loginPresenter.requestAction(action);
		}else {
			ToastUtil.showShort("账户名或密码不能为空");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
