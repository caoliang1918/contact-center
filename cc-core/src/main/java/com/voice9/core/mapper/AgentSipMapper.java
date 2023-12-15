package com.voice9.core.mapper;

import com.voice9.core.entity.AgentSip;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.AgentSipPo;

import java.util.List;
import java.util.Map;

public interface AgentSipMapper extends BaseMapper<AgentSip> {

    /**
     * 查询坐席sip
     *
     * @param agentId
     * @return
     */
    List<String> selectByAgent(Long agentId);

    /**
     * 批量插入
     *
     * @param mapList
     * @return
     */
    int batchInsert(List<Map<String, Object>> mapList);

    /**
     * 查询sip号码
     *
     * @param params
     * @return
     */
    List<AgentSipPo> selectAgentSip(Map<String, Object> params);


    /**
     * @param sip
     * @return
     */
    AgentSip selectBySip(Integer sip);

    /**
     *
     * @return
     */
    Integer maxSip();


}