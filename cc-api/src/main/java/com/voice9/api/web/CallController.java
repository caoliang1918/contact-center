package com.voice9.api.web;

import com.github.pagehelper.PageInfo;
import com.voice9.core.po.AdminAccountInfo;
import com.voice9.core.po.CallLogPo;
import com.voice9.core.po.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * 话单导出
     *
     * @param response
     * @param adminAccountInfo
     * @param query
     * @throws IOException
     */
    @GetMapping("calllog/export")
    public void calllogExport(HttpServletResponse response, @ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, null, query);
        try {
            callLogService.calllogExport(response, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
