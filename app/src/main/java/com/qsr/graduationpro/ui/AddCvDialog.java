package com.qsr.graduationpro.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.bmobUtils.RecordTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.Record;
import com.qsr.graduationpro.utils.TextUtil;
import com.qsr.graduationpro.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**************************************
 * FileName : com.qsr.graduationpro.ui
 * Author : qsr
 * Time : 2017/5/6 22:28
 * Description :
 **************************************/
public class AddCvDialog extends Dialog implements View.OnClickListener {
    @Bind(R.id.addcv_time)
    EditText addcvTime;
    @Bind(R.id.addcv_address)
    EditText addcvAddress;
    @Bind(R.id.addcv_content)
    EditText addcvContent;
    @Bind(R.id.add_cancel)
    TextView addCancel;
    @Bind(R.id.add_submit)
    TextView addSubmit;
    private Context context;
    private Record record;
    public AddCvDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addcv);
        ButterKnife.bind(this);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        addCancel.setOnClickListener(this);
        addSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_cancel:
                this.dismiss();
                break;
            case R.id.add_submit:
                addCv();
                break;
        }
    }

    private void addCv() {
        record = new Record();
        String time = addcvTime.getText().toString().trim();
        String address = addcvAddress.getText().toString().trim();
        final String content = addcvContent.getText().toString().trim();
        if(TextUtil.isEmpty(time,address,content)){
            ToastUtil.showShort("不能有空");
        }else {
            record.setRecordTime(time);
            record.setRecordOwner(BmobUser.getCurrentUser(context).getUsername());
            record.setRecordPlace(address);
            record.setRecordContent(content);
            record.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    dismiss();
                    ToastUtil.showShort("添加成功");
                    //让履历界面重新加载数据
                    EventBus.getDefault().post(new Action(Constants.enevtBus.BUS_REFRESH_CV));
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.showShort("添加失败" + s);
                }
            });
        }
    }
}
