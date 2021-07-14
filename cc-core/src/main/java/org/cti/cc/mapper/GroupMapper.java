package org.cti.cc.mapper;

import org.cti.cc.entity.Group;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupInfo;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends BaseMapper<Group> {

    /**
     *
     * @param params
     * @return
     */
    List<GroupInfo> selectGroupInfoList(Map<String , Object> params);

    /**
     *
     * @param params
     * @return
     */
    GroupInfo selectGroupInfo(Map<String , Object> params);

}