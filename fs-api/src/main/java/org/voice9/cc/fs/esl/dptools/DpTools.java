package org.voice9.cc.fs.esl.dptools;


import org.voice9.cc.fs.esl.internal.IModEslApi;
import org.voice9.cc.fs.esl.transport.SendMsg;

public class DpTools {

    private final IModEslApi api;

    public DpTools(IModEslApi api) {
        this.api = api;
    }

    public DpTools answer() {
        api.sendMessage(new SendMsg().addCallCommand("answer"));
        return this;
    }

}
