package org.zhongweixian.ivr.action.base;

import org.apache.commons.logging.Log;
import org.apache.commons.scxml.ErrorReporter;
import org.apache.commons.scxml.EventDispatcher;
import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.SCXMLExpressionException;
import org.apache.commons.scxml.model.Action;
import org.apache.commons.scxml.model.ModelException;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.ivr.cache.CacheService;

import java.util.Collection;

/**
 * Created by caoliang on 2021/9/23
 */
public abstract class BaseAction extends Action {
    protected Logger logger = LoggerFactory.getLogger(BaseAction.class);


    @Autowired
    protected CacheService cacheService;

    public abstract void execute(SCInstance scInstance, CallInfo callInfo);

    @Override
    public void execute(EventDispatcher eventDispatcher, ErrorReporter errorReporter, SCInstance scInstance, Log log, Collection collection) throws ModelException, SCXMLExpressionException {
        Long callId = (Long) scInstance.getRootContext().get("_callId");
        CallInfo callInfo = cacheService.getCallInfo(callId);
        execute(scInstance, callInfo);
    }
}
