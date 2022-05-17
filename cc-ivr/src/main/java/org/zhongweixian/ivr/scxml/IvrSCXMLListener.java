package org.zhongweixian.ivr.scxml;

import org.apache.commons.scxml2.*;
import org.apache.commons.scxml2.env.SimpleDispatcher;
import org.apache.commons.scxml2.env.SimpleErrorReporter;
import org.apache.commons.scxml2.env.jexl.JexlContext;
import org.apache.commons.scxml2.env.jexl.JexlEvaluator;
import org.apache.commons.scxml2.model.*;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.zhongweixian.ivr.cache.CacheService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by caoliang on 2022/5/9
 */
public class IvrSCXMLListener {
    private Logger logger = LoggerFactory.getLogger(IvrSCXMLListener.class);


    private SCXML scxml;
    private SCXMLExecutor executor;
    private CacheService cacheService;
    private RestTemplate restTemplate;
    private RestTemplate httpClient;
    private CallInfo callInfo;


    public IvrSCXMLListener(SCXML scxml, CacheService cacheService, CallInfo callInfo) {
        this.scxml = scxml;
        this.cacheService = cacheService;
        this.callInfo = callInfo;


        Datamodel datamodel = scxml.getDatamodel();
        List<Data> dataList = datamodel.getData();
        if (!CollectionUtils.isEmpty(dataList)) {
            for (Data data : dataList) {
                callInfo.getProcessData().put(data.getId(), data.getSrc());
            }
        }
        Map<String, Object> vars = new HashMap<>();
        vars.put("_callId", callInfo.getCallId());
        vars.put("_deviceId", callInfo.getDeviceList().get(0));
        try {
            this.initialize(scxml, new JexlContext(vars), new JexlEvaluator(), callInfo);
        } catch (ModelException e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * Instantiate and initialize the underlying executor instance.
     *
     * @param stateMachine The state machine
     * @param rootCtx      The root context
     * @param evaluator    The expression evaluator
     */
    private void initialize(final SCXML stateMachine, final Context rootCtx, final Evaluator evaluator, final CallInfo callInfo) throws ModelException {
        executor = new SCXMLExecutor(evaluator, new SimpleDispatcher(), new SimpleErrorReporter());
        executor.setStateMachine(stateMachine);
        executor.setRootContext(rootCtx);
        executor.addListener(stateMachine, new SCXMLListener() {
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
                logger.info("from:{}  to:{} transition:{} event:{}", from, to, transition, event);
                if ("end".equals(transition.getEvent())) {
                    return;
                }
                fireEvent(to.getId());
            }
        });
        try {
            executor.go();
        } catch (ModelException me) {
            logError(me);
        }
    }

    /**
     * Utility method for logging error.
     *
     * @param exception The exception leading to this error condition.
     */
    private void logError(final Exception exception) {
        if (logger.isErrorEnabled()) {
            logger.error(exception.getMessage(), exception);
        }
    }

    /**
     * Fire an event on the SCXML engine.
     *
     * @param event The event name.
     * @return Whether the state machine has reached a &quot;final&quot;
     * configuration.
     */
    public boolean fireEvent(final String event) {
        TriggerEvent[] evts = {new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT)};
        try {
            executor.triggerEvents(evts);
        } catch (ModelException me) {
            logError(me);
        }
        return executor.getCurrentStatus().isFinal();
    }

    /**
     * Fire an event on the SCXML engine.
     *
     * @param event event
     * @return Whether the state machine has reached a &quot;final&quot;
     */
    public boolean fireEvent(final TriggerEvent event) {
        try {
            executor.triggerEvent(event);
        } catch (ModelException me) {
            logError(me);
        }
        return executor.getCurrentStatus().isFinal();
    }

    public void triggerEvents(TriggerEvent[] evts) {

    }
}
