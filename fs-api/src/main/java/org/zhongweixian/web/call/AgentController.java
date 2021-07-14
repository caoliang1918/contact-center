package org.zhongweixian.web.call;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.vo.AgentVo;
import org.cti.cc.vo.GroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.websocket.event.WsLoginEvnet;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;
import org.zhongweixian.web.base.BaseController;

/**
 * Created by caoliang on 2020/12/17
 */
@RestController
@RequestMapping("/v1/cti/agent")
public class AgentController extends BaseController {


    /**
     * 查看坐席信息
     *
     * @param agentInfo
     * @return
     */
    @GetMapping("agentInfo")
    public CommonResponse<AgentVo> agentInfo(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        AgentVo agentVo = new AgentVo();
        BeanUtils.copyProperties(agentInfo, agentVo);
        return new CommonResponse<AgentVo>(agentVo);
    }

    /**
     * 坐席上班
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("ready")
    public CommonResponse ready(@ModelAttribute("agentInfo") AgentInfo agentInfo) {

        return new CommonResponse<>();
    }

    /**
     * 坐席上班
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("notReady")
    public CommonResponse notReady(@ModelAttribute("agentInfo") AgentInfo agentInfo) {

        return new CommonResponse<>();
    }

    /**
     * 查看技能组
     *
     * @param groupId
     * @return
     */
    @GetMapping("group/{groupId}")
    public CommonResponse groupReadyAgent(@PathVariable Long groupId, @ModelAttribute("agentInfo") AgentInfo agentInfo) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !agentInfo.getGroupIds().contains(groupInfo.getId())) {
            return new CommonResponse();
        }
        GroupVo groupVo = new GroupVo();
        BeanUtils.copyProperties(groupInfo, groupVo);
        return new CommonResponse(groupVo);
    }

    /**
     * 技能组空闲坐席
     *
     * @param groupId
     * @param agentInfo
     * @return
     */
    @GetMapping("group/{groupId}/ready")
    public CommonResponse readyAgent(@PathVariable Long groupId, @ModelAttribute("agentInfo") AgentInfo agentInfo) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !agentInfo.getGroupIds().contains(groupInfo.getId())) {
            return new CommonResponse();
        }
        return new CommonResponse(groupInfo.getOnlineAgents());
    }


}
