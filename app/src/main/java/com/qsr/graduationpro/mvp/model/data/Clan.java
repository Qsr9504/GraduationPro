package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/13 19:23
 * Description : 家族实体类
 **************************************/
public class Clan  extends BmobObject{
	private String clanName;//家族名称
	private String clanZuxun;//家训
	private String clanPerface;//族谱前言
	private String clanDesc;//家族来源
	private User clanCreater;//家谱创建者
	private User clanManager1;//家族管理员1（外键）
	private User clanManager2;//家族管理员2（外键）
	private User clanManager3;//家族管理员3（外键）
	private String clanCode;//家族序列号（唯一）
	private Surname clanSurname;//家族姓氏
	private Integer	 isNormal;//是否正常使用（ 0 未建立，1正常使用，2 已封禁）
	private Integer	 clanVote;//已经申请人数
	private String	clanAddress;//家族主要聚居地址
	private String	clanNickname;//家族昵称
	private Boolean	 isPublic;//公开家族信息（false不公开）

	public Clan() {}

	public Clan(String clanName,User clanCreater,Surname clanSurname, Integer isNormal, Integer clanVote, String clanAddress, String clanNickname) {
		this.clanName = clanName;
		this.clanCreater = clanCreater;
		this.clanManager1 = clanCreater;
		this.clanSurname = clanSurname;
		this.isNormal = 0;//默认暂不可使用
		this.clanVote = clanVote;
		this.clanAddress = clanAddress;
		this.clanNickname = clanNickname;
		this.isPublic = false;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public String getClanZuxun() {
		return clanZuxun;
	}

	public void setClanZuxun(String clanZuxun) {
		this.clanZuxun = clanZuxun;
	}

	public String getClanPerface() {
		return clanPerface;
	}

	public void setClanPerface(String clanPerface) {
		this.clanPerface = clanPerface;
	}

	public String getClanDesc() {
		return clanDesc;
	}

	public void setClanDesc(String clanDesc) {
		this.clanDesc = clanDesc;
	}

	public User getClanCreater() {
		return clanCreater;
	}

	public void setClanCreater(User clanCreater) {
		this.clanCreater = clanCreater;
	}

	public User getClanManager1() {
		return clanManager1;
	}

	public void setClanManager1(User clanManager1) {
		this.clanManager1 = clanManager1;
	}

	public User getClanManager2() {
		return clanManager2;
	}

	public void setClanManager2(User clanManager2) {
		this.clanManager2 = clanManager2;
	}

	public User getClanManager3() {
		return clanManager3;
	}

	public void setClanManager3(User clanManager3) {
		this.clanManager3 = clanManager3;
	}

	public String getClanCode() {
		return clanCode;
	}

	public void setClanCode(String clanCode) {
		this.clanCode = clanCode;
	}

	public Surname getClanSurname() {
		return clanSurname;
	}

	public void setClanSurname(Surname clanSurname) {
		this.clanSurname = clanSurname;
	}

	public Integer getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}

	public Integer getClanVote() {
		return clanVote;
	}

	public void setClanVote(Integer clanVote) {
		this.clanVote = clanVote;
	}

	public String getClanAddress() {
		return clanAddress;
	}

	public void setClanAddress(String clanAddress) {
		this.clanAddress = clanAddress;
	}

	public String getClanNickname() {
		return clanNickname;
	}

	public void setClanNickname(String clanNickname) {
		this.clanNickname = clanNickname;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public void setPublic(Boolean aPublic) {
		isPublic = aPublic;
	}

	@Override
	public String toString() {
		return "Clan{" +
				"clanName='" + clanName + '\'' +
				", clanZuxun='" + clanZuxun + '\'' +
				", clanPerface='" + clanPerface + '\'' +
				", clanDesc='" + clanDesc + '\'' +
				", clanCreater=" + clanCreater +
				", clanManager1=" + clanManager1 +
				", clanManager2=" + clanManager2 +
				", clanManager3=" + clanManager3 +
				", clanCode='" + clanCode + '\'' +
				", clanSurname=" + clanSurname +
				", isNormal=" + isNormal +
				", clanVote=" + clanVote +
				", clanAddress='" + clanAddress + '\'' +
				", clanNickname='" + clanNickname + '\'' +
				", isPublic=" + isPublic +
				'}';
	}
}
