package org.zhongweixian.web.call;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.entity.MakeCallVo;
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


    /**
     * 发起呼叫
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("makecall")
    public CommonResponse makecall(@ModelAttribute("agentInfo") AgentInfo agentInfo, @RequestBody @Validated MakeCallVo makeCallVo) {
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState() == AgentState.TALKING) {
            return new CommonResponse(ErrorCode.AGENT_CALLING);
        }
        if (makeCallVo.getCallType() == null) {
            return new CommonResponse(ErrorCode.CALLTYPE_ERROR);
        }


        logger.info("makecall agent:{}", agentInfo.getAgentKey());
        return new CommonResponse("");
    }

}
