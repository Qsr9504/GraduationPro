package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**************************************
 * FileName : com.qsr.graduationpro.bmobUtils
 * Author : qsr
 * Time : 2017/5/3 21:13
 * Description :
 **************************************/
public class AssociateTool extends BaseTool {
    private BmobUser bmobUser;
    private static AssociateTool associateTool;
    //双层同步
    public static synchronized AssociateTool getInstance(){
        synchronized (AssociateTool.class) {
            if (associateTool == null) {
                associateTool = new AssociateTool(App.mContext);
            }
        }
        return associateTool;
    }
    private AssociateTool(Context context){
        this.context = context;
        bmobUser = new BmobUser();
    }
    public void addAsso(final String username, final String account, final int type){
        //查询用户是否存在
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username", account);
        query.findObjects(context,new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                if(list.size() == 0){
                    //查询失败，该用户不存在
                    action.setEvent(Constants.eventString.EVENT_ASSOCIATE);
                    action.setState(Constants.stateCode.STATE_NOT_FOUND);//未找到该人
                    action.setResultData("该用户不存在");
                    EventBus.getDefault().post(action);
                }else {
                    LogUtil.MyLog_e("用户查询存在,接下来准备进行添加关系操作!"+list.get(0).toString());
                    //查询存在，接下来进行添加关系操作
                    addAsso2(username, list.get(0).getUsername(),type);
                    action.setEvent(Constants.eventString.EVENT_ASSOCIATE);
                    action.setState(Constants.stateCode.STATE_PROGRESS);
                    action.setResultData("用户存在");
                    EventBus.getDefault().post(action);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    private void addAsso2(final String user1, final String user2, final int type){
        action.setEvent(Constants.eventString.EVENT_ASSOCIATE);
        //添加用户1的关系
        //添加用户2的关系
        List slist = new ArrayList();
        slist.add(user1);
        slist.add(user2);
        BmobQuery<UserNode> query = new BmobQuery<UserNode>();
        query.addWhereContainedIn("mySelf",Arrays.asList(slist.toArray()));
        query.findObjects(context, new FindListener<UserNode>() {
            @Override
            public void onSuccess(List<UserNode> list) {
                if(list.size() == 0){
                    addAsso2(user1,user2,type);
                }else {
                    LogUtil.MyLog_e("查询到结点个数是:"+list.size()+"分别是"+list.toString());
                    addAsso3(user1,user2,list.get(0),list.get(1),type);
                    //正在进行
                    action.setState(Constants.stateCode.STATE_PROGRESS);
                    action.setResultData("查询到两个结点，准备更新结点");
                    EventBus.getDefault().post(action);
                }
            }

            @Override
            public void onError(int i, String s) {
                LogUtil.MyLog_e("查询对应两个结点发生错误");
            }
        });
    }
    private void addAsso3(final String user1, final String user2, final UserNode userNode1, final UserNode userNode2,final int type){
        String userNode1Tar = userNode1.getMySelf().equals(user1)?user2:user1;
        String userNode2Tar = userNode2.getMySelf().equals(user2)?user1:user2;
        if(type == Constants.relativeCode.DIDI){
            if(userNode1.getMySelf().equals(user1)){
                userNode1.setMmordd(userNode1Tar);
                userNode2.setGgorjj(userNode2Tar);
            }else {
                userNode2.setMmordd(userNode2Tar);
                userNode1.setGgorjj(userNode1Tar);
            }
        }else if(type == Constants.relativeCode.GEGE){
            if(userNode1.getMySelf().equals(user1)){
                userNode1.setGgorjj(userNode1Tar);
                userNode2.setMmordd(userNode2Tar);
            }else {
                userNode2.setGgorjj(userNode2Tar);
                userNode1.setMmordd(userNode1Tar);
            }
        }else if(type == Constants.relativeCode.FUQIN){
            LogUtil.MyLog_e("为"+user1+"设置父亲"+userNode1Tar);
            if(userNode1.getMySelf().equals(user1)){
                userNode1.setFather(userNode1Tar);
                userNode2.setEldestSon(userNode2Tar);
            }else {
                userNode2.setFather(userNode2Tar);
                userNode1.setEldestSon(userNode1Tar);
            }
        }else if(type == Constants.relativeCode.ZHANGZI){
            if(userNode1.getMySelf().equals(user1)){
                userNode1.setEldestSon(userNode1Tar);
                userNode2.setFather(userNode2Tar);
            }else {
                userNode2.setEldestSon(userNode2Tar);
                userNode1.setFather(userNode1Tar);
            }
        }else if(type == Constants.relativeCode.QIZI){
            userNode1.setHalf(userNode1Tar);
            userNode2.setHalf(userNode2Tar);
        }
        userNode1.update(context, userNode1.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                userNode2.update(context, userNode2.getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        LogUtil.MyLog_e("更新完全完全完成！！！");
                        action.setState(Constants.stateCode.STATE_SUCCESS);
                        action.setEvent(Constants.eventString.EVENT_ASSOCIATE);
                        EventBus.getDefault().post(action);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        LogUtil.MyLog_e("第二个用户更新失败");
                        addAsso3(user1,user2,userNode1,userNode2,type);
                    }
                });
            }
            @Override
            public void onFailure(int code, String msg) {
                LogUtil.MyLog_e("第一个用户结点更新失败");
                addAsso3(user1,user2,userNode1,userNode2,type);
            }
        });

    }
}
