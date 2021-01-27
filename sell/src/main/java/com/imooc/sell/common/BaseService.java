package com.imooc.sell.common;

/**
 * @author ：David.Yan
 * @date ：Created in 2019/3/10 20:05
 * @description：
 * @modified By：
 * @version:
 */
import com.baomidou.mybatisplus.extension.service.IService;

public interface BaseService< T extends BaseEntity> extends IService<T>{

   /* *//**
     * 获取单条数据
     * @param id
     * @return
     *//*
    public T get(String id);
    *//**
     * 获取单条数据
     * @param entity
     * @return
     *//*
    public T get(T entity);
    *//**
     * 查询列表数据
     * @param entity
     * @return
     *//*
    public List<T> findList(T entity);
    *//**
     * 分页查询（会统计记录总数信息 在Page对象里）
     * @param entity
     * @return
     *//*
    public IPage<T> findPageList(Page page, T entity);

    //增加数据
    public boolean insert(T entity) ;

    boolean batchInsert();

    //修改数据
    public boolean update(T entity) ;
    //删除数据
    public boolean delete(T entity) ;

    //删除数据
    public boolean deleteById(String id);

    //批量删除
    boolean batchDelete(List ids);*/


}
