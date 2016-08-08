package com.sung.sframe.common.dao.mongodb;

import com.sung.sframe.common.exception.SteelFrameBaseException;
import com.sung.sframe.common.model.BaseModel;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * 封装MongoDB
 * Created by sungang on 2016/8/8.
 */
public interface BaseMongoDao<T extends BaseModel> {


    /**
     * 保存单个对象
     *
     * @param entity
     */
    T save(T entity) throws SteelFrameBaseException;


    /**
     * 批量添加数据
     *
     * @param entitys
     */
    void batchSave(Collection<T> entitys) throws SteelFrameBaseException;

    /**
     * 查询多个数据
     * @param t
     * @return
     * @throws SteelFrameBaseException
     */
    List<T> find(T t) throws SteelFrameBaseException;

    /**
     * 查询单个数据
     *
     * @param t
     * @return
     */
    T findOne(T t) throws SteelFrameBaseException;

    /**
     * 通过ID获取记录
     * @param id
     * @return
     * @throws SteelFrameBaseException
     */
    T findById(String id) throws SteelFrameBaseException;

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     * @param id
     * @param collectionName
     * @return
     */
    T findById(String id, String collectionName);

    /**
     * 更新数据
     * @param query
     * @param update
     */
    void update(Query query, Update update);

    /**
     * 根据 id 跟新数据
     * @param id
     * @param t
     */
    void updateById(String id, T t);

    /**
     * count
     * @param t
     * @return
     */
    long count(T t);
}
