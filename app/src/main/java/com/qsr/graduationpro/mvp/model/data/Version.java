package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/5 16:50
 * Description : app版本信息
 **************************************/
public class Version extends BmobObject{
	private String versionName;
	private Integer versionCode;
	private String versionDesc;
	private String versionUrl;

	public Version() {
		super();
	}

	public Version(String versionName, Integer versionCode, String versionDesc, String versionUrl) {
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.versionDesc = versionDesc;
		this.versionUrl = versionUrl;
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

	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}

	@Override
	public String toString() {
		return "Version{" +
				"versionName='" + versionName + '\'' +
				", versionCode=" + versionCode +
				", versionDesc='" + versionDesc + '\'' +
				", versionUrl='" + versionUrl + '\'' +
				'}';
	}
}
