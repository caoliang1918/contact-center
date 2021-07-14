package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.AgentSip;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.AgentSipPo;

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
     * 查询sip号码
     *
     * @param companyId
     * @param id
     * @return
     */
    AgentSip selectById(@Param("companyId") Long companyId, @Param("id") Long id);
}