package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/13 19:15
 * Description : 用户结点
 **************************************/
public class UserNode extends BmobObject{
	private String ggorjj;//兄长或姐姐
	private String mmordd;//弟弟或妹妹
	private String father;//父亲
	private String eldestSon;//长子或长女
	private String mySelf;//用户详细
	private String half;//另一半的详细
	private Boolean isMarried;//是否已婚（true是）
	private Boolean isAlive;//是否健在（true是）

	public String getGgorjj() {
		return ggorjj;
	}

	public void setGgorjj(String ggorjj) {
		this.ggorjj = ggorjj;
	}

	public String getMmordd() {
		return mmordd;
	}

	public void setMmordd(String mmordd) {
		this.mmordd = mmordd;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getEldestSon() {
		return eldestSon;
	}

	public void setEldestSon(String eldestSon) {
		this.eldestSon = eldestSon;
	}

	public String getMySelf() {
		return mySelf;
	}

	public void setMySelf(String mySelf) {
		this.mySelf = mySelf;
	}

	public String getHalf() {
		return half;
	}

	public void setHalf(String half) {
		this.half = half;
	}

	public Boolean getMarried() {
		return isMarried;
	}

	public void setMarried(Boolean married) {
		isMarried = married;
	}

	public Boolean getAlive() {
		return isAlive;
	}

	public void setAlive(Boolean alive) {
		isAlive = alive;
	}

	public UserNode() {}

	public UserNode(String mySelf, Boolean isMarried, Boolean isAlive) {
		this.mySelf = mySelf;
		this.isMarried = false;//默认未婚
		this.isAlive = true;//默认健在
	}

	@Override
	public String toString() {
		return "UserNode{" +
				"ggorjj=" + ggorjj +
				", mmordd=" + mmordd +
				", father=" + father +
				", eldestSon=" + eldestSon +
				", mySelf=" + mySelf +
				", half=" + half +
				", isMarried=" + isMarried +
				", isAlive=" + isAlive +
				'}';
	}

}
