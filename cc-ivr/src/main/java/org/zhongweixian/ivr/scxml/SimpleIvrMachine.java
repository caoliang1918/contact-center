package org.zhongweixian.ivr.scxml;

import org.apache.commons.scxml2.*;
import org.apache.commons.scxml2.env.SimpleDispatcher;
import org.apache.commons.scxml2.env.SimpleErrorReporter;
import org.apache.commons.scxml2.env.jexl.JexlContext;
import org.apache.commons.scxml2.env.jexl.JexlEvaluator;
import org.apache.commons.scxml2.io.SCXMLReader;
import org.apache.commons.scxml2.model.*;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
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

    private Map<Long, SCXML> scxmlMap = new ConcurrentHashMap<>();
    private Map<Long, SCXMLExecutor> executorMap = new ConcurrentHashMap<>();


    public void runIvr(CallInfo callInfo, Long ivrId) {
        SCXML scxml = scxmlMap.get(ivrId);
        if (scxml == null) {
            scxml = createSCXML(ivrId);
        }

        Evaluator evaluator = new JexlEvaluator();
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, new SimpleDispatcher(), new SimpleErrorReporter());

        executor.addListener(scxml, new SCXMLListener() {
            @Override
            public void onEntry(EnterableState enterableState) {
                logger.info("enterableState:{}", enterableState.getOnEntries());
            }

            @Override
            public void onExit(EnterableState enterableState) {
                logger.info("enterableState:{}", enterableState);
            }

            @Override
            public void onTransition(TransitionTarget transitionTarget, TransitionTarget transitionTarget1, Transition transition, String s) {
                logger.info("{} , {} , {} , {}", transitionTarget, transitionTarget1, transition, s);
            }
        });
        try {
            executor.setStateMachine(scxml);
            executor.setRootContext(new JexlContext());
            executor.go();
            executorMap.put(callInfo.getCallId(), executor);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            Map<String, TransitionTarget> mapTg = scxml.getTargets();

            mapTg.forEach((k, v) -> {
                logger.info("{}:{}", k, v);
            });

            SimpleTransition simpleTransition = scxml.getInitialTransition();

            scxml.setInitial("reset");

            executor.triggerEvent(new TriggerEvent("reset", TriggerEvent.CALL_EVENT));
            executor.triggerEvent(new TriggerEvent("running", TriggerEvent.CALL_EVENT));
        } catch (ModelException e) {
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
    private SCXML createSCXML(Long ivrId) {
        if (scxmlMap.containsKey(ivrId)) {
            return scxmlMap.get(ivrId);
        }
        File file = new File(ivrId + ".xml");
        if (!file.exists()) {
            logger.info("create new {} file", ivrId);
        }
        //定义好action事件
        List<CustomAction> actionList = new ArrayList<>();
        SCXMLReader.Configuration configuration = new SCXMLReader.Configuration(null, null, actionList);
        try {
            SCXML scxml = SCXMLReader.read(new URL("file:" + ivrId + ".xml"), configuration);
            scxmlMap.put(ivrId, scxml);
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
    public boolean triggerEvent(Long callId, String event) {
        SCXMLExecutor executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        TriggerEvent[] evts = {new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT)};
        try {
            executor.triggerEvents(evts);
        } catch (ModelException e) {
            logger.error(e.getMessage(), e);
        }
        return executor.getStatus().isFinal();
    }

    /**
     * @param callId
     * @param event
     * @param payload
     * @return
     */
    public boolean triggerEvent(Long callId, String event, String payload) {
        SCXMLExecutor executor = executorMap.get(callId);
        if (executor == null) {
            return false;
        }
        TriggerEvent triggerEvent = new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, payload);
        try {
            executor.triggerEvent(triggerEvent);
        } catch (ModelException e) {
            logger.error(e.getMessage(), e);
        }
        return executor.getStatus().isFinal();
    }


}
