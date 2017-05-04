package com.qsr.graduationpro.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.base.BaseActivity;
import com.qsr.graduationpro.bmobUtils.AssociateTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.mvp.presenter.AssociatePresenter;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AssociateActivity extends BaseActivity {

    @Bind(R.id.asso_account)
    EditText assoAccount;
    @Bind(R.id.asso_submit)
    Button assoSubmit;

    private String username;
    private String account;
    private Integer type;
    private AssociatePresenter associatePresenter;
    private Map map;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_associate;
    }

    @Override
    protected void AfterInitView() {
        EventBus.getDefault().register(this);
        action = new Action();
        map = new HashMap();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("from");
        username = gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE,""),UserNode.class).getMySelf();
        type = bundle.getInt("type");
    }

    @Override
    protected void notify(Action action) {
    }

    @Override
    public void MyOnClick(View v) {
        //点击确定按钮
        account = assoAccount.getText().toString().trim();
        //执行访问命令
        AssociateTool.getInstance().addAsso(username,account,type);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMes(Action action){
        if(Constants.eventString.EVENT_ASSOCIATE.equals(action.getEvent())){
            if(Constants.stateCode.STATE_NOT_FOUND == action.getState()){
                ToastUtil.showShort(action.getResultData().toString());
            }else if(Constants.stateCode.STATE_PROGRESS == action.getState()){
                ToastUtil.showShort(action.getResultData().toString());
            }else if(Constants.stateCode.STATE_SUCCESS == action.getState()){
                ToastUtil.showShort("关系添加完毕！");
                action = new Action();
                action.setEvent(Constants.enevtBus.BUS_REFRESH);
                //更新主界面
                EventBus.getDefault().post(action);
                //关闭当前界面
                ActivityManager.getInstance().removeCurrent();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
