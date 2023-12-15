package com.voice9.api.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.voice9.core.entity.Agent;

import java.util.Date;

/**
 * Created by caoliang on 2021/6/22
 */
public class ExcelAgentEntity extends Agent {

    /**
     * PK
     */
    @Excel(name = "坐席id", width = 10)
    private Long id;

    /**
     *
     */
    @Excel(name = "创建时间", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date cts;


    /**
     * 企业ID
     */
    @Excel(name = "企业id", width = 10)
    private Long companyId;

    /**
     * 坐席账户
     */
    @Excel(name = "坐席账号", width = 15)
    private String agentKey;

    /**
     * 坐席名称
     */
    @Excel(name = "坐席名称", width = 15)
    private String agentName;

    /**
     * 坐席分机号
     */
    @Excel(name = "分机号", width = 15)
    private String agentCode;

    /**
     * 座席类型：1:普通座席；2：班长
     */
    @Excel(name = "坐席类型", width = 15, replace = {"普通坐席_1", "班长_2"})
    private Integer agentType;

    /**
     * 绑定的电话号码
     */
    @Excel(name = "坐席电话号码", width = 20)
    private String sipPhone;

    /**
     * 是否录音 0 no 1 yes
     */
    @Excel(name = "是否录音", width = 10, replace = {"是_1", "否_0"})
    private Integer record;

    /**
     * 座席主要技能组  不能为空 必填项
     */
    @Excel(name = "技能组id", width = 10)
    private Long groupId;

    /**
     * 话后自动空闲间隔时长
     */
    @Excel(name = "话后空闲时长", width = 20)
    private Integer afterInterval;

    /**
     * 主叫显号
     */
    @Excel(name = "主叫显号", width = 20)
    private String display;

    /**
     * 振铃时长
     */
    @Excel(name = "振铃时长", width = 10)
    private Integer ringTime;


}
