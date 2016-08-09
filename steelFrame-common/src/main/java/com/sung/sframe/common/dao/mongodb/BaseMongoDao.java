package com.sung.sframe.common.dao.mongodb;

import com.sung.sframe.common.exception.SteelFrameBaseException;
import com.sung.sframe.common.model.BaseModel;
import com.sung.sframe.common.query.Pager;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
     * @param entity
     * @return
     * @throws SteelFrameBaseException
     */
    List<T> find(T entity) throws SteelFrameBaseException;

    /**
     * 查询单个数据
     *
     * @param entity
     * @return
     */
    T findOne(T entity) throws SteelFrameBaseException;

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
    T findById(String id, String collectionName) throws SteelFrameBaseException;

    /**
     * 通过条件查询更新数据
     * @param query
     * @param update
     */
    void update(Query query, Update update) throws SteelFrameBaseException;

    /**
     * 通过条件查询更新数据
     * @param query
     * @param update
     * @throws SteelFrameBaseException
     */
    void updateFirst(Query query, Update update) throws SteelFrameBaseException;

    /**
     * 根据 id 跟新数据
     * @param id
     * @param entity
     */
    void updateById(String id, T entity) throws SteelFrameBaseException;

    /**
     * 批量更新
     * Map<> key -> id , value - T
     * @param entitys
     * @throws SteelFrameBaseException
     */
    void batchUpdate(Map<String,T> entitys) throws SteelFrameBaseException;

    /**
     * count
     * @param t
     * @return
     */
    long count(T t)throws SteelFrameBaseException;

    /**
     * 分页查询数据
     * @param pager 分页对象
     * @param entity 查询条件
     * @return
     * @throws SteelFrameBaseException
     */
    Pager<T> findByPage(Pager<T> pager,T entity)throws SteelFrameBaseException;
}
