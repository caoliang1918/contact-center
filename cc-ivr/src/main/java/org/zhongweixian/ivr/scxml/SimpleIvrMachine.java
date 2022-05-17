package org.zhongweixian.ivr.scxml;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.scxml2.TriggerEvent;
import org.apache.commons.scxml2.io.SCXMLReader;
import org.apache.commons.scxml2.model.CustomAction;
import org.apache.commons.scxml2.model.Data;
import org.apache.commons.scxml2.model.Datamodel;
import org.apache.commons.scxml2.model.SCXML;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.IvrWorkflow;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.ivr.action.InitAction;
import org.zhongweixian.ivr.action.PlayAction;
import org.zhongweixian.ivr.cache.CacheService;
import org.zhongweixian.ivr.exception.BusinessException;
import org.zhongweixian.ivr.service.IvrWorkflowService;
import org.zhongweixian.ivr.util.IvrConstant;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caoliang on 2021/8/8
 */
@Component
public class SimpleIvrMachine {
    private Logger logger = LoggerFactory.getLogger(SimpleIvrMachine.class);

    @Autowired
    private IvrWorkflowService ivrWorkflowService;

    @Autowired
    private CacheService cacheService;

    @Value("${minio.endpoint:}")
    private String minioEndpoint;

    private Map<String, SCXML> scxmlMap = new ConcurrentHashMap<>();
    private Map<Long, IvrSCXMLListener> executorMap = new ConcurrentHashMap<>();


    public void startRun(CallInfo callInfo, String deviceId, Long ivrId) {
        SCXML scxml = loadXml(callInfo.getCallId(), deviceId, ivrId);
        if (scxml == null) {
            throw new BusinessException(ErrorCode.FILE_NOT_EXIST);
        }
        executorMap.put(callInfo.getCallId(), new IvrSCXMLListener(scxml, cacheService, callInfo));
    }


    /**
     * 创建scxml对象，每个ivr可以共用一个
     *
     * @param ivrId
     * @return
     * @throws Exception
     */
    private SCXML loadXml(Long callId, String deviceId, Long ivrId) {
        IvrWorkflow ivrWorkflow = ivrWorkflowService.findById(ivrId);
        if (ivrWorkflow == null || ivrWorkflow.getStatus() == 0) {
            return null;
        }
        logger.info("callId:{} deviceId:{} ivrId:{} ossId:{}", callId, deviceId, ivrId, ivrWorkflow.getOssId());
        if (scxmlMap.containsKey(ivrWorkflow.getOssId())) {
            return scxmlMap.get(ivrWorkflow.getOssId());
        }

        try {
            //http://110.40.188.252:9000/cc-ivr/1/1000.xml
            String ivrUrl = minioEndpoint + Constant.SK + Constant.IVR_BUCKET + Constant.SK + ivrWorkflow.getCompanyId() + Constant.SK + ivrWorkflow.getOssId();

            URL url = new URL(ivrUrl);
            url.toURI();

            //定义好action事件
            List<CustomAction> customActions = this.initActions();
            SCXMLReader.Configuration configuration = new SCXMLReader.Configuration(null, null, customActions);
            SCXML scxml = SCXMLReader.read(new URL(ivrUrl), configuration);

            Datamodel datamodel = scxml.getDatamodel();
            if (datamodel == null) {
                datamodel = new Datamodel();
            } else {
                List<Data> dataList = datamodel.getData();
                if (!CollectionUtils.isEmpty(dataList)) {
                    for (Data data : dataList) {
                        String expr = data.getExpr();
                        data.setExpr(null);
                        data.setSrc(expr);
                    }
                }
            }
            scxml.setDatamodel(datamodel);
            scxmlMap.put(ivrWorkflow.getOssId(), scxml);
            return scxml;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 执行事件
     *
     * @param callId
     * @param event
     * @return
     */
    public boolean fireEvent(Long callId, String event) {
        if (StringUtils.isBlank(event)) {
            return false;
        }
        IvrSCXMLListener executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        TriggerEvent[] evts = new TriggerEvent[]{new TriggerEvent(event, 3)};
        try {
            executor.triggerEvents(evts);
        } catch (Exception var4) {
            logger.warn(var4.getMessage(), var4);
        }
        return false;
    }

    /**
     * @param callId
     * @param event
     * @return
     */
    public boolean fireEvent(Long callId, final TriggerEvent event) {
        IvrSCXMLListener executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        try {
            return executor.fireEvent(event);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }


    /**
     * @return
     */
    private List<CustomAction> initActions() {
        String namespaceURI = "https://www.voice9.com";
        List<CustomAction> actions = new ArrayList<>();
        actions.add(new CustomAction(namespaceURI, IvrConstant.INITION, InitAction.class));
        actions.add(new CustomAction(namespaceURI, IvrConstant.PLAY, PlayAction.class));
        return actions;
    }
}
