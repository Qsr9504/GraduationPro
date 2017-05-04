package com.qsr.graduationpro;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.bmobUtils.UserTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.mvp.presenter.UserPresenter;
import com.qsr.graduationpro.mvp.view.AssociateActivity;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.qsr.graduationpro.utils.ToastUtil;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private CardView mainCardView;
    private TextView mainFather;
    private AVLoadingIndicatorView loadingIndicatorView;
    private LinearLayout linearLayout;
    private TextView mainChild;
    private TextView mainBor;
    private TextView mainDidi;

    private static UserNode userNode;
    private static User user;
    private static String username;
    private UserPresenter userPresenter;
    private Bundle bundle;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        EventBus.getDefault().register(this);
        //初始化主界面
        View view = View.inflate(this, R.layout.main_family, null);
        loadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingview);
        linearLayout = (LinearLayout) view.findViewById(R.id.loadingviewll);
        mainAvatar = (ImageView) view.findViewById(R.id.mainAvatar);
        mainName = (TextView) view.findViewById(R.id.mainName);
        mainSex = (TextView) view.findViewById(R.id.mainSex);
        mainBirth = (TextView) view.findViewById(R.id.mainBirth);
        mainFather = (TextView) view.findViewById(R.id.mainFather);
        mainChild = (TextView) view.findViewById(R.id.mainChild);
        mainBor = (TextView) view.findViewById(R.id.mainBor);
        mainDidi = (TextView) view.findViewById(R.id.mainDidi);
        mainFrame.addView(view);
    }

    @Override
    protected void AfterInitView() {
        LoadingShow();
        UserTool.getInstance().getUser(BmobUser.getCurrentUser(this).getUsername());
        UserTool.getInstance().getUserNode(BmobUser.getCurrentUser(this).getUsername());
    }

    @Override
    protected void notify(Action action) {
//        //登录注册操作完成
//        if (action.getState() == Constants.stateCode.STATE_SUCCESS) {
//            if(BmobUser.getCurrentUser(this).getUsername().equals(userNode.getMySelf())){
//                String str = gson.toJson(userNode);
//                SPUtil.put(Constants.mySP.CURRENT_USERNODE, str);
//                SPUtil.put(Constants.mySP.USERNODE, str);
//                LogUtil.MyLog_e(this,"已经将当前界面上的结点加入sp1");
//            }
//            userNode = (UserNode) action.getResultData();//初始化当前结点
//            //这里只用作登录 注册 之后刷新界面
//            refreshUI();
//        } else {
//            ToastUtil.showShort("这个用户有问题！MainActivity!");
//            refreshUI();
//        }
    }

    //刷新当前主界面 根据当前userNode信息刷新界面
    private void refreshUI() {
        String aaa;
        if(user.getRealName() == null) {
            mainName.setText("姓名：未知");
        }else
            mainName.setText("姓名：" + user.getRealName());
        if(user.getSex() == null) {
            mainSex.setText("性别：未知");
        }else {
            aaa = user.getSex() == 1 ? "男" : "女";
            mainSex.setText("性别：" + aaa);
        }
        if(user.getBirthday() == null) {
            mainBirth.setText("生日：未知");
        }else
            mainBirth.setText("生日：" + user.getBirthday());
        //加载对话框消失
        LoadingDismiss();
    }

    @Override
    public void MyOnClick(View v) {
        LoadingShow();
        switch (v.getId()) {
            case R.id.mainDidi://点击弟弟
                ToastUtil.showShort("点击弟弟");
                checkIsExist(Constants.relativeCode.DIDI);
                break;
            case R.id.mainBor://点击哥哥
                ToastUtil.showShort("点击哥哥");
                checkIsExist(Constants.relativeCode.GEGE);
                break;
            case R.id.mainFather://点击父亲
                ToastUtil.showShort("点击父亲");
                checkIsExist(Constants.relativeCode.FUQIN);
                break;
            case R.id.mainChild://点击孩子
                ToastUtil.showShort("点击孩子");
                checkIsExist(Constants.relativeCode.ZHANGZI);
                break;
        }
    }

    //检查该用户是否存在
    private void checkIsExist(final int person) {
        userNode = gson.fromJson((String)SPUtil.get(Constants.mySP.USERNODE,""),UserNode.class);
        String doUser = "";
        switch (person) {
            case Constants.relativeCode.DIDI://点击了弟弟
                doUser = userNode.getMmordd() == null ? "isEmpty" : userNode.getMmordd();
                break;
            case Constants.relativeCode.GEGE://点击了哥哥
                doUser = userNode.getGgorjj() == null ? "isEmpty" : userNode.getGgorjj();
                break;
            case Constants.relativeCode.FUQIN://点击了父亲
                doUser = userNode.getFather() == null ? "isEmpty" : userNode.getFather();
                break;
            case Constants.relativeCode.ZHANGZI://点击了长子
                doUser = userNode.getEldestSon() == null ? "isEmpty" : userNode.getEldestSon();
                break;
        }
        if("isEmpty".equals(doUser) || "".equals(doUser) || doUser == null){
            showMyDialog(person);
        }else {
            UserTool.getInstance().getUser(doUser);
            UserTool.getInstance().getUserNode(doUser);
        }

    }

    private void showMyDialog(final int person) {
        LoadingDismiss();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("该关系不存在!");
        builder.setMessage("是否添加");
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到添加关系界面
                bundle = new Bundle();
                //将需要操作的用户名传送给新开的activity
                bundle.putInt("type", person);
                //启动添加关系的界面
                ActivityManager.getInstance().startAct(MainActivity.this, new AssociateActivity(), bundle);
            }
        }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SPUtil.remove(Constants.mySP.USERNODE);
        ButterKnife.unbind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMes(Action action) {
        if (Constants.enevtBus.BUS_MAIN_USER.equals(action.getEvent())) {
            LogUtil.MyLog_e("主界面收到了刷新界面用户的eventbus");
            user = (User) action.getResultData();
            refreshUI();
        }else if(Constants.enevtBus.BUS_REFRESH.equals(action.getEvent())){
            LogUtil.MyLog_e(this,"接收到刷新界面要求");
            UserTool.getInstance().getUser(userNode.getMySelf());
            UserTool.getInstance().getUserNode(userNode.getMySelf());
            LoadingShow();
        } else if(Constants.enevtBus.BUS_MAIN_USERNODE.equals(action.getEvent())){
            LogUtil.MyLog_e(this,"主界面收到了对应结点信息");
            hindFather((UserNode)action.getResultData());
        }
    }

    //当该用户不是长子时候，取消父亲的可见性
    private void hindFather(UserNode resultData) {
        if(resultData.getGgorjj() == null && resultData.getFather() ==null){
            mainFather.setVisibility(View.VISIBLE);
            mainBor.setVisibility(View.VISIBLE);
        }
        if(resultData.getGgorjj()!=null){
            mainFather.setVisibility(View.INVISIBLE);
            mainBor.setVisibility(View.VISIBLE);
        }
        if (resultData.getFather() != null) {
            mainBor.setVisibility(View.INVISIBLE);
            mainFather.setVisibility(View.VISIBLE);
        }
    }

    private void LoadingShow(){
        linearLayout.setVisibility(View.VISIBLE);
    }
    private void LoadingDismiss(){
        linearLayout.setVisibility(View.GONE);
    }

}
