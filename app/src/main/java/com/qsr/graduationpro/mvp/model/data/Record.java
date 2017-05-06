package com.qsr.graduationpro.mvp.model.data;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.model.data
 * Author : qsr
 * Time : 2017/3/13 19:34
 * Description : 履历的实体类
 **************************************/
public class Record extends BmobObject {
	private String recordOwner;//履历主人（外键）
	private String recordContent;//履历详细内容
	private String recordTime;//履历发生时间
	private String recordType;//履历类型
	private String recordPlace;//履历发生地点

	public Record() {}

	public Record(String recordOwner) {
		this.recordOwner = recordOwner;
	}

	public String getRecordOwner() {
		return recordOwner;
	}

	public void setRecordOwner(String recordOwner) {
		this.recordOwner = recordOwner;
	}

	public String getRecordContent() {
		return recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordPlace() {
		return recordPlace;
	}

	public void setRecordPlace(String recordPlace) {
		this.recordPlace = recordPlace;
	}

	@Override
	public String toString() {
		return "Record{" +
				"recordOwner=" + recordOwner +
				", recordContent='" + recordContent + '\'' +
				", recordTime='" + recordTime + '\'' +
				", recordType='" + recordType + '\'' +
				", recordPlace='" + recordPlace + '\'' +
				'}';
	}
}
