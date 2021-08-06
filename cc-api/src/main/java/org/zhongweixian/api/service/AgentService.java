package org.zhongweixian.api.service;

import com.github.pagehelper.PageInfo;
import org.cti.cc.entity.Agent;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentSipPo;
import org.cti.cc.vo.AgentVo;
import org.zhongweixian.api.vo.AgentSipVo;

import java.util.List;
import java.util.Map;


public interface AgentService extends BaseService<Agent> {


    /**
     * 添加修改坐席
     *
     * @param agent
     * @return
     */
    int saveOrUpdate(AgentVo agent);

    /**
     * 批量添加坐席
     *
     * @param companyId
     * @param count
     * @param prefix
     * @param start
     * @param pwd
     * @return
     */
    Integer batchAddAgent(Long companyId, Integer count, String prefix, Long start, String pwd);

    /**
     * 获取坐席详情
     *
     * @param companyId
     * @param id
     * @return
     */
    AgentInfo getAgentInfo(Long companyId, Long id);

    /**
     * 技能中添加坐席
     *
     * @param companyId
     * @param skillId   技能ID
     * @param ids       坐席ID
     * @param rank      技能等级
     * @return
     */
    int agentBindSkill(Long companyId, Long skillId, List<Long> ids, Integer rank);

    /**
     * 删除坐席
     *
     * @param companyId
     * @param id
     * @return
     */
    int deleteAgent(Long companyId, Long id);

    /**
     * sip列表
     *
     * @param params
     * @return
     */
    PageInfo<AgentSipPo> agentSipList(Map<String, Object> params);

    /**
     * 添加sip号码
     *
     * @param agentSipVo
     * @return
     */
    int saveOrUpdateAgentSip(AgentSipVo agentSipVo);

    /**
     * 删除sip
     *
     * @param companyId
     * @param id
     * @return
     */
    int deleteSip(Long companyId, Long id);
}
