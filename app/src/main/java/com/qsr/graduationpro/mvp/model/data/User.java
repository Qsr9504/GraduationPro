package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobUser;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/2/24 14:45
 * Description : 用户类
 **************************************/
public class User extends BmobUser{
	private String realName;//真实姓名
	private Integer sex;//性别 0为女，1为男
	private String avatar;//头像
	private String phone;//手机号码
	private String	qq;//QQ帐号
	private String	wechat;//微信账号
	private Integer coin;//账户金币
	private Clan clanPk1;//所属家族1（外键）
	private Clan clanPk2;//所属家族2（外键）
	private User half;//另一半（外键）
	private Integer isFame;//入选名人堂（0为没有，1为入选）

	public User() {
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Clan getClanPk1() {
		return clanPk1;
	}

	public void setClanPk1(Clan clanPk1) {
		this.clanPk1 = clanPk1;
	}

	public Clan getClanPk2() {
		return clanPk2;
	}

	public void setClanPk2(Clan clanPk2) {
		this.clanPk2 = clanPk2;
	}

	public User getHalf() {
		return half;
	}

	public void setHalf(User half) {
		this.half = half;
	}

	public Integer getIsFame() {
		return isFame;
	}

	public void setIsFame(Integer isFame) {
		this.isFame = isFame;
	}

	@Override
	public String toString() {
		return "User{" +
				"realName='" + realName + '\'' +
				", sex=" + sex +
				", avatar='" + avatar + '\'' +
				", phone='" + phone + '\'' +
				", qq='" + qq + '\'' +
				", wechat='" + wechat + '\'' +
				", coin=" + coin +
				", clanPk1=" + clanPk1 +
				", clanPk2=" + clanPk2 +
				", half=" + half +
				", isFame=" + isFame +
				'}';
	}
}
