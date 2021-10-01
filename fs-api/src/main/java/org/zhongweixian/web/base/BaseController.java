package org.zhongweixian.web.base;

import org.cti.cc.entity.Station;
import org.cti.cc.po.AgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.websocket.handler.WsLogoutHandler;
import org.zhongweixian.cc.websocket.handler.WsNotReadyHandler;
import org.zhongweixian.cc.websocket.handler.WsReadyHandler;

/**
 * Created by caoliang on 2020/11/2
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected CallCdrService callCdrService;

    @Autowired
    protected CacheService cacheService;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected WsReadyHandler readyHandler;

    @Autowired
    protected WsNotReadyHandler notReadyHandler;

    @Autowired
    protected WsLogoutHandler logoutHandler;

    @Autowired
    protected Station station;

    @Autowired
    protected FsListen fsListen;



}
