package com.voice9.api.service;

import com.github.pagehelper.PageInfo;
import com.voice9.core.entity.CompanyPhone;
import com.voice9.core.po.CompanyDisplayPo;
import com.voice9.core.vo.CompanyPhoneVo;
import com.voice9.core.vo.DisplayGroupVo;

import java.util.Map;

/**
 * 显号号码
 * <p>
 * Created by caoliang on 2021/5/26
 */
public interface DisplayService extends BaseService<CompanyPhone> {

    /**
     * 添加企业号码
     *
     * @param companyPhoneVo
     * @return
     */
    int saveOrUpdatePhone(CompanyPhoneVo companyPhoneVo);

    /**
     * 删除显号
     *
     * @param companyId
     * @param id
     * @return
     */
    int deletePhone(Long companyId, Long id);


    /**
     * 号码池分页
     *
     * @param params
     * @return
     */
    PageInfo<CompanyDisplayPo> findDisplayByPage(Map<String, Object> params);

    /**
     * 获取号码池详情
     *
     * @param companyId
     * @param id
     * @return
     */
    CompanyDisplayPo getDisplay(Long companyId, Long id);

    /**
     * 新增修改号码池
     *
     * @param displayGroupVo
     * @return
     */
    int saveOrUpdateDisplay(DisplayGroupVo displayGroupVo);

    /**
     * 删除号码池
     *
     * @param companyId
     * @param id
     * @return
     */
    int deleteDisplay(Long companyId, Long id);
}
