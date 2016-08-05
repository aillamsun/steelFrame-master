package com.sung.sframe.common.constants;

/**
 * Created with IntelliJ IDEA.
 * User: jacks
 * Date: 2016/6/14
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";


    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    /**
     * 当前登录用户名
     */
    String CURRENT_USERNAME = "username";
    /**
     * 系统编码
     */
    String ENCODING = "UTF-8";


    String SYSNAME = "SFrame 脚手架";
}
