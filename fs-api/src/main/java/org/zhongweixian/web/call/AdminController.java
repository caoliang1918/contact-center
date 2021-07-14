package org.zhongweixian.web.call;

import org.cti.cc.entity.AdminAccount;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.CallLogPo;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.service.CallCdrService;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 呼叫话单查询等
 * 需要企业账号访问
 */
@RestController
@RequestMapping("v1/cti/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CallCdrService callCdrService;

    @Autowired
    private CacheService cacheService;

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

}
