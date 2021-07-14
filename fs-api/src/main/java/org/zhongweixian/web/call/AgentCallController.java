package org.zhongweixian.web.call;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhongweixian.web.base.BaseController;

/**
 * Created by caoliang on 2020/12/16
 */
@RestController
@RequestMapping("/v1/cti/call")
public class AgentCallController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AgentCallController.class);



    /**
     * 发起呼叫
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("makecall")
    public CommonResponse makecall(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        logger.info("makecall agent:{}", agentInfo.getAgentKey());
        return new CommonResponse("");
    }

}
