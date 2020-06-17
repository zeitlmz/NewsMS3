package com.newsms.annotation.impl;

/**
 * 系统字典类
 *
 * @author lmz
 */
public class STUTIC {

    public static final String LOGIN_SUCCESS_STATUS = "100001";
    public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";
    public static final String LOGIN_FAILED_STATUS = "100002";
    public static final String LOGIN_ILLEGAL_STATUS = "100003";
    public static final String LOGIN_ILLEGAL_MESSAGE = "非法的请求，请按照网站导航继续访问你";

    public static final String PARAM_ILLEGAL_STATUS = "100004";
    public static final String PARAM_ILLEGAL_MESSAGE = "非法的请求参数映射";
    public static final String PARAM_ILLEGAL_URL = "403.html";
    public static final String PARAM_ILLEGAL_NOT_SUPPORT = "暂不支持该参数类型的转换";
    public static final String PARAM_ILLEGAL_NOTNULL = "参数不能为空！";

    public static final String RESPONSE_TYPE_NOTFOUND = "不可用的类型！";
    public static final String RESPONSE_TYPE_TEXT_HTML = "TEXT_HTML：输出文本或者html或者js代码片段！";
    public static final String RESPONSE_TYPE_JSON = "JSON：输出JSON格式的数据";
    public static final String RESPONSE_TYPE_PAGE = "PAGE：转发到指定jsp或者html页面";
    public static final String SEND_REDIRECT_SYNTAX = "重定向语法错误！应该类似如下：\nsendRedirect：/index.html";

}
