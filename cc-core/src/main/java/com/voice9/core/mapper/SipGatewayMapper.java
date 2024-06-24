package com.voice9.core.mapper;


import com.voice9.core.entity.SipGateway;
import com.voice9.core.mapper.base.BaseMapper;

public interface SipGatewayMapper extends BaseMapper<SipGateway> {


    SipGateway selectByUsername(String username);

    /**
     * 定时检测网关下线
     *
     * @param time
     */
    void checkSipGatewayRegister(Long time);
}