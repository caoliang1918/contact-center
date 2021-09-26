package org.zhongweixian.ivr.scxml;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.scxml.Evaluator;
import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.SimpleDispatcher;
import org.apache.commons.scxml.env.SimpleErrorHandler;
import org.apache.commons.scxml.env.SimpleErrorReporter;
import org.apache.commons.scxml.env.jexl.JexlContext;
import org.apache.commons.scxml.env.jexl.JexlEvaluator;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.*;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
        executor.setStateMachine(scxml);
        executor.setSuperStep(true);
        executor.setRootContext(new JexlContext());
        executorMap.put(callInfo.getCallId(), executor);
        executor.addListener(scxml, new SCXMLListener() {
            @Override
            public void onEntry(TransitionTarget transitionTarget) {
                State state = getCurrentState(callInfo.getCallId());
                logger.info("onEntry transitionTarget {},{}", transitionTarget.getId(), state);
                if (state != null) {
                    List<Transition> transitionList = (List<Transition>) transitionTarget.getTransitionsList();
                    if (!CollectionUtils.isEmpty(transitionList)) {
                        logger.info("state id: {}, event:{},  target:{} , isFinal:{} ", state.getId(), transitionList.get(0).getEvent(), transitionList.get(0).getTargets(), state.isFinal());
                        fireEvent(callInfo.getCallId(), transitionList.get(0).getEvent());
                    }
                }
            }

            @Override
            public void onExit(TransitionTarget transitionTarget) {
                logger.info("onExit transitionTarget {}", transitionTarget.getId());
            }

            @Override
            public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition) {
                logger.info("{} , {} {} ", from.getId(), to.getId(), transition.getTargets());
            }
        });

        try {
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
    private SCXML createSCXML(Long ivrId) {
        if (scxmlMap.containsKey(ivrId)) {
            return scxmlMap.get(ivrId);
        }
        File file = new File(ivrId + ".xml");
        if (!file.exists()) {
            logger.info("create new {} file", ivrId);
        }
        //定义好action事件
        List<CustomAction> actionList = initActions();
        try {
            SCXML scxml = SCXMLParser.parse(new URL("file:" + ivrId + ".xml"), new SimpleErrorHandler(), actionList);
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
    public boolean fireEvent(Long callId, String event) {
        if (StringUtils.isBlank(event)) {
            return false;
        }
        TriggerEvent[] evts = new TriggerEvent[]{new TriggerEvent(event, 3, (Object) null)};
        try {
            this.executorMap.get(callId).triggerEvents(evts);
        } catch (ModelException var4) {
        }
        return this.executorMap.get(callId).getCurrentStatus().isFinal();
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
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return executor.getCurrentStatus().isFinal();
    }

    private State getCurrentState(Long callId) {
        Set<?> states = executorMap.get(callId).getCurrentStatus().getStates();
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


    private List<CustomAction> initActions(){
        List<CustomAction> actions = new ArrayList<>();

        return actions;
    }
}
