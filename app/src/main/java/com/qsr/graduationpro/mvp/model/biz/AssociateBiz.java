package com.qsr.graduationpro.mvp.model.biz;

import com.qsr.graduationpro.base.BaseBiz;
import com.qsr.graduationpro.bmobUtils.AssociateTool;
import com.qsr.graduationpro.bmobUtils.UserTool;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;

import java.util.HashMap;
import java.util.Map;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.biz
 * Author : qsr
 * Time : 2017/5/3 20:54
 * Description :
 **************************************/
public class AssociateBiz extends BaseBiz {
    private Action action;
    private String username;
    private String account;
    private int type;
    private Map map = new HashMap();
    @Override
    public void setAction(Action action) {
        this.action = action;
    }
    public void doAddAsso(){
        map = (Map) action.getRequestData();
        account = (String) map.get("account");
        username = (String) map.get("username");
        type = (int) map.get("type");
        AssociateTool.getInstance().registerBmobInterface(AssociateBiz.this);
        AssociateTool.getInstance().addAsso(username,account,type);
    }
}
