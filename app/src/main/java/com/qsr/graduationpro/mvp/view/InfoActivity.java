package com.qsr.graduationpro.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.bmobUtils.FileTool;
import com.qsr.graduationpro.bmobUtils.UserTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.ui.IconFontTextView;
import com.qsr.graduationpro.ui.UpdateDialog;
import com.qsr.graduationpro.ui.circleAvatar.CircleTransform;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.SPUtil;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class InfoActivity extends Activity {
    @Bind(R.id.info_avatar)
    ImageView infoAvatar;
    @Bind(R.id.info_name_inner)
    TextView infoNameInner;
    @Bind(R.id.info_name)
    LinearLayout infoName;
    @Bind(R.id.info_birth_inner)
    TextView infoBirthInner;
    @Bind(R.id.info_birth)
    LinearLayout infoBirth;
    @Bind(R.id.info_sex_inner)
    TextView infoSexInner;
    @Bind(R.id.info_sex)
    LinearLayout infoSex;
    @Bind(R.id.info_phone_inner)
    TextView infoPhoneInner;
    @Bind(R.id.info_phone)
    LinearLayout infoPhone;
    @Bind(R.id.info_fame_inner)
    TextView infoFameInner;
    @Bind(R.id.info_fame)
    LinearLayout infoFame;
    @Bind(R.id.info_qq_inner)
    TextView infoQqInner;
    @Bind(R.id.info_qq)
    LinearLayout infoQq;
    @Bind(R.id.info_wechat_inner)
    TextView infoWechatInner;
    @Bind(R.id.info_wechat)
    LinearLayout infoWechat;
    @Bind(R.id.info_married_inner)
    TextView infoMarriedInner;
    @Bind(R.id.info_married)
    LinearLayout infoMarried;
    @Bind(R.id.loadingview)
    AVLoadingIndicatorView loadingview;
    @Bind(R.id.loadingviewll)
    LinearLayout loadingviewll;
    @Bind(R.id.back)
    IconFontTextView back;
    @Bind(R.id.edit)
    IconFontTextView edit;
    private Gson gson = new Gson();
    private final int REQUEST_CODE = 9504;
    private UpdateDialog updateDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    protected void initView() {
        LoadingShow();
        //从网络获取当前用户信息展示
        UserTool.getInstance().getUserInfo(
                (gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class)).getMySelf());
    }

    public void MyOnClick(View v) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMes(Action action) {
        if (Constants.enevtBus.BUS_USERINFO.equals(action.getEvent())) {
            final User user = (User) action.getResultData();
            LogUtil.MyLog_e("infoA获取到用户信息是："+user.toString());
            //显示头像
            Picasso.with(this).load(user.getAvatar())//设置不缓存
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .placeholder(R.mipmap.icon).transform(new CircleTransform()).into(infoAvatar);
            infoNameInner.setText(checkEmpty(user.getRealName()));
            infoBirthInner.setText(checkEmpty(user.getBirthday()));
            infoSexInner.setText(user.getSex() == null ? "暂未填写" : (user.getSex() == 0 ? "女" : "男"));
            infoPhoneInner.setText(checkEmpty(user.getPhone()));
            infoFameInner.setText(user.getIsFame() == null ? "暂未填写" : (user.getIsFame() == 1 ? "是" : "否"));
            infoQqInner.setText(checkEmpty(user.getQq()));
            infoWechatInner.setText(checkEmpty(user.getWechat()));
            //从本地验证获取结点 检查另一半是否存在
            if(gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class).getHalf() == null){
                infoMarriedInner.setText(checkEmpty(gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class).getHalf()));
                infoMarried.setClickable(false);//设置为不可点击
            }else {
                infoMarriedInner.setText(gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class).getHalf());
                infoMarried.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserTool.getInstance().getUserInfo(gson.fromJson((String) SPUtil.get(Constants.mySP.USERNODE, ""), UserNode.class).getHalf());
                    }
                });
            }
            if (user.getUsername().equals(BmobUser.getCurrentUser(this).getUsername())) {
                //如果当前是本人的话
                edit.setVisibility(View.VISIBLE);
            } else {
                //如果不是本人，说明是查看别人的信息
                edit.setVisibility(View.GONE);
                //设置头像不可点击
                infoAvatar.setClickable(false);
            }
            LoadingDismiss();
        }else if(Constants.enevtBus.BUS_SEND_AVATAR.equals(action.getEvent())){
            //如果上传头像成功 1.获取图片地址 2.更新本地用户  3.更新服务器用户头像信息
            String picUrl = (String) action.getResultData();
            User user = new User();
            user.setObjectId(BmobUser.getCurrentUser(this).getObjectId());
            user.setAvatar(picUrl);
            UserTool.getInstance().updateUser(user);
        }else if(Constants.enevtBus.BUS_UPDATE_USER.equals(action.getEvent())){
            LoadingShow();
            //更新用户成功
            //重新载入当前信息
            UserTool.getInstance().getUserInfo(BmobUser.getCurrentUser(this).getUsername());
            //通知主界面刷新
            EventBus.getDefault().post(new Action(Constants.enevtBus.BUS_REFRESH));
        }
    }

    @OnClick(R.id.back)
    public void back(View view) {
        ActivityManager.getInstance().removeCurrent();
    }

    @OnClick(R.id.edit)
    public void edit(View view) {
        //更改当前用户信息
        showUpdateDialog();
    }
    //弹出更新对话框
    private void showUpdateDialog() {
        updateDialog = new UpdateDialog(this);
        updateDialog.show();
    }

    @OnClick(R.id.info_avatar)
    public void getAvatar(View view) {
        // 自定义图片加载器
       ImageLoader loader = new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                LogUtil.MyLog_e("图片路径:"+path);
                // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
                Picasso.with(context).load(new File(path)).placeholder(R.mipmap.icon1)
                        .error(R.mipmap.icon2).into(imageView);
            }
        };
// 自由配置选项
        ImgSelConfig config = new ImgSelConfig.Builder(this, loader)
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#3F51B5"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();

        // 跳转到图片选择器
        ImgSelActivity.startActivity(InfoActivity.this, config, REQUEST_CODE);
    }

    public String checkEmpty(Object o) {
        if (o == null) {
            return "暂未填写";
        } else {
            return (String) o;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            for (String path : pathList) {
                LoadingShow();
                //更新至服务器
                FileTool.getInstance().sentAvatar(InfoActivity.this,path);
                //打印出当前的路径
                LogUtil.MyLog_e("图片的存储路径为：" + path);
            }
        }
    }
}
