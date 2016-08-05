package com.sung.sframe.controller;

import com.sung.sframe.common.utils.LogUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

/**
 *
 * 配置一个通配的url请求，替代@RequestMapping中指定的方式
 *
 * Created with IntelliJ IDEA.
 * User: jacks
 * Date: 2016/6/16
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
public class ControllerClassNameHandlerMapping extends AbstractControllerUrlHandlerMapping{
    /**
     * 是否区分大小写
     */
    private boolean caseSensitive = false;

    /**
     * controller 所在包路径的前缀
     */
    private String[] frameworkPackagePrefixs = new String[] {"com.sung.sframe."};
    /**
     * controller 所在包路径的后缀
     */
    private String[] controllerPackageSuffixs = new String[] {"controller"};

    /**
     * controller的类后缀
     */
    private String[] controllerClassSuffixs = new String[]{"Controller"};

    public ControllerClassNameHandlerMapping(){
        super();
    }

    @Override
    protected String[] buildUrlsForHandler(String beanName, Class<?> beanClass) {
        LogUtils.logInfo("Controller类名称:" + beanName + "Controller 类信息:" + beanClass.getName());
        //请求路径前缀
        StringBuilder pathMapping = buildPathPrefix(beanClass);
        LogUtils.logInfo("Controller Path Mapping:" + pathMapping);
        //获取类名
        String className = ClassUtils.getShortName(beanClass);
        //请求路径后缀
        String path = getEndWithSuffixPath(className, controllerClassSuffixs);
        LogUtils.logInfo("Path:" + path);

        if (path.length() > 0) {
            if (this.caseSensitive) {
                pathMapping.append(path.substring(0, 1).toLowerCase()).append(path.substring(1));
            }
            else {
                pathMapping.append(path.toLowerCase());
            }
        }

        //最终转换后的请求url路径
        String[] finalPath = null;
        if (isMultiActionControllerType(beanClass)) {
            finalPath = new String[] {pathMapping.toString(), pathMapping.toString() + "/*"};
        }
        else {
            finalPath = new String[] {pathMapping.toString() + "*"};
        }
        for (String str : finalPath) {
            LogUtils.logInfo("FinalPath:" + str);
        }
        return finalPath;
    }

    /**
     * buildPathPrefix:生成请求前缀.
     * @param beanClass
     * @return
     */
    private StringBuilder buildPathPrefix(Class beanClass) {
        StringBuilder pathMapping = new StringBuilder();
        pathMapping.append("/");
        //在这里加入自己的URL映射逻辑规范
        pathMapping.append(buildPathByBeanClass(beanClass, frameworkPackagePrefixs, controllerPackageSuffixs, caseSensitive));
        return pathMapping;
    }

    /**
     * buildPathByBeanClass:根据类名称和包名称来组成自定义的URL映射
     * @param beanClass
     * @param frameworkPackagePrefixs
     * @param controllerPackageSuffixs
     * @param caseSensitive
     * @return
     */
    private String buildPathByBeanClass(Class beanClass, String[] frameworkPackagePrefixs, String[] controllerPackageSuffixs, boolean caseSensitive) {
        String packageName = ClassUtils.getPackageName(beanClass);
        if (StringUtils.isEmpty(packageName)) {
            return "";
        }
        String actionPackageSuffix = "";
        for (String packageSuffix : controllerPackageSuffixs) {
            if (packageName.endsWith(packageSuffix)) {
                actionPackageSuffix = packageSuffix;
                break;
            }
        }
        String newpackageName="";
        for (String frameworkPackagePrefix : frameworkPackagePrefixs) {
            if (packageName.startsWith(frameworkPackagePrefix)) {
                newpackageName = StringUtils.replace(packageName, frameworkPackagePrefix, "");
//              break;
            }
        }
        newpackageName = StringUtils.replace(newpackageName, actionPackageSuffix, "");
        newpackageName = StringUtils.replace(newpackageName, ".", "/");
        return (caseSensitive ? newpackageName : newpackageName.toLowerCase()) + "/";
    }

    /**
     * getEndWithSuffixPath:根据类后缀数组筛选出path的结果
     * @param className
     * @param suffixs
     * @return
     */
    private String getEndWithSuffixPath(String className, String[] suffixs) {
        String path = "";
        for (String suffix : suffixs) {
            if (className.endsWith(suffix)) {
                path = className.substring(0, className.lastIndexOf(suffix));
                break;
            }
        }
        return StringUtils.isEmpty(path) ? className : path;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public String[] getFrameworkPackagePrefixs() {
        return frameworkPackagePrefixs;
    }

    public void setFrameworkPackagePrefixs(String[] frameworkPackagePrefixs) {
        this.frameworkPackagePrefixs = frameworkPackagePrefixs;
    }

    public String[] getControllerPackageSuffixs() {
        return controllerPackageSuffixs;
    }

    public void setControllerPackageSuffixs(String[] controllerPackageSuffixs) {
        this.controllerPackageSuffixs = controllerPackageSuffixs;
    }

    public String[] getControllerClassSuffixs() {
        return controllerClassSuffixs;
    }

    public void setControllerClassSuffixs(String[] controllerClassSuffixs) {
        this.controllerClassSuffixs = controllerClassSuffixs;
    }
}
