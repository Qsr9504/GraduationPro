package com.qsr.graduationpro;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.mvp.presenter.UserPresenter;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {

	@Bind(R.id.mainFrame)
	FrameLayout mainFrame;
	@Bind(R.id.slidingAvatar)
	ImageView slidingAvatar;
	@Bind(R.id.myPhone)
	TextView myPhone;
	@Bind(R.id.myCV)
	TextView myCV;
	@Bind(R.id.myFamily)
	TextView myFamily;
	@Bind(R.id.myFamous)
	TextView myFamous;
	@Bind(R.id.drawer_ll)
	LinearLayout drawerLl;

	ImageView mainAvatar;
	private TextView mainName;
	private TextView mainSex;
	private TextView mainBirth;
	private TextView mainIsFame;
	private CardView mainCardView;
	private TextView mainFather;
	private TextView mainChild;
	private TextView mainBor;
	private TextView mainDidi;

	private UserNode userNode;
	private UserPresenter userPresenter;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		//初始化主界面
		View view = View.inflate(this, R.layout.main_family, null);
		mainAvatar = (ImageView) view.findViewById(R.id.mainAvatar);
		mainName = (TextView) view.findViewById(R.id.mainName);
		mainSex = (TextView) view.findViewById(R.id.mainSex);
		mainBirth = (TextView) view.findViewById(R.id.mainBirth);
		mainIsFame = (TextView) view.findViewById(R.id.mainIsFame);
		mainFather = (TextView) view.findViewById(R.id.mainFather);
		mainChild = (TextView) view.findViewById(R.id.mainChild);
		mainBor = (TextView) view.findViewById(R.id.mainBor);
		mainDidi = (TextView) view.findViewById(R.id.mainDidi);
		mainFrame.addView(view);
	}

	@Override
	protected void AfterInitView() {
		userPresenter = new UserPresenter();
//		if() {
			//如果本地缓存不存在
			action = new Action(Constants.eventString.EVENT_USERNODE);
			action.setRequestData(BmobUser.getCurrentUser(this, User.class));//携带当前用户信息
			userPresenter.registerPresenterListener(this);//注册当前监听回调
			userPresenter.requestAction(action);//将指示发送到控制器
//		}else {
			//如果存在，取出本地缓存并刷新界面
//			refreshUI(user);
//		}
	}

	@Override
	protected void notify(Action action) {
		if(action.getResultData()!=null){
			userNode = (UserNode) action.getResultData();//初始化当前结点
			//----------------------------
			//       需要添加本地缓存
			//----------------------------
			refreshUI(userNode.getMySelf());
		}else {
			ToastUtil.showShort("这个用户有问题！MainActivity!");
		}
	}

	//刷新当前主界面
	private void refreshUI(User user) {
		try {
			String aaa;
			mainName.setText("姓名：" + user.getRealName());
			aaa = user.getSex() == 1 ? "男" : "女";
			mainSex.setText("性别：" + aaa);
			mainBirth.setText("生日：" + user.getBirthday());
			aaa = user.getIsFame() == 1 ? "是" : "否";
			mainIsFame.setText("是否入选名人堂：" + aaa);
		}catch (Exception e){
			mainName.setText("姓名：未知");
			mainSex.setText("性别：未知");
			mainBirth.setText("生日：未知");
			mainIsFame.setText("是否入选名人堂：未知");
		}
	}

	@Override
	public void MyOnClick(View v) {
		switch (v.getId()) {
			case R.id.mainDidi://点击弟弟
				ToastUtil.showShort("点击弟弟");
				user = userNode.getMmordd();
				break;
			case R.id.mainBor://点击哥哥
				ToastUtil.showShort("点击哥哥");
				user = userNode.getGgorjj();
				break;
			case R.id.mainFather://点击父亲
				ToastUtil.showShort("点击父亲");
				user = userNode.getFather();
				break;
			case R.id.mainChild://点击孩子
				ToastUtil.showShort("点击孩子");
				user = userNode.getEldestSon();
				break;
		}
		if(user == null){
			//该用户不存在
			//跳出提示框说明是否添加
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("该用户不存在!");
			builder.setMessage("是否添加");
			builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
		}else{
			//查询出该用户结点，并且刷新界面
		}
	}
	//检查该用户是否存在
	private void checkIsExist(int person) {
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		ButterKnife.unbind(this);
	}
}
