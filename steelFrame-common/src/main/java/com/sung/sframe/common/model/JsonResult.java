package com.sung.sframe.common.model;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by sungang on 2016/7/4.
 */
public class JsonResult<T> {
    /**
     * 结果
     */
    private boolean result;
    /**
     * 成功的消息
     */
    private String msg;

    /**
     * 具体每个输入错误的消息
     */
    private HashMap<String, String> errors = Maps.newHashMap();

    /**
     * 返回的数据
     */
    private T t;


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

//    public void check() throws ValidateException {
//        if (this.getErrors().size() > 0) {
//            this.setResult(false);
//            throw new ValidateException("有错误发生");
//        } else {
//            this.setResult(true);
//        }
//    }
}
