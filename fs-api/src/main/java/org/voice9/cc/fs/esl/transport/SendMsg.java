/*
 * Copyright 2010 david varnes.
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.voice9.cc.fs.esl.transport;

import java.util.ArrayList;
import java.util.List;

public class SendMsg {
    private final List<String> msgLines = new ArrayList<>();
    private final boolean hasUuid;

    /**
     * Constructor for use with outbound socket client only.  This client mode does not need a call
     * UUID for context.
     */
    public SendMsg() {
        msgLines.add("sendmsg");
        hasUuid = false;
    }

    /**
     * Constructor for use with the inbound client.
     *
     * @param uuid of the call to send message to (it should be in 'park' to be operated on).
     */
    public SendMsg(String uuid) {
        msgLines.add("sendmsg " + uuid);
        hasUuid = true;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   call-command: command
     * </pre>
     *
     * @param command the string command [ execute | hangup ]
     */
    public SendMsg addCallCommand(String command) {
        msgLines.add("call-command: " + command);
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   execute-app-name: appName
     * </pre>
     *
     * @param appName the string app name to execute
     */
    public SendMsg addExecuteAppName(String appName) {
        msgLines.add("execute-app-name: " + appName);
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   execute-app-arg: arg
     * </pre>
     *
     * @param arg the string arg
     */
    public SendMsg addExecuteAppArg(String arg) {
        msgLines.add("execute-app-arg: " + arg);
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   loops: count
     * </pre>
     *
     * @param count the int number of times to loop
     */
    public SendMsg addLoops(int count) {
        msgLines.add("loops: " + count);
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   hangup-cause: cause
     * </pre>
     *
     * @param cause the string cause
     */
    public SendMsg addHangupCause(String cause) {
        msgLines.add("hangup-cause: " + cause);
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *   nomedia-uid: value
     * </pre>
     *
     * @param value the string value part of the line
     */
    public SendMsg addNomediaUuid(String value) {
        msgLines.add("nomedia-uuid: " + value);
        return this;
    }

    public SendMsg addAsync() {
        msgLines.add("async: true");
        return this;
    }

    /**
     * Adds the following line to the message:
     * <pre>
     *    event-lock: true
     *  </pre>
     */
    public SendMsg addEventLock() {
        msgLines.add("event-lock: true");
        return this;
    }

    /**
     * A generic method to add a message line. The constructed line in the sent message will be in the
     * form:
     * <pre>
     *   name: value
     * </pre>
     *
     * @param name  part of line
     * @param value part of line
     */
    public SendMsg addGenericLine(String name, String value) {
        msgLines.add(name + ": " + value);
        return this;
    }

    /**
     * The list of strings that make up the message to send to FreeSWITCH.
     *
     * @return list of strings, as they were added to this message.
     */
    public List<String> getMsgLines() {
        return msgLines;
    }

    /**
     * Indicate if message was constructed with a UUID.
     *
     * @return true if constructed with a UUID.
     */
    public boolean hasUuid() {
        return hasUuid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SendMsg: ");
        if (msgLines.size() > 1) {
            sb.append(msgLines.get(1));
        } else if (msgLines.size() > 0) {
            sb.append(0);
        }

        return sb.toString();
    }


}
