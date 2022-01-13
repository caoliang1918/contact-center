package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.Group;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupInfo;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends BaseMapper<Group> {

    /**
     * @param params
     * @return
     */
    List<GroupInfo> selectGroupInfoList(Map<String, Object> params);

    /**
     * @param params
     * @return
     */
    GroupInfo selectGroupInfo(Map<String, Object> params);

    /**
     * 查询企业技能组id
     *
     * @param companyId
     * @return
     */
    List<Long> selectGroupIds(Long companyId);

    Long selectByName(@Param("companyId") Long companyId , @Param("name")String name);

}