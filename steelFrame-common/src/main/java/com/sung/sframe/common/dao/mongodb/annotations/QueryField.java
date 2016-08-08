package com.sung.sframe.common.dao.mongodb.annotations;

import com.sung.sframe.common.dao.mongodb.enums.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sungang on 2016/8/8.
 * 用于实体Bean的属性上的注解，注解有两个属性可以设置，type表示查询类似，默认为equals
 * attribute表示要查询的属性，默认为空串，在使用时如果为空串，则默认为实体Bean字段的名称
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {

    QueryType type() default QueryType.EQUALS;
    String attribute() default "";
}
