package org.voice9.web.call;

import com.voice9.core.entity.AdminUser;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.voice9.web.base.BaseController;
import org.voice9.cc.exception.BusinessException;

import java.util.List;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 呼叫话单查询等
 * 需要企业账号访问
 */
@RestController
@RequestMapping("/cti/admin")
public class AdminController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ModelAttribute("adminAccount")
    public AdminUser adminAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (AdminUser) authentication.getPrincipal();
    }

    /**
     * 6.1.1 查看话单
     *
     * @param adminUser
     * @param callId
     * @return
     */
    @GetMapping("call")
    public CommonResponse<CallLogPo> call(@ModelAttribute("adminAccount") AdminUser adminUser, @RequestParam Long callId) {
        CallLogPo callLogPo = callCdrService.getCall(adminUser.getCompanyId(), callId);
        return new CommonResponse<CallLogPo>(callLogPo);
    }


    /**
     * 6.1.2 查看通话中话单详情
     *
     * @param adminUser
     * @param callId
     * @return
     */
    @GetMapping("calling")
    public CommonResponse<CallInfo> calling(@ModelAttribute("adminAccount") AdminUser adminUser, @RequestParam Long callId) {
        CallInfo callInfo = cacheService.getCallInfo(callId);
        return new CommonResponse<CallInfo>(callInfo);
    }

    /**
     * 6.2.1 查询坐席状态
     *
     * @param adminUser
     * @param agentKey
     * @return
     */
    @GetMapping("agent/{agentKey}")
    public CommonResponse<AgentInfo> getAgentState(@ModelAttribute("adminAccount") AdminUser adminUser, @PathVariable String agentKey) {
        if (agentKey.split("@").length != 2) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR, "agentKey");
        }
        if (!adminUser.getCode().equals(agentKey.split("@")[1])) {
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
     * 6.2.2 技能组空闲坐席
     *
     * @param adminUser
     * @param groupId
     * @return
     */
    @GetMapping("group/{groupId}/ready")
    public CommonResponse<GroupAgentFree> readyAgent(@ModelAttribute("adminAccount") AdminUser adminUser, @PathVariable Long groupId) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !groupInfo.getCompanyId().equals(adminUser.getCompanyId())) {
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
