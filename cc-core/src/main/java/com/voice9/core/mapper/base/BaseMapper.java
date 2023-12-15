package com.voice9.core.mapper.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface BaseMapper<T> {

    /**
     * 查询总数
     *
     * @param params
     * @return
     */
    Integer selectCountByMap(Map<String, Object> params);

    /**
     * 分页
     *
     * @param params
     * @return
     */
    List<T> selectListByMap(Map<String, Object> params);


    /**
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 插入当前月表数据
     *
     * @param record
     * @return
     */
    int insertMonthSelective(T record);

    /**
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);


    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);


    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);


    T selectById(@Param("companyId") Long companyId, @Param("id") Long id);
}
