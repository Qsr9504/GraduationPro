package com.qsr.graduationpro.app;

/**************************************
 * FileName : com.qsr.graduationpro.app
 * Author : qsr
 * Time : 2017/1/3 20:53
 * Description : 数据常量
 **************************************/
public class Constants {
	public interface mySP{
		final String OPEN_CHECK = "open_check";//是否开启版本检查
		final String IS_FRIST = "is_frist";//是否开启版本检查
	}
	public interface stateCode{//请求状态码
		final int STATE_SUCCESS = 1;
		final int STATE_ERROR = 0;
	}
	public interface eventString{//请求指令
		final String EVENT_VERSION = "event_checkVersion";//版本信息检测指令
	}
}
