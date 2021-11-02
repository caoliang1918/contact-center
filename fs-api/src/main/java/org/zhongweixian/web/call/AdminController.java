package org.zhongweixian.web.call;

import org.cti.cc.entity.AdminAccount;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.exception.BusinessException;
import org.zhongweixian.web.base.BaseController;

import java.util.List;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 呼叫话单查询等
 * 需要企业账号访问
 */
@RestController
@RequestMapping("v1/cti/admin")
public class AdminController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ModelAttribute("adminAccount")
    public AdminAccount adminAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (AdminAccount) authentication.getPrincipal();
    }

    /**
     * 查看话单
     *
     * @param adminAccount
     * @param callId
     * @return
     */
    @GetMapping("call")
    public CommonResponse<CallLogPo> call(@ModelAttribute("adminAccount") AdminAccount adminAccount, @RequestParam Long callId) {
        CallLogPo callLogPo = callCdrService.getCall(adminAccount.getCompanyId(), callId);
        return new CommonResponse<CallLogPo>(callLogPo);
    }


    /**
     * 查看通话中话单详情
     *
     * @param adminAccount
     * @param callId
     * @return
     */
    @GetMapping("calling")
    public CommonResponse<CallInfo> calling(@ModelAttribute("adminAccount") AdminAccount adminAccount, @RequestParam Long callId) {
        CallInfo callInfo = cacheService.getCallInfo(callId);
        return new CommonResponse<CallInfo>(callInfo);
    }

    /**
     * 3.1.6 查询坐席状态
     *
     * @param adminAccount
     * @param agentKey
     * @return
     */
    @GetMapping("agent/{agentKey}")
    public CommonResponse<AgentInfo> getAgentState(@ModelAttribute("adminAccount") AdminAccount adminAccount, @PathVariable String agentKey) {
        if (agentKey.split("@").length != 2) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR, "agentKey");
        }
        if (!adminAccount.getCode().equals(agentKey.split("@")[1])) {
            throw new BusinessException(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(agentKey);
        if (agentInfo == null) {
            agentInfo = agentService.getAgentInfo(agentKey);
        }
        if (agentInfo == null || agentInfo.getStatus() != 1) {
            logger.warn("agentKey:{} is not exist", agentKey);
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        if (agentInfo.getAgentState() == null) {
            agentInfo.setAgentState(AgentState.LOGOUT);
        }
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        AgentInfo agentInfo1 = new AgentInfo();
        BeanUtils.copyProperties(agentInfo, agentInfo1);
        agentInfo1.setPasswd(null);
        return new CommonResponse<AgentInfo>(agentInfo1);
    }

    /**
     * 3.1.7 技能组空闲坐席
     *
     * @param adminAccount
     * @param groupId
     * @return
     */
    @GetMapping("group/{groupId}/ready")
    public CommonResponse<GroupAgentFree> readyAgent(@ModelAttribute("adminAccount") AdminAccount adminAccount, @PathVariable Long groupId) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !groupInfo.getCompanyId().equals(adminAccount.getCompanyId())) {
            throw new BusinessException(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        GroupAgentFree groupAgentFree = new GroupAgentFree();
        groupAgentFree.setId(groupInfo.getId());
        groupAgentFree.setGroupType(groupInfo.getGroupType());
        groupAgentFree.setName(groupInfo.getName());
        List<String> agentQueues = groupHandler.getFreeAgents(groupInfo.getId());
        groupAgentFree.setFreeAgents(agentQueues);
        return new CommonResponse<GroupAgentFree>(groupAgentFree);
    }

}
