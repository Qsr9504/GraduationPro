package com.qsr.graduationpro.utils;

import com.qsr.graduationpro.app.Constants;
import com.qsr.graduationpro.mvp.model.data.Action;
import com.qsr.graduationpro.mvp.model.data.User;

/**************************************
 * FileName : com.qsr.graduationpro.utils
 * Author : qsr
 * Time : 2017/3/13 20:12
 * Description : 这里需要详细再写写，验证没有用的！！！！
 **************************************/
public class LoginUtil {
	private static String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";
	public static boolean doCheckLoginMes(String account,String password){
		//检查是否为空字符串
		if(TextUtil.isEmpty(account,password)){
			ToastUtil.showShort("账户名或密码不能为空");return false;
		}
		if (!account.matches(regex)){
			ToastUtil.showShort("账户名不合法");return false;
		}
		if(password.length() > 6){
			ToastUtil.showShort("密码最低6位");return false;
		}
		return true;
	}

	public static boolean doCheckRegisterMes(String account,String psw1,String psw2){
		if(TextUtil.isEmpty(account,psw1,psw2)){
			ToastUtil.showShort("账号密码不能为空");return false;
		}
		if(!psw1.equals(psw2)){
			ToastUtil.showShort("两次密码不一致");return false;
		}
		if(!account.matches(regex)){
			ToastUtil.showShort("账户名不合法");return false;
		}
		if(psw1.length() < 6){
			ToastUtil.showShort("密码长度不能少于6位数");return false;
		}
		return true;
	}
}
