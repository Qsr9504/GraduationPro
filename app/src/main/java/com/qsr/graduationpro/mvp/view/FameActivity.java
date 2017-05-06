package com.qsr.graduationpro.mvp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.view.adapter.FameAdapter;
import com.qsr.graduationpro.mvp.view.adapter.SpacesItemDecoration;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class FameActivity extends FragmentActivity {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.loadingview)
    AVLoadingIndicatorView loadingview;
    @Bind(R.id.loadingviewll)
    LinearLayout loadingviewll;
    private List<User> userList = new ArrayList<User>();
    private FameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fame);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        initData();
    }

    private void initData() {
        LoadingShow();
        LogUtil.MyLog_e("准备获取全部用户信息");
        getAllUserAcatar();
    }

    private void getAllUserAcatar() {
        //只返回Person表的objectId这列的值
        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.setLimit(20);
        bmobQuery.addQueryKeys("isFame,avatar");
        bmobQuery.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                docheck(list);
                LogUtil.MyLog_e("获取信息成功"+list.toString());
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    //过滤出来不愿意公开消息的人
    private void docheck(List<User> list) {
        userList.clear();
        for (User u: list) {
            if(u.getIsFame() == 1){
                userList.add(u);
            }
        }
        LogUtil.MyLog_e("数据过滤成功"+userList.size());
        recycleview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置adapter
        FameAdapter adapter=new FameAdapter(this,userList);
        recycleview.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(50);
        recycleview.addItemDecoration(decoration);
        LoadingDismiss();
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
