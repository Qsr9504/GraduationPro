package com.qsr.graduationpro.mvp.model.data;

import java.util.List;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/2/24 16:53
 * Description : 请求事件类
 **************************************/
public class Action<T> {
	private String event;//请求事件

	private List<T> resultDataList;//返回结果集合

	private T resultData;//返回结果对象

	private String describe;//描述

	private int state;//状态

	private T requestData;//访问 所需对象

	private List<T> requestDataList;//访问 所需对象集合

	public T getRequestData() {
		return requestData;
	}

	public void setRequestData(T requestData) {
		this.requestData = requestData;
	}

	public List<T> getRequestDataList() {
		return requestDataList;
	}

	public void setRequestDataList(List<T> requestDataList) {
		this.requestDataList = requestDataList;
	}

	public Action() {
	}

	public Action(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<T> getResultDataList() {
		return resultDataList;
	}

	public void setResultDataList(List<T> resultDataList) {
		this.resultDataList = resultDataList;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
