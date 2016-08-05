package com.sung.sframe.shiro.web.session.mgt;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 *
 * 为OnlineSession定制的Web Session Manager
 * 主要是在此如果会话的属性修改了 就标识下其修改了 然后方便 OnlineSessionDao同步
 *
 * Created with IntelliJ IDEA.
 * User: jacks
 * Date: 2016/6/15
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class OnlineWebSessionManager extends DefaultWebSessionManager {


}
