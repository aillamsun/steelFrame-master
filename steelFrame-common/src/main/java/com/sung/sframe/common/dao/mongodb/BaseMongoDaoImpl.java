package com.sung.sframe.common.dao.mongodb;

import com.sung.sframe.common.dao.mongodb.annotations.QueryField;
import com.sung.sframe.common.exception.SteelFrameBaseException;
import com.sung.sframe.common.model.BaseModel;
import com.sung.sframe.common.query.Pager;
import com.sung.sframe.common.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public List<T> find(T entity) {
        Assert.isNull(entity, "entity is not null");
        Query query = buildBaseQuery(entity);
        return mongoTemplate.find(query, entityClass);
    }


    @Override
    public T findOne(T entity) {
        Assert.isNull(entity, "entity is not null");
        Query query = buildBaseQuery(entity);
        return mongoTemplate.findOne(query, entityClass);
    }


    @Override
    public void update(Query query, Update update) {
        Assert.isNull(query, "query is not null");
        Assert.isNull(update, "update is not null");
        mongoTemplate.findAndModify(query, update, entityClass);
    }

    @Override
    public void updateFirst(Query query, Update update) throws SteelFrameBaseException {
        Assert.isNull(query, "query is not null");
        Assert.isNull(update, "update is not null");
        mongoTemplate.updateFirst(query, update, entityClass);
    }

    @Override
    public void updateById(String id, T entity) {
        Assert.isNull(id, "id is not null");
        Assert.isNull(entity, "entity is not null");
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = buildBaseUpdate(entity);
        update(query, update);
    }

    @Override
    public void batchUpdate(Map<String, T> updates) throws SteelFrameBaseException {
        Assert.isNull(updates, "你要更新的数据不能null");
        for (String id : updates.keySet()) {
            updateById(id, updates.get(id));
        }
    }

    @Override
    public T save(T entity) {
        Assert.isNull(entity, "entity is not null");
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        Assert.isNull(id, "id is not null");
        return mongoTemplate.findById(id, entityClass);
    }


    @Override
    public T findById(String id, String collectionName) {
        Assert.isNull(id, "id is not null");
        Assert.isNull(collectionName, "collectionName is not null");
        return mongoTemplate.findById(id, entityClass, collectionName);
    }


    @Override
    public long count(T entity) {
        Assert.isNull(entity, "entity is not null");
        Query query = buildBaseQuery(entity);
        return mongoTemplate.count(query, entityClass);
    }


    @Override
    public Pager<T> findByPage(Pager<T> pager, T entity) throws SteelFrameBaseException {
        Query query = buildBaseQuery(entity);
        long count = this.count(entity);
        pager.setTotalCount(count);
        int pageSize = pager.getPageSize();
        int fr = pager.getFirstResult();
        query.skip(fr);// skip相当于从那条记录开始
        query.limit(pageSize);// 从skip开始,取多少条记录
        List<T> datas = mongoTemplate.find(query, entityClass);
        pager.setDatas(datas);
        return pager;
    }


    private Query buildBaseQuery(T entity) {
        Query query = new Query();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
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
     * @param entity
     * @return
     */
    private Update buildBaseUpdate(T entity) {
        Update update = new Update();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value != null) {
                    update.set(field.getName(), value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update;
    }

//    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);
}
