package com.sung.sframe.common.controller;

import com.sung.sframe.common.model.BaseModel;
import com.sung.sframe.common.utils.LogUtils;
import com.sung.sframe.common.utils.ReflectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sungang on 2016/6/10.
 */
public abstract class AbstarctBaseController<M extends BaseModel> {
    /**
     * 实体类型
     */
    protected final Class<M> entityClass;

    private String viewPrefix;

    protected AbstarctBaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        setViewPrefix(defaultViewPrefix());
    }

    /**
     * 设置通用数据
     *
     * @param model
     */
    protected void setCommonData(Model model) {

    }

    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        String viewName = getViewPrefix() + suffixName;
        LogUtils.logInfo("viewName======= " + viewName);
        return viewName;
    }

    /**
     * 共享的验证规则
     * 验证失败返回true
     *
     * @param m
     * @param result
     * @return
     */
    protected boolean hasError(M m, BindingResult result) {
        return result.hasErrors();
    }

    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        String redirectUrl = getViewPrefix() + backURL;
        LogUtils.logInfo("redirectToUrl:======= " + redirectUrl);
        return "redirect:" + redirectUrl;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }



}
