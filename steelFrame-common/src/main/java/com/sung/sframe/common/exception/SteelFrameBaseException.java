package com.sung.sframe.common.exception;

import com.sung.sframe.common.utils.MessageUtils;
import org.springframework.util.StringUtils;

/**
 *
 * 基础异常
 *
 * Created with IntelliJ IDEA.
 * User: jacks
 * Date: 2016/6/15
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class SteelFrameBaseException extends RuntimeException {

    //所属模块
    private String module;
    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;


    public SteelFrameBaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }


    public SteelFrameBaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public SteelFrameBaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public SteelFrameBaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public SteelFrameBaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }
}
