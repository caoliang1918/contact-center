package org.zhongweixian.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;

import java.util.List;

/**
 * Created by caoliang on 2022/6/15
 */
public class DetectedSpeechEvent extends FsBaseEvent {


    @JSONField(name = "Speech-Type")
    private String type;


    @JSONField(name = "DETECTED_SPEECH")
    private List<String> speech;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSpeech() {
        return speech;
    }

    public void setSpeech(List<String> speech) {
        this.speech = speech;
    }

    @Override
    public String toString() {
        return "DetectedSpeechEvent{" +
                "type='" + type + '\'' +
                ", speech=" + speech +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
