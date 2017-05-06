package com.qsr.graduationpro.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**************************************
 * FileName : com.qsr.graduationpro.ui
 * Author : qsr
 * Time : 2017/5/5 22:34
 * Description :
 **************************************/
public class UpdateDialog extends Dialog implements View.OnClickListener {
    @Bind(R.id.update_inner_name)
    EditText updateInnerName;
    @Bind(R.id.update_inner_birth)
    EditText updateInnerBirth;
    @Bind(R.id.update_inner_sex)
    TextView updateInnerSex;
    @Bind(R.id.update_sex)
    LinearLayout updateSex;
    @Bind(R.id.update_inner_phone)
    EditText updateInnerPhone;
    @Bind(R.id.update_inner_isfame)
    TextView updateInnerIsfame;
    @Bind(R.id.update_isfame)
    LinearLayout updateIsfame;
    @Bind(R.id.update_inner_qq)
    EditText updateInnerQq;
    @Bind(R.id.update_inner_wechat)
    EditText updateInnerWechat;
    @Bind(R.id.update_cancel)
    TextView updateCancel;
    @Bind(R.id.update_submit)
    TextView updateSubmit;
    private Context context;

    public UpdateDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }
    @OnClick(R.id.update_sex)
    public void setSex(View v){
        if(updateInnerSex.getText().toString().trim().equals("男")){
            updateInnerSex.setText("女");
        }else {
            updateInnerSex.setText("男");
        }
    }

    @OnClick(R.id.update_isfame)
    public void setIsfame(View view){
        if(updateInnerIsfame.getText().toString().trim().equals("是")){
            updateInnerIsfame.setText("否");
        }else {
            updateInnerIsfame.setText("是");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);
        ButterKnife.bind(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //从服务器获取最新信息
        getUserInfo(BmobUser.getCurrentUser(context).getUsername());
    }

    private void getUserInfo(final String username) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username", username);
        query.findObjects(context,new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                if(list.size() == 0)
                    getUserInfo(username);
                else {
                    initData(list.get(0));
                }
            }

            @Override
            public void onError(int i, String s) {
                LogUtil.MyLog_e("获取用户发生错误"+s);
            }
        });
    }

    private void initData(User user) {
        updateCancel.setOnClickListener(this);
        updateSubmit.setOnClickListener(this);
        updateInnerName.setText(IsEmpty(user.getRealName()));
        updateInnerBirth.setText(IsEmpty(user.getBirthday()));
        updateInnerSex.setText(user.getSex() == null ? "" : (user.getSex() == 1 ? "男" : "女"));
        updateInnerPhone.setText(IsEmpty(user.getPhone()));
        updateInnerIsfame.setText(user.getSex() == null ? "" : (user.getIsFame() == 1 ? "是" : "否"));
        updateInnerQq.setText(IsEmpty(user.getQq()));
        updateInnerWechat.setText(IsEmpty(user.getWechat()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_cancel:
                this.dismiss();
                break;
            case R.id.update_submit:
                getMes();
                break;
        }
    }

    private void getMes() {
        User user = new User();
        user.setRealName(updateInnerName.getText().toString().trim());
        user.setSex("男".equals(updateInnerSex.getText().toString().trim()) ? 1 : 0);
        user.setBirthday(updateInnerBirth.getText().toString().trim());
        user.setPhone(updateInnerPhone.getText().toString().trim());
        user.setIsFame("是".equals(updateInnerIsfame.getText().toString().trim()) ? 1 : 0);
        user.setQq(updateInnerQq.getText().toString().trim());
        user.setWechat(updateInnerWechat.getText().toString().trim());
        //进行更新
        LogUtil.MyLog_e("updialog要更新的user是：" + user.toString());
        user.update(context, BmobUser.getCurrentUser(context).getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                EventBus.getDefault().post(new Action(Constants.enevtBus.BUS_UPDATE_USER));
                dismiss();//对话框消失
            }

            @Override
            public void onFailure(int i, String s) {
                LogUtil.MyLog_e("更新失败" + s);
                dismiss();
            }
        });
    }

    private String IsEmpty(String str) {
        if (str == null)
            return "";
        if (str.equals(""))
            return "";
        return str;
    }

}
