package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/5 16:50
 * Description : app版本信息
 **************************************/
public class Version extends BmobObject{
	private String id;
	private String versionName;
	private Integer versionCode;
	private String versionDesc;
	private String versionUrl;

	public Version() {
		super();
	}

	public Version(String tableName, String id, String versionName, Integer versionCode, String versionDesc, String versionUrl) {
		super(tableName);
		this.id = id;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.versionDesc = versionDesc;
		this.versionUrl = versionUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getVersionUrl() {
		return versionUrl;
	}

	public void setVersionUrl(String versionAddress) {
		this.versionUrl = versionAddress;
	}

	@Override
	public String toString() {
		return "Version{" +
				"id='" + id + '\'' +
				", versionName='" + versionName + '\'' +
				", versionCode=" + versionCode +
				", versionDesc='" + versionDesc + '\'' +
				", versionAddress='" + versionUrl + '\'' +
				'}';
	}
}
