package org.zhongweixian.web.call;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.entity.MakeCallVo;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.web.base.BaseController;

/**
 * Created by caoliang on 2020/12/16
 * <p>
 * rest cti for agent
 */
@RestController
@RequestMapping("/v1/cti/call")
public class AgentCallController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AgentCallController.class);

    @Autowired
    private CallCdrService callCdrService;

    /**
     * 发起呼叫
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("makecall")
    public CommonResponse makecall(@ModelAttribute("agentInfo") AgentInfo agentInfo, @RequestBody @Validated MakeCallVo makeCallVo) {
        if (agentInfo.getAgentState() != null) {
            if (agentInfo.getCallId() != null || agentInfo.getAgentState() == AgentState.TALKING) {
                return new CommonResponse(ErrorCode.AGENT_CALLING);
            }
        }
        if (makeCallVo.getCallType() == null) {
            return new CommonResponse(ErrorCode.CALLTYPE_ERROR);
        }
        callCdrService.makeCall(makeCallVo, agentInfo);
        logger.info("agent:{} makecall:{}", agentInfo.getAgentKey(), makeCallVo.toString());
        return new CommonResponse("");
    }

}
