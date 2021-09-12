package org.zhongweixian.api.web;

import com.github.pagehelper.PageInfo;
import org.cti.cc.po.AdminAccountInfo;
import org.cti.cc.po.CallLogPo;
import org.cti.cc.po.CommonResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by caoliang on 2021/9/9
 * <p>
 * 话单查询
 */
@RestController
@RequestMapping("call")
public class CallController extends BaseController {


    /**
     * 2.1.1 话单列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @RequestMapping("calllog")
    public CommonResponse<PageInfo<CallLogPo>> calllog(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<>(callLogService.calllogList(params));
    }

}
