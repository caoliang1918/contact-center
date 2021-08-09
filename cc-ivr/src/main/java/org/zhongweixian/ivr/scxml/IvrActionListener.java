package org.zhongweixian.ivr.scxml;

import org.apache.commons.scxml.env.SimpleSCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caoliang on 2021/8/4
 */
public class IvrActionListener extends SimpleSCXMLListener {
    private Logger logger = LoggerFactory.getLogger(IvrActionListener.class);

    @Override
    public void onEntry(TransitionTarget state) {

    }

    @Override
    public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition) {

    }

    @Override
    public void onExit(TransitionTarget state) {

    }
}
