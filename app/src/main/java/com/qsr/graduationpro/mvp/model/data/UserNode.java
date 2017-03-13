package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/13 19:15
 * Description : 用户结点
 **************************************/
public class UserNode extends BmobObject{
	private User ggorjj;//兄长或姐姐
	private User mmordd;//弟弟或妹妹
	private User father;//父亲
	private User eldestSon;//长子或长女
	private User mySelf;//用户详细
	private User half;//另一半的详细
	private Boolean isMarried;//是否已婚（true是）
	private Boolean isAlive;//是否健在（true是）

	public UserNode() {}

	public UserNode(User ggorjj, User mmordd,
	                User father, User eldestSon, User mySelf,
	                User half, Boolean isMarried, Boolean isAlive) {
		this.ggorjj = ggorjj;
		this.mmordd = mmordd;
		this.father = father;
		this.eldestSon = eldestSon;
		this.mySelf = mySelf;
		this.half = half;
		this.isMarried = isMarried;
		this.isAlive = isAlive;
	}
	public UserNode(User mySelf, Boolean isMarried, Boolean isAlive) {
		this.ggorjj = null;
		this.mmordd = null;
		this.father = null;
		this.eldestSon = null;
		this.mySelf = mySelf;
		this.half = null;
		this.isMarried = false;//默认未婚
		this.isAlive = true;//默认健在
	}

	public User getGgorjj() {
		return ggorjj;
	}

	public void setGgorjj(User ggorjj) {
		this.ggorjj = ggorjj;
	}

	public User getMmordd() {
		return mmordd;
	}

	public void setMmordd(User mmordd) {
		this.mmordd = mmordd;
	}

	public User getFather() {
		return father;
	}

	public void setFather(User father) {
		this.father = father;
	}

	public User getEldestSon() {
		return eldestSon;
	}

	public void setEldestSon(User eldestSon) {
		this.eldestSon = eldestSon;
	}

	public User getMySelf() {
		return mySelf;
	}

	public void setMySelf(User mySelf) {
		this.mySelf = mySelf;
	}

	public User getHalf() {
		return half;
	}

	public void setHalf(User half) {
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
