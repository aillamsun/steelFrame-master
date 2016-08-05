package com.sung.sframe.resolver;

import com.sung.sframe.common.utils.LogUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring 统一异常处理
 * Created by sungang on 2016/6/10.
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //把漏网的异常信息记入日志
        LogUtils.logError("Catch Exception: ", ex);
        StringPrintWriter printWriter = new StringPrintWriter();
        ex.printStackTrace(printWriter);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", printWriter.getString());
        return mv;
    }
}
