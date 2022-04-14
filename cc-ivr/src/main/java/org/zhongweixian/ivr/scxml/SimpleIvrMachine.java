package org.zhongweixian.ivr.scxml;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.scxml2.Evaluator;
import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.SCXMLListener;
import org.apache.commons.scxml2.TriggerEvent;
import org.apache.commons.scxml2.env.SimpleContext;
import org.apache.commons.scxml2.env.SimpleDispatcher;
import org.apache.commons.scxml2.env.SimpleErrorReporter;
import org.apache.commons.scxml2.env.jexl.JexlEvaluator;
import org.apache.commons.scxml2.io.SCXMLReader;
import org.apache.commons.scxml2.model.*;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.IvrWorkflow;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.ivr.action.InitAction;
import org.zhongweixian.ivr.action.PlayAction;
import org.zhongweixian.ivr.service.IvrWorkflowService;
import org.zhongweixian.ivr.util.IvrConstant;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caoliang on 2021/8/8
 */
@Component
public class SimpleIvrMachine {
    private Logger logger = LoggerFactory.getLogger(SimpleIvrMachine.class);

    @Autowired
    private IvrWorkflowService ivrWorkflowService;

    @Value("${minio.endpoint:}")
    private String minioEndpoint;

    private Map<String, SCXML> scxmlMap = new ConcurrentHashMap<>();
    private Map<Long, SCXMLExecutor> executorMap = new ConcurrentHashMap<>();


    public void startRun(CallInfo callInfo, String deviceId, Long ivrId) {
        logger.info("callId:{} deviceId:{} ivrId:{}", callInfo.getCallId(), deviceId, ivrId);
        SCXML scxml = loadXml(ivrId);
        Evaluator evaluator = new JexlEvaluator();
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, new SimpleDispatcher(), new SimpleErrorReporter());


        SimpleContext context = new SimpleContext();
        context.set("callId", callInfo.getCallId());
        context.set("deviceId", deviceId);

        executor.setRootContext(context);
        executorMap.put(callInfo.getCallId(), executor);
        executor.addListener(scxml, new SCXMLListener() {
            @Override
            public void onEntry(EnterableState state) {
                logger.info("onEntry transitionTarget {}", state.getId());

            }

            @Override
            public void onExit(EnterableState state) {
                logger.info("callId:{} exit:{} ", callInfo.getCallId(), state.getId());

            }

            @Override
            public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition, String event) {
                logger.info("from:{}  to:{} transition:{} ", from.getId(), to.getId(), transition.getEvent());
                if ("end".equals(transition.getEvent())) {
                    return;
                }
                fireEvent(callInfo.getCallId(), to.getId());
            }


        });

        try {
            executor.setStateMachine(scxml);
            executor.go();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 创建scxml对象，每个ivr可以共用一个
     *
     * @param ivrId
     * @return
     * @throws Exception
     */
    private SCXML loadXml(Long ivrId) {
        IvrWorkflow ivrWorkflow = ivrWorkflowService.findById(ivrId);
        if (ivrWorkflow == null || ivrWorkflow.getStatus() == 0) {
            return null;
        }
        if (scxmlMap.containsKey(ivrWorkflow.getOssId())) {
            return scxmlMap.get(ivrId);
        }

        try {
            //http://110.40.188.252:9000/cc-ivr/1/1000.xml
            String ivrUrl = minioEndpoint + Constant.SK + Constant.IVR_BUCKET + Constant.SK + ivrWorkflow.getCompanyId() + Constant.SK + ivrWorkflow.getOssId() + Constant.XML;

            //定义好action事件
            List<CustomAction> customActions = this.initActions();
            SCXMLReader.Configuration configuration = new SCXMLReader.Configuration(null, null, customActions);
            SCXML scxml = SCXMLReader.read(new URL(ivrUrl), configuration);
            logger.info("=================:{}", scxml);
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
        SCXMLExecutor executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        TriggerEvent[] evts = new TriggerEvent[]{new TriggerEvent(event, 3)};
        try {
            executor.triggerEvents(evts);
        } catch (ModelException var4) {
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
        SCXMLExecutor executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        try {
            executor.triggerEvent(event);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return executor.detachInstance().getCurrentStatus().isFinal();
    }


    private State getCurrentState(Long callId) {
        SCXMLExecutor scxmlExecutor = executorMap.get(callId);
        if (scxmlExecutor == null) {
            return null;
        }
        Set<?> states = scxmlExecutor.detachInstance().getCurrentStatus().getStates();
        if (CollectionUtils.isEmpty(states)) {
            return null;
        }
        return ((State) states.iterator().next());
    }

    public boolean executeNext(Long callId) {
        State state = getCurrentState(callId);
        if (state != null) {
            List<Transition> transitionList = (List<Transition>) state.getTransitionsList();
            return fireEvent(callId, transitionList.get(0).getEvent());
        }
        return false;
    }


    /**
     * @return
     */
    private List<CustomAction> initActions() {
        String namespaceURI = "http://www.voice9.com";
        List<CustomAction> actions = new ArrayList<>();
        actions.add(new CustomAction(namespaceURI, IvrConstant.INITION, InitAction.class));
        actions.add(new CustomAction(namespaceURI, IvrConstant.INITION, PlayAction.class));
        return actions;
    }
}
