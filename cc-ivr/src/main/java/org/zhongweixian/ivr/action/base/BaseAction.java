package org.zhongweixian.ivr.action.base;

import io.minio.MinioClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.scxml2.*;
import org.apache.commons.scxml2.model.Action;
import org.apache.commons.scxml2.model.Log;
import org.cti.cc.constant.Constant;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zhongweixian.ivr.cache.CacheService;
import org.zhongweixian.ivr.util.SpringContextUtil;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/23
 */
public abstract class BaseAction extends Action {
    protected Logger logger = LoggerFactory.getLogger(BaseAction.class);

    private static final String YYYYMMDDHH = "yyyyMMddHH";

    protected CacheService cacheService;

    protected RestTemplate restTemplate;

    public BaseAction(){
        this.cacheService = SpringContextUtil.getBean(CacheService.class);
    }

    public abstract void execute(SCInstance scInstance, CallInfo callInfo);


    @Override
    public void execute(ActionExecutionContext context) {
        SCXMLExecutionContext executionContext = (SCXMLExecutionContext) context.getInternalIOProcessor();
        Object callId = executionContext.getScInstance().getRootContext().get("_callId");
        if (callId == null) {
            return;
        }
        CallInfo callInfo = cacheService.getCallInfo(Long.parseLong(callId.toString()));
        this.execute(executionContext.getScInstance(), callInfo);
    }


    /**
     * 通过http tts引擎来生产wav在线文件
     *
     * @param callInfo
     * @param content
     * @param params
     * @return
     */
    protected String playTts(CallInfo callInfo, String content, Map<String, Object> params) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (params != null) {
            expression(content, params);
        }
        // 1、调内部引用获取到生成wav后的文件
        String wav = "";

        // 2、把wav文件推送到文件存储
        String oss = "";

        String day = DateFormatUtils.format(new Date(), YYYYMMDDHH);
        return Constant.VOICE_BUCKET + Constant.SK + day + Constant.SK + oss;
    }

    /**
     * http请求
     *
     * @param callInfo
     * @param url
     * @param body
     * @return
     */
    protected String httpExecute(CallInfo callInfo, String url, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        logger.info("the http url is {} , the param is {}", url, body);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 表达式替换
     *
     * @param body   #{[name]}
     * @param params
     */
    protected String expression(String body, Map<String, Object> params) {
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
