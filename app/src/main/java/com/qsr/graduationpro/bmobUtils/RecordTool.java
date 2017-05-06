package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.Record;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/5/6 21:46
 * Description :
 **************************************/
public class RecordTool extends BaseTool{
    private BmobUser bmobUser;
    private static RecordTool recordTool;
    private Context context;
    //双层同步
    public static synchronized RecordTool getInstance(){
        synchronized (RecordTool.class) {
            if (recordTool == null) {
                recordTool = new RecordTool(App.mContext);
            }
        }
        return recordTool;
    }
    private RecordTool(Context context){
        this.context = context;
        bmobUser = new BmobUser();
    }

    public void getRecoed(String username){
        BmobQuery<Record> query = new BmobQuery<Record>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("recordOwner", username);
      //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(context,new FindListener<Record>() {
            @Override
            public void onSuccess(List<Record> list) {
                action = new Action(Constants.enevtBus.BUS_GETCV);
                action.setResultDataList(list);
                EventBus.getDefault().post(action);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
