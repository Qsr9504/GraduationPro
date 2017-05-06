package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/5/5 12:37
 * Description :
 **************************************/
public class FileTool extends BaseTool{
    private static FileTool fileTool;
    private User user;
    //双层同步
    public static synchronized FileTool getInstance(){
        synchronized (FileTool.class) {
            if (fileTool == null) {
                fileTool = new FileTool(App.mContext);
            }
        }
        return fileTool;
    }
    private FileTool(Context context){
        this.context = context;
    }

    public void sentAvatar(Context mContext,String path){
        action = new Action(Constants.enevtBus.BUS_SEND_AVATAR);
        final String[] filePaths = new String[1];
        filePaths[0] = path;
        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.uploadblock(mContext,new UploadFileListener() {
            @Override
            public void onSuccess() {
                //文件上传成功，将本地的sp中信息更改
                action.setResultData(bmobFile.getFileUrl(context));
                LogUtil.MyLog_e("头像上传成功:"+bmobFile.getFileUrl(context));
                EventBus.getDefault().post(action);
            }

            @Override
            public void onProgress(Integer value) {
                super.onProgress(value);
            }

            @Override
            public void onFailure(int i, String s) {
                //文件上传失败
                LogUtil.MyLog_e("头像上传失败"+s);
            }
        });
    }

}
