package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/2/24 14:45
 * Description : 用户类
 **************************************/
public class User extends BmobUser{
	private String username;//用户名
	private String password;//密码
	private String birthday;//生日
	private String userId;//用户id
	private int coin;//账户金币
	private int sex;//性别 0为女，1为男
	private int isManage;//是否是管理者 0为不是，其他为是
	private String belong;//所属的家族名称
	public User() {	}

	public User(String username, String password, String birthday, String userId, int coin, int sex, int isManage, String belong) {
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.userId = userId;
		this.coin = coin;
		this.sex = sex;
		this.isManage = isManage;
		this.belong = belong;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", birthday='" + birthday + '\'' +
				", userId='" + userId + '\'' +
				", coin=" + coin +
				", sex=" + sex +
				", isManage=" + isManage +
				", belong='" + belong + '\'' +
				'}';
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getIsManage() {
		return isManage;
	}

	public void setIsManage(int isManage) {
		this.isManage = isManage;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}
}
