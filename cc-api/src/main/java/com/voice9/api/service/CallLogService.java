package com.voice9.api.service;

import com.github.pagehelper.PageInfo;
import com.voice9.core.entity.CallDevice;
import com.voice9.core.entity.CallLog;
import com.voice9.core.po.CallLogPo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
public interface CallLogService extends BaseService<CallLog> {


    /**
     * 分页
     *
     * @param params
     * @return
     */
    PageInfo<CallLogPo> calllogList(Map<String, Object> params);

    /**
     * 话单导出
     *
     * @param response
     * @param params
     */
    void calllogExport(HttpServletResponse response, Map<String, Object> params) throws IOException;

    /**
     * 每天清理当天数据
     *
     * @param time
     * @return
     */
    int cleaDayOfData(Long time);

    /**
     * 查询call_device列表
     *
     * @param params
     * @return
     */
    List<CallDevice> callDeviceList(Map<String, Object> params);
}
