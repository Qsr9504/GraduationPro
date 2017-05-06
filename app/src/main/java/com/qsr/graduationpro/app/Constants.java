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
		final String CURRENT_USERNODE = "current_usernode";//当前用户结点
		final String CURRENT_USER = "current_user";//当前用户结点
		final String USERNODE = "usernode";//用户结点
	}
	public interface stateCode{//请求状态码  1
		final int STATE_SUCCESS = 11;
		final int STATE_ERROR = 12;
		final int STATE_NOT_FOUND = 13;
		final int STATE_UNKNOW = 14;
		final int STATE_PROGRESS = 15;
	}
	public interface relativeCode{//关系代码
		final int DIDI = 0;//弟弟
		final int GEGE = 1;//哥哥
		final int FUQIN = 2;//父亲
		final int ZHANGZI = 3;//长子
		final int QIZI = 4;//妻子

	}
	public interface eventString{//请求指令
		final String EVENT_VERSION = "event_checkVersion";//版本信息检测指令
		final String EVENT_LOGIN = "event_login";//用户登录指令
		final String EVENT_REGISTER = "event_register";//用户注册指令
		final String EVENT_USERNODE_BY_USER = "event_user_node_nfo_byuser";//用户结点信息
		final String EVENT_USERNODE_BY_USERNAME = "event_user_node_nfo_byusername";//用户结点信息
		final String EVENT_ASSOCIATE = "event_associate";//添加用户关系命令
	}
	public interface mesWhat{//handle消息处理的what信息
	}
	public interface enevtBus{
		final String BUS_MAIN_USER = "bus_main_user";
		final String BUS_MAIN_USERNODE = "bus_main_usernode";
		final String BUS_REFRESH = "bus_main_refresh";
		final String BUS_USERINFO = "bus_userinfo";
		final String BUS_SEND_AVATAR = "bus_send_avatar";
		final String BUS_UPDATE_USER = "bus_update_user";
	}
}
