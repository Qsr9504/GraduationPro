package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/5 15:08
 * Description : 家族 类
 **************************************/
public class Family extends BmobObject{
	private String id;//家族的编号
	private String name;//家族名称
	private String manage1;//家族负责人1
	private String manage2;//家族负责人2
	private String manage3;//家族负责人3
	private String address;//家族主要聚居地址
}
