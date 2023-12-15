package com.voice9.api.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
public interface BaseService<T> {

    /**
     * 根据记录新增
     *
     * @param record
     * @return
     */
    int add(T record);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id修改（只修改不为空的字段）
     *
     * @param record
     * @return
     */
    int editById(T record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    T findById(Long id);


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageInfo<T> findByPageParams(Map<String, Object> params);

}
