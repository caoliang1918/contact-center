package org.zhongweixian.api.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.cti.cc.entity.CallLog;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.mapper.CallDetailMapper;
import org.cti.cc.mapper.CallDeviceMapper;
import org.cti.cc.mapper.CallDtmfMapper;
import org.cti.cc.mapper.CallLogMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CallLogPo;
import org.cti.cc.util.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zhongweixian.api.exception.BusinessException;
import org.zhongweixian.api.service.CallLogService;
import org.zhongweixian.api.vo.excel.ExcelInboundCallLogEntity;
import org.zhongweixian.api.vo.excel.ExcelOutboundCallLogEntity;

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

    /**
     * 最大50万下载
     */
    @Value("${calllog.export.limit:500000}")
    private Long calllogLimit;

    @Override
    BaseMapper<CallLog> baseMapper() {
        return callLogMapper;
    }

    @Override
    public void subTable(Long start, Long end, String month) {
        // cc_call_log
        callLogMapper.createNewTable(start, end, month);
        callLogMapper.clearTable(start, end);


        //cc_call_device
        callDeviceMapper.createNewTable(start, end, month);
        callDeviceMapper.clearTable(start, end);

    }

    @Override
    public PageInfo<CallLogPo> calllogList(Map<String, Object> params) {
        Long start = (Long) params.get("start");
        Long end = (Long) params.get("end");
        if (start == null) {
            start = DateTimeUtil.getBeforeDay(0);
            params.put("start" , start);
        }
        if (end == null) {
            end = DateTimeUtil.getBeforeDay(-1);
            params.put("end" , end);
        }
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<CallLogPo> list = callLogMapper.getCallList(params);
        return new PageInfo<>(list);
    }

    @Override
    public void calllogExport(HttpServletResponse response, Map<String, Object> params) throws IOException {
        Long count = callLogMapper.selectCountByMap(params);
        if (count == 0L) {
            new BusinessException(ErrorCode.EXPORT_EMPTY);
        }
        if (count > calllogLimit) {
            new BusinessException(ErrorCode.CALL_LOG_EXPORT_LIMIT);
        }

        String direction = (String) params.getOrDefault("direction", "OUTBOUND");
        params.put("direction", direction);
        List<CallLog> callLogs = callLogMapper.selectListByMap(params);
        // 1:呼入；2:外呼
        String sheetName = null;
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
            sheetName = "外呼详单";
            workbook = ExcelExportUtil.exportExcel(new ExportParams(
                    null, sheetName, ExcelType.XSSF), ExcelOutboundCallLogEntity.class, entityList);
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
            sheetName = "呼入详单";
            workbook = ExcelExportUtil.exportExcel(new ExportParams(
                    null, sheetName, ExcelType.XSSF), ExcelInboundCallLogEntity.class, entityList);
        }

        String filename = URLEncoder.encode(sheetName + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + ".xlsx", "UTF8");
        response.setHeader("content-disposition", "attachment;Filename=" + filename);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }


}
