package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/11/6
 */
public class FsExecuteComplateEvent extends FsBaseEvent {

    @JSONField(name = "Application")
    private String application;

    @JSONField(name = "variable_current_application_data")
    private String applicationData;

    @JSONField(name = "Application-Response")
    private String response;

    @JSONField(name = "variable_SYMWRD_DTMF_RETURN")
    private String dtmf;


    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(String applicationData) {
        this.applicationData = applicationData;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDtmf() {
        return dtmf;
    }

    public void setDtmf(String dtmf) {
        this.dtmf = dtmf;
    }
}
