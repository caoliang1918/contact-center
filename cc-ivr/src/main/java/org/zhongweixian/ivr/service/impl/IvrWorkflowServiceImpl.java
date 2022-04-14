package org.zhongweixian.ivr.service.impl;

import org.cti.cc.entity.IvrWorkflow;
import org.cti.cc.mapper.IvrWorkflowMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhongweixian.ivr.service.IvrWorkflowService;

/**
 * Created by caoliang on 2022/3/15
 */
@Service
public class IvrWorkflowServiceImpl extends BaseServiceImpl<IvrWorkflow> implements IvrWorkflowService {

    @Autowired
    private IvrWorkflowMapper ivrWorkflowMapper;

    @Override
    BaseMapper<IvrWorkflow> baseMapper() {
        return ivrWorkflowMapper;
    }
}
