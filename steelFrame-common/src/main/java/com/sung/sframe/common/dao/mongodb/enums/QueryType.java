package com.sung.sframe.common.dao.mongodb.enums;

import com.sung.sframe.common.dao.mongodb.annotations.QueryField;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by sungang on 2016/8/8.
 */
public enum QueryType {

    EQUALS {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).is(value.toString());
            }
            return new Criteria();
        }
    },
    LIKE {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).regex(value.toString());
            }
            return new Criteria();
        }
    },
    GT {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).gt(value.toString());
            }
            return new Criteria();
        }
    },
    GTE {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).gte(value.toString());
            }
            return new Criteria();
        }
    },
    LT {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).lt(value.toString());
            }
            return new Criteria();
        }
    },
    LTE {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                String queryField = getQueryFieldName(queryFieldAnnotation, field);
                return Criteria.where(queryField).lte(value.toString());
            }
            return new Criteria();
        }
    },
    IN {
        @Override
        public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
            if (check(queryFieldAnnotation, field, value)) {
                if (value instanceof List) {
                    String queryField = getQueryFieldName(queryFieldAnnotation, field);
                    // 此处必须转型为List，否则会在in外面多一层[]
                    return Criteria.where(queryField).in((List<?>) value);
                }
            }
            return new Criteria();
        }
    };

    private static boolean check(QueryField queryField, Field field, Object value) {
        return !(queryField == null || field == null || value == null);
    }

    public abstract Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value);

    /**
     * 如果实体bean的字段上QueryField注解没有设置attribute属性时，默认为该字段的名称
     *
     * @param field
     * @return
     */
    private static String getQueryFieldName(QueryField queryField, Field field) {
        String queryFieldValue = queryField.attribute();
        if (!StringUtils.hasText(queryFieldValue)) {
            queryFieldValue = field.getName();
        }
        return queryFieldValue;
    }
}
