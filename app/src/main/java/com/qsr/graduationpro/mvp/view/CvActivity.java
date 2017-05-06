package com.qsr.graduationpro.mvp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.bmobUtils.RecordTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.Record;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.mvp.view.adapter.RecordAdapter;
import com.qsr.graduationpro.mvp.view.adapter.SpacesItemDecoration;
import com.qsr.graduationpro.ui.AddCvDialog;
import com.qsr.graduationpro.ui.IconFontTextView;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class CvActivity extends FragmentActivity {

    @Bind(R.id.cv_back)
    IconFontTextView cvBack;
    @Bind(R.id.cv_add)
    IconFontTextView cvAdd;
    @Bind(R.id.cv_recycle)
    RecyclerView cvRecycle;
    @Bind(R.id.loadingview)
    AVLoadingIndicatorView loadingview;
    @Bind(R.id.loadingviewll)
    LinearLayout loadingviewll;
    private Gson gson;
    private List<Record> recordList;
    private RecordAdapter recordAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        gson = new Gson();
        initView();
        initData();
    }

    private void initData() {
        LoadingShow();
        //从服务器获取该用户的履历信息
        RecordTool.getInstance().getRecoed(
                gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE,"")
                        ,UserNode.class).getMySelf()
        );
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMes(Action action){
        if(Constants.enevtBus.BUS_GETCV.equals(action.getEvent())){
            recordList = action.getResultDataList();
            LogUtil.MyLog_e("获取到履历信息"+recordList.toString());
            cvRecycle.setLayoutManager(new LinearLayoutManager(this));
            recordAdapter = new RecordAdapter(this,recordList);
            cvRecycle.addItemDecoration(new SpacesItemDecoration(10));
            cvRecycle.setAdapter(recordAdapter);

            LoadingDismiss();
        }else if(Constants.enevtBus.BUS_REFRESH_CV.equals(action.getEvent())){
            LoadingShow();
            //从服务器获取该用户的履历信息
            RecordTool.getInstance().getRecoed(BmobUser.getCurrentUser(this).getUsername());
        }
    }
    private void initView() {
        //对比查看的用户是不是当前用户   是否隐藏增加按钮
        if (BmobUser.getCurrentUser(this).getUsername().equals(
                (gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class).getMySelf())
        )){
            //如果是当前用户
            cvAdd.setVisibility(View.VISIBLE);
        } else {
            cvAdd.setVisibility(View.GONE);
        }
    }

    public void back(View view) {
        ActivityManager.getInstance().removeCurrent();
    }

    public void addBtn(View view) {
        showAddDialog();
    }

    private void showAddDialog() {
        //弹出增加履历对话框
        AddCvDialog addCvDialog = new AddCvDialog(this);
        addCvDialog.show();
    }

    private void LoadingShow() {
        loadingviewll.setVisibility(View.VISIBLE);
    }

    private void LoadingDismiss() {
        try {
            Thread.sleep(500);
            loadingviewll.setVisibility(View.GONE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
