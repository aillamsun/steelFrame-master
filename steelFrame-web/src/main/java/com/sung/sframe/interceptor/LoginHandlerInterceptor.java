package com.sung.sframe.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名称：登录过滤，权限验证
 * Created by sungang on 2016/6/10.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String path = request.getServletPath();
//        if(path.matches(SysConst.NO_INTERCEPTOR_PATH)){
//            return true;
//        }else{
//            User user = (User) Jurisdiction.getSession().getAttribute(SysConst.SESSION_USER);
//            if(user!=null){
//                path = path.substring(1, path.length());
//                boolean b = Jurisdiction.hasJurisdiction(path); //访问权限校验
//                if(!b){
//                    response.sendRedirect(request.getContextPath() + SysConst.LOGIN);
//                }
//                return b;
//            }else{
//                //登陆过滤
//                response.sendRedirect(request.getContextPath() + SysConst.LOGIN);
//                return false;
//            }
//        }
        return true;
    }
}
