package com.qsr.graduationpro.bmobUtils;

import android.content.Context;

import com.qsr.graduationpro.app.App;
import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.User;
import com.qsr.graduationpro.mvp.model.data.UserNode;
import com.qsr.graduationpro.utils.LogUtil;

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
    public void addAsso(final User user, final String account, final int type){
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
                    bmobInterface.BmobCallBack(action);
                }else {
                    LogUtil.MyLog_e("用户查询存在,接下来准备进行添加关系操作!"+list.get(0).toString());
                    //查询存在，接下来进行添加关系操作
                    addAsso(user, list.get(0),type);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    private void addAsso(final User user1, final User user2, final int type){
        LogUtil.MyLog_e(user1.toString()+"--"+user2.toString()+"---"+type);
        //添加用户1的关系
        //添加用户2的关系
        BmobQuery<UserNode> query = new BmobQuery<UserNode>();
        BmobQuery<User> innerQuery = new BmobQuery<User>();
        List<String> list = new ArrayList<String>();
        list.add(user1.getUsername());
        list.add(user2.getUsername());
        innerQuery.addWhereContainedIn("username",Arrays.asList(list.toArray()));
        query.include("mySelf");
        query.addWhereMatchesQuery("mySelf","_User",innerQuery);
        query.findObjects(context, new FindListener<UserNode>() {
            @Override
            public void onSuccess(List<UserNode> list) {
                LogUtil.MyLog_e("查询到结点个数是:"+list.size()+"分别是"+list.toString());
                addAsso(user1,user2,list.get(1),list.get(0),type);
            }

            @Override
            public void onError(int i, String s) {
                LogUtil.MyLog_e("内部查询发生错误");
            }
        });
    }
    private void addAsso(final User user1, User user2, UserNode userNode1, final UserNode userNode2, int type){
        if(type == Constants.relativeCode.DIDI){
            userNode1.setMmordd(user2);
            userNode2.setGgorjj(user1);
        }else if(type == Constants.relativeCode.GEGE){
            userNode1.setGgorjj(user2);
            userNode2.setMmordd(user1);
        }else if(type == Constants.relativeCode.FUQIN){
            userNode1.setFather(user2);
            userNode2.setEldestSon(user1);
        }else if(type == Constants.relativeCode.ZHANGZI){
            userNode1.setEldestSon(user2);
            userNode2.setFather(user1);
        }else if(type == Constants.relativeCode.QIZI){
            userNode1.setHalf(user2);
            userNode2.setHalf(user1);
        }
        userNode1.update(context, userNode1.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                userNode2.update(context, userNode2.getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        LogUtil.MyLog_e("更新完全完全完成！！！");
                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }
                });
            }
            @Override
            public void onFailure(int code, String msg) {

            }
        });

    }
}
