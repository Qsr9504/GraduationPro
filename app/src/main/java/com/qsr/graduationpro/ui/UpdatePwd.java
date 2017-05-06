package com.qsr.graduationpro.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qsr.graduationpro.MainActivity;
import com.qsr.graduationpro.R;
import com.qsr.graduationpro.mvp.view.LoginActivity;
import com.qsr.graduationpro.utils.ActivityManager;
import com.qsr.graduationpro.utils.LogUtil;
import com.qsr.graduationpro.utils.TextUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**************************************
 * FileName : com.qsr.graduationpro.ui
 * Author : qsr
 * Time : 2017/5/6 13:59
 * Description :
 **************************************/
public class UpdatePwd extends Dialog implements View.OnClickListener {
    @Bind(R.id.pwd_old)
    EditText pwdOld;
    @Bind(R.id.pwd_new1)
    EditText pwdNew1;
    @Bind(R.id.pwd_new2)
    EditText pwdNew2;
    @Bind(R.id.update_cancel)
    TextView updateCancel;
    @Bind(R.id.update_submit)
    TextView updateSubmit;
    private Context context;

    public UpdatePwd(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_changepwd);
        ButterKnife.bind(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        updateCancel.setOnClickListener(this);
        updateSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_cancel:
                this.dismiss();
                break;
            case R.id.update_submit:
                checkPwd();
                break;
        }
    }

    private void checkPwd() {
        String pwd1 = pwdNew1.getText().toString().trim();
        String pwd2 = pwdNew2.getText().toString().trim();
        String pwdold = pwdOld.getText().toString().trim();
        if(TextUtil.isEmpty(pwd1,pwd2)){
            ToastUtil.showShort("新密码不能为空");
        }else if(TextUtil.isEmpty(pwdold)){
            ToastUtil.showShort("请输入旧密码");
        }else if(!pwd1.equals(pwd2)){
            ToastUtil.showShort("两次新密码不一致");
        }else {
            BmobUser.updateCurrentUserPassword(context,pwdold, pwd1, new UpdateListener() {
                @Override
                public void onSuccess() {
                    dismiss();
                    ToastUtil.showShort("密码更改成功,请重新登录");
                    //退出登录，重新登录
                    BmobUser.logOut(context);
                    ActivityManager.getInstance().startAct(ActivityManager.getInstance().currentActivity(),new LoginActivity());
                }

                @Override
                public void onFailure(int i, String s) {
                    LogUtil.MyLog_e("更新密码失败");
                    ToastUtil.showShort(s);
                }
            });
        }
    }
}
