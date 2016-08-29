package com.sung.sframe.common.controller;


import com.alibaba.fastjson.JSONObject;
import com.sung.sframe.common.constants.Constants;
import com.sung.sframe.common.controller.permission.PermissionList;
import com.sung.sframe.common.model.BaseModel;
import com.sung.sframe.common.service.BaseService;
import com.sung.sframe.common.service.IService;
import com.sung.sframe.common.utils.UuidUtil;
import com.sung.sframe.common.web.utils.SpringMvcUtils;
import com.sung.sframe.common.web.validate.BeanValidators;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.IOException;
import java.util.List;

/**
 * Created by sungang on 2016/6/9.
 */
public class BaseCRUDController<M extends BaseModel> extends AbstarctBaseController<M> {

    private static final long serialVersionUID = 6357869213649815390L;

    protected final String ADD = getViewPrefix() + "/add";
    protected final String EDIT = getViewPrefix() + "/edit";
    protected final String DETAILS = getViewPrefix() + "/details";
    protected final String LIST = getViewPrefix() + "/list";

    /**
     * 权限
     */
    protected PermissionList permissionList = null;


    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;


    @Override
    public IService<M> getBaseService() {
        return null;
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }


    /**
     * 功能说明：通用列表查询方法
     *
     * @param model
     * @param item
     * @return
     */
//    @RequestMapping("list_com")
//    protected String list(Model model, @ModelAttribute M item) {
//        getBaseService().selectByExample(item);
//        return LIST;
//    }

    /**
     * 功能说明：通用跳转到新增页面
     *
     * @param model
     * @param item
     * @return
     */
//    @RequestMapping(value = "add_com",method = RequestMethod.POST)
//    protected String add(Model model, @ModelAttribute M item) {
//        baseService.insert(item);
//        model.addAttribute(Constants.OP_NAME, "新增");
//        return ADD;
//    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(Country country) {
//        if (country.getId() != null) {
//            countryService.updateAll(country);
//        } else {
//            countryService.insert(country);
//        }
//        return "redirect:" + page_list;
//    }

    /**
     * 功能说明：通用跳转到编辑页面
     *
     * @param model
     * @param id
     * @return
     */
//    @RequestMapping(value = "/edit/{id}")
//    protected String edit(Model model, @PathVariable("id") String id) {
//        M m = baseService.selectByKey(id);
//        model.addAttribute(m);
//        return EDIT;
//    }

    /**
     * 功能说明：通用详情页面
     *
     * @param model
     * @param id
     * @return
     */
//    @RequestMapping(value = "/details/{id}")
//    protected String details(Model model, @PathVariable("id") String id) {
//        M m = baseService.selectByKey(id);
//        model.addAttribute(m);
//        return DETAILS;
//    }

    /**
     * 功能说明：通用新增 更新
     *
     * @param item
     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    protected void save(@ModelAttribute M item) {
//        SpringMvcUtils.responseJSONWriter();
//    }

    /**
     * 功能说明：通用删除
     *
     * @param id
     */
//    @RequestMapping(value = "/delete/{id}")
//    private void delete(@PathVariable("id") String id) {
//        SpringMvcUtils.responseJSONWriter();
//    }


    /**
     * 得到ModelAndView
     *
     * @return
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 服务端参数有效性验证
     *
     * @param model  验证的实体对象
     * @param object 验证组
     * @param groups 验证成功：返回true；严重失败：将错误信息添加到 message 中
     * @return
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {

        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }


    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }


    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }


    /**
     * 添加Model消息
     *
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     *
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }


    /**
     * 客户端返回JSON字符串
     *
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JSONObject.toJSONString(object), "application/json");
    }

    /**
     * 客户端返回字符串
     *
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }


    /***
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
   /* @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
        });
    }*/

    /**
     * 得到32位的uuid
     *
     * @return
     */
    public String get32UUID() {
        return UuidUtil.get32UUID();
    }


}
