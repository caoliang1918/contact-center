package com.voice9.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voice9.api.service.BaseService;
import org.apache.commons.lang3.RandomStringUtils;
import com.voice9.core.mapper.CompanyMapper;
import com.voice9.core.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    abstract BaseMapper<T> baseMapper();

    /**
     * 批量插入时，每次插入条数
     */
    @Value("${batch.insert.cnt:500}")
    protected Integer batchInsertCnt;

    /**
     *
     */
    @Value("${voice9.minio.server}")
    protected String ossServer;

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected CompanyMapper companyMapper;


    @Override
    public int add(T record) {
        return baseMapper().insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int editById(T record) {
        return baseMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public T findById(Long id) {
        return baseMapper().selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<T> findByPageParams(Map<String, Object> params) {
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = baseMapper().selectListByMap(params);
        return new PageInfo<T>(list);
    }

    public String randomDelete() {
        return "-del" + RandomStringUtils.randomAlphabetic(6);
    }

}
