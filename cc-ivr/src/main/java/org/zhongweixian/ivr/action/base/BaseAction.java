package org.zhongweixian.ivr.action.base;

import org.apache.commons.logging.Log;
import org.apache.commons.scxml2.ActionExecutionContext;
import org.apache.commons.scxml2.ErrorReporter;
import org.apache.commons.scxml2.EventDispatcher;
import org.apache.commons.scxml2.SCInstance;
import org.apache.commons.scxml2.model.Action;
import org.apache.commons.scxml2.model.ActionExecutionError;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.zhongweixian.ivr.cache.CacheService;

import java.util.Collection;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/23
 */
public abstract class BaseAction extends Action {
    protected Logger logger = LoggerFactory.getLogger(BaseAction.class);


    @Autowired
    protected CacheService cacheService;

    /**
     * 执行action
     *
     * @param scInstance
     * @param callInfo
     */
    public abstract void execute(SCInstance scInstance, CallInfo callInfo);

    @Override
    public void execute(ActionExecutionContext exctx) throws ActionExecutionError {

    }

    public void execute(EventDispatcher eventDispatcher, ErrorReporter errorReporter, SCInstance scInstance, Log log, Collection collection) {
        Long callId = (Long) scInstance.getRootContext().get("_callId");
        CallInfo callInfo = cacheService.getCallInfo(callId);
        execute(scInstance, callInfo);
    }


    /**
     * 表达式替换
     *
     * @param params
     * @param body   #{[name]}
     */
    protected String expression(Map<String, Object> params, String body) {
        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext();
        String content = null;
        try {
            content = parser.parseExpression(body, parserContext).getValue(params, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return content;
    }
}
