package com.qsr.graduationpro.utils;

import android.text.TextUtils;

/**************************************
 * FileName : com.example.qsr.fav_deal.utils
 * Author : qsr
 * Time : 2016/7/26 12:55
 * Description : TextView工具类
 * 主要功能: 判断多个字符串中是否有空串
 **************************************/
public class TextUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String ... strings){
        for (String s: strings){
            if(TextUtils.isEmpty(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean MyIsEmpty(String str){
        if(str == null)
            return true;
        if(str.equals(""))
            return true;
        return false;
    }
    //判断登录输入是否合法
//    public static boolean
}
