package com.voice9.api.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voice9.api.vo.excel.ExcelInboundCallLogEntity;
import com.voice9.api.vo.excel.ExcelOutboundCallLogEntity;
import com.voice9.core.mapper.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.CallDevice;
import com.voice9.core.entity.CallLog;
import com.voice9.core.enums.Direction;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.CallLogPo;
import com.voice9.core.util.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.voice9.api.exception.BusinessException;
import com.voice9.api.service.CallLogService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
@Service
public class CallLogServiceImpl extends BaseServiceImpl<CallLog> implements CallLogService {

    @Autowired
    private CallDeviceMapper callDeviceMapper;

    @Autowired
    private CallLogMapper callLogMapper;

    @Autowired
    private CallDetailMapper callDetailMapper;

    @Autowired
    private CallDtmfMapper callDtmfMapper;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    /**
     * 最大10万下载
     */
    @Value("${calllog.export.limit:100000}")
    private Long calllogLimit;

    @Override
    BaseMapper<CallLog> baseMapper() {
        return callLogMapper;
    }

    @Override
    public PageInfo<CallLogPo> calllogList(Map<String, Object> params) {
        Long start = (Long) params.get("start");
        Long end = (Long) params.get("end");
        if (start == null) {
            start = DateTimeUtil.getBeforeDay(0);
            params.put("start", start);
        }
        if (end == null) {
            end = DateTimeUtil.getBeforeDay(-1);
            params.put("end", end);
        }
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<CallLogPo> list = callLogMapper.getCallList(params);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(callLogPo -> {
                callLogPo.setOssServer(ossServer);
            });
        }
        return new PageInfo<>(list);
    }

    @Override
    public void calllogExport(HttpServletResponse response, Map<String, Object> params) throws IOException {
        Integer count = callLogMapper.selectCountByMap(params);
        if (count == 0L) {
            new BusinessException(ErrorCode.EXPORT_EMPTY);
        }
        if (count > calllogLimit) {
            new BusinessException(ErrorCode.CALL_LOG_EXPORT_LIMIT);
        }

        String direction = (String) params.getOrDefault("direction", "OUTBOUND");
        params.put("direction", direction);
        List<CallLog> callLogs = callLogMapper.selectListByMap(params);
        /**
         * 呼入和外呼分开，如果不传默认是外呼
         */
        Workbook workbook = null;
        if (Direction.OUTBOUND.name().equals(direction)) {
            List<ExcelOutboundCallLogEntity> entityList = new ArrayList<>();
            ExcelOutboundCallLogEntity entity = null;
            for (CallLog callLog : callLogs) {
                entity = new ExcelOutboundCallLogEntity();
                BeanUtils.copyProperties(callLog, entity);
                entity.setCallTime(DateTimeUtil.format(callLog.getCallTime()));
                entity.setAnswerTime(DateTimeUtil.format(callLog.getAnswerTime()));
                entity.setEndTime(DateTimeUtil.format(callLog.getEndTime()));
                entity.setRecordTime(DateTimeUtil.format(callLog.getRecordTime()));
                entityList.add(entity);
            }
            workbook = ExcelExportUtil.exportExcel(new ExportParams(null, direction, ExcelType.XSSF), ExcelOutboundCallLogEntity.class, entityList);
        } else if (Direction.INBOUND.name().equals(direction)) {
            List<ExcelInboundCallLogEntity> entityList = new ArrayList<>();
            ExcelInboundCallLogEntity entity = null;
            for (CallLog callLog : callLogs) {
                entity = new ExcelInboundCallLogEntity();
                BeanUtils.copyProperties(callLog, entity);
                entity.setCallTime(DateTimeUtil.format(callLog.getCallTime()));
                entity.setAnswerTime(DateTimeUtil.format(callLog.getAnswerTime()));
                entity.setEndTime(DateTimeUtil.format(callLog.getEndTime()));
                entity.setRecordTime(DateTimeUtil.format(callLog.getRecordTime()));
                entity.setFristQueueTime(DateTimeUtil.format(callLog.getFristQueueTime()));
                entity.setQueueStartTime(DateTimeUtil.format(callLog.getQueueStartTime()));
                entity.setQueueEndTime(DateTimeUtil.format(callLog.getQueueEndTime()));
                entityList.add(entity);
            }
            workbook = ExcelExportUtil.exportExcel(new ExportParams(null, direction, ExcelType.XSSF), ExcelInboundCallLogEntity.class, entityList);
        }
        String filename = URLEncoder.encode(direction + Constant.UNDER_LINE + params.getOrDefault("companyId", 0) + Constant.UNDER_LINE + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + ".xlsx", Constant.UTF_8);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;Filename=" + filename);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    @Override
    public int cleaDayOfData(Long end) {
        int result = 0;
        // cc_call_log
        result += callLogMapper.clearTable(end);

        //cc_call_device
        result += callDeviceMapper.clearTable(end);

        //cc_call_detail
        result += callDetailMapper.clearTable(end);

        //cc_call_dtmf
        result += callDtmfMapper.clearTable(end);

        //cc_agent_state_log
        result += agentStateLogMapper.clearTable(end / 1000L);

        return result;
    }

    @Override
    public List<CallDevice> callDeviceList(Map<String, Object> params) {
        return callDeviceMapper.selectListByMap(params);
    }


}
