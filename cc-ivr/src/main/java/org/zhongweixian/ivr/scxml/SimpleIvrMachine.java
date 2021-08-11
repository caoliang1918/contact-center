package org.zhongweixian.ivr.scxml;

import org.apache.commons.scxml2.Context;
import org.apache.commons.scxml2.Evaluator;
import org.apache.commons.scxml2.SCXMLExecutor;
import org.apache.commons.scxml2.SCXMLListener;
import org.apache.commons.scxml2.env.SimpleErrorReporter;
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
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caoliang on 2021/8/8
 */
@Component
public class SimpleIvrMachine {
    private Logger logger = LoggerFactory.getLogger(SimpleIvrMachine.class);

    private Map<Long, SCXML> scxmlMap = new ConcurrentHashMap<>();


    public void runIvr(CallInfo callInfo, Long ivrId) {
        SCXML scxml = scxmlMap.get(ivrId);
        if (scxml == null) {
            try {
                scxml = createSCXML(ivrId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }


    private SCXML createSCXML(Long ivrId) throws Exception {
        File file = new File(ivrId + ".xml");
        logger.info(file.getPath(), file.exists());

        List<CustomAction> actionList = new ArrayList<>();

        Evaluator evaluator = new JexlEvaluator();
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, null, new SimpleErrorReporter());
        SCXML scxml = SCXMLReader.read(new URL("file:" + ivrId + ".xml"));

        executor.addListener(scxml, new SCXMLListener() {
            @Override
            public void onEntry(EnterableState enterableState) {
                logger.info("{}", enterableState);
            }

            @Override
            public void onExit(EnterableState enterableState) {
                logger.info("{}", enterableState);
            }

            @Override
            public void onTransition(TransitionTarget transitionTarget, TransitionTarget transitionTarget1, Transition transition, String s) {
                logger.info("{} , {} , {} , {}", transitionTarget, transitionTarget1, transition, s);
            }
        });
        executor.setStateMachine(scxml);
        Context rootContext = evaluator.newContext(null);
        executor.setRootContext(rootContext);
        executor.go();
        return scxml;
    }


}
