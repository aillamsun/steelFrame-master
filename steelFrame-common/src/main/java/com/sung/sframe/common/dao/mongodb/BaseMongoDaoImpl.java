package com.sung.sframe.common.dao.mongodb;

import com.sung.sframe.common.dao.mongodb.annotations.QueryField;
import com.sung.sframe.common.model.BaseModel;
import com.sung.sframe.common.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by sungang on 2016/8/8.
 */
public abstract class BaseMongoDaoImpl<T extends BaseModel> implements BaseMongoDao<T> {

    private static final int DEFAULT_SKIP = 0;
    private static final int DEFAULT_LIMIT = 200;

    /**
     * 实体类型
     */
    protected final Class<T> entityClass;

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected BaseMongoDaoImpl() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
    }


    @Override
    public List<T> find(T t) {
        Query query = buildBaseQuery(t);
        return mongoTemplate.find(query, entityClass);
    }


    @Override
    public T findOne(T t) {
        Query query = buildBaseQuery(t);
        return mongoTemplate.findOne(query, entityClass);
    }


    @Override
    public void update(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, entityClass);
    }

    @Override
    public void updateById(String id, T t) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = buildBaseUpdate(t);
        update(query, update);
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, entityClass);
    }


    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, entityClass, collectionName);
    }


    @Override
    public long count(T t) {
        Query query = buildBaseQuery(t);
        return mongoTemplate.count(query, entityClass);
    }


    private Query buildBaseQuery(T t) {
        Query query = new Query();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    QueryField queryField = field.getAnnotation(QueryField.class);
                    if (queryField != null) {
                        query.addCriteria(queryField.type().buildCriteria(queryField, field, value));
                    }
                }
            } catch (Exception e) {
                // should not happend
            }
        }
        return query;
    }

    /**
     *
     * @param t
     * @return
     */
    private Update buildBaseUpdate(T t) {
        Update update = new Update();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    update.set(field.getName(), value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);
}
