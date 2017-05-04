package com.qsr.graduationpro.mvp.presenter;

import com.qsr.graduationpro.base.BasePresenter;
import com.qsr.graduationpro.mvp.model.biz.AssociateBiz;
import com.qsr.graduationpro.mvp.model.data.Action;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.presenter
 * Author : qsr
 * Time : 2017/5/3 20:53
 * Description :
 **************************************/
public class AssociatePresenter extends BasePresenter{
    private AssociateBiz associateBiz;
    @Override
    public void init() {
        if(associateBiz == null){
            associateBiz = new AssociateBiz();
        }
        associateBiz.registerBizListener(this);
    }

    @Override
    public void requestAction(Action action) {
        associateBiz.setAction(action);
        associateBiz.doAddAsso();
    }
}
