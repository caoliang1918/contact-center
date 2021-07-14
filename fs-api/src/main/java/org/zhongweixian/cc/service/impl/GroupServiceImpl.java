package org.zhongweixian.cc.service.impl;

import org.cti.cc.entity.Group;
import org.cti.cc.mapper.GroupMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
@Component
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    BaseMapper<Group> baseMapper() {
        return groupMapper;
    }

    @Override
    public List<GroupInfo> getGroupByConpany(Long companyId) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        return groupMapper.selectGroupInfoList(params);
    }
}
