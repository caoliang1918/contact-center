package org.voice9.cc.fs.esl.dptools;

import org.voice9.cc.fs.esl.internal.IModEslApi;
import org.voice9.cc.fs.esl.transport.CommandResponse;
import org.voice9.cc.fs.esl.transport.SendMsg;
import org.voice9.cc.fs.esl.transport.message.EslMessage;

import java.util.UUID;


public class Execute {

    IModEslApi api;
    String uuid;

    public Execute(IModEslApi api, String uuid) {
        this.api = api;
        this.uuid = uuid;
    }

    /**
     * Sends an info packet with a sipfrag. If the phone supports it will show
     * message on the display.
     *
     * @param message
     *            to display
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_Send_Display">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_Send_Display
     *     </a>
     */
    public void sendDiplay(String message) throws ExecuteException {
        sendExeMesg("send_display", message);
    }

    /**
     * Answers an incoming call or session.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_answer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_answer
     *     </a>
     */
    public void answer() throws ExecuteException {
        sendExeMesg("answer");

    }

    /**
     * Make an attended transfer.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_att_xfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_att_xfer
     *     </a>
     * @param channelUrl
     *            ex: sofia/default/${attxfer_callthis}
     */
    public void attAnswer(String channelUrl) throws ExecuteException {
        sendExeMesg("att_xfer", channelUrl);
    }

    /**
     * 
     * @param key
     *            the button you want to respond to after the * button is
     *            pressed. If you wanted to respond to *1, you would put 1 in
     *            place of KEY. You are limited to a single digit.
     * @param leg
     *            which call leg(s) to listen on. Acceptable parameters are a, b
     *            or ab.
     * @param flags
     *            modifies the behavior. The following flags are available: a -
     *            Respond on A leg, b - Respond on B leg, o - Respond on
     *            opposite leg, s - Respond on same leg, i - Execute inline, 1 -
     *            Unbind this meta_app after it is used one time
     * @param application
     *            is which application you want to execute.
     * @param params
     *            are the arguments you want or need to provide to the
     *            APPLICATION.
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bind_meta_app">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bind_meta_app
     *     </a>
     */
    public void bindMetaApp(String key, String leg, String flags,
            String application, String params) throws ExecuteException {
        sendExeMesg("bind_meta_app", key + " " + leg + flags + " " + application + "::" + params);
    }

    /**
     * Cancels currently running application on the given UUID. Dialplan
     * execution proceeds to the next application. Optionally clears all
     * unprocessed events (queued applications) on the channel.
     * 
     * @param all
     *            clear all unprocessed events (queued applications) on the
     *            channel, otherwise just the current application.
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_break">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_break
     *     </a>
     */
    public void breakChannel(boolean all) throws ExecuteException {
        sendExeMesg("break", all ? "all" : "");
    }

    /**
     * Provides the ability to bridge two endpoints. Generally used to route an
     * incoming call to one or more endpoints. Multiple endpoints can be dialed
     * simultaneously or sequentially using the comma and pipe delimiters,
     * respectively.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bridge">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bridge
     *     </a>
     */
    public void bridge(String endpoint) throws ExecuteException {
        sendExeMesg("bridge", endpoint);
    }

    /**
     * Export a channel variable across a bridge. This application differs from
     * export in that it works with *any* kind of bridge, not just a bridge
     * called from the dialplan. For example, bridge_export will export its
     * variables if the leg is uuid_transfer'd whereas export will not
     * 
     * @param key
     *            channel variable name
     * @param value
     *            channel variable value
     * @param local
     *            to only export to the B leg false, otherwise true
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bridge_export">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_bridge_export
     *     </a>
     */
    public void bridgeExport(String key, String value, boolean local)
            throws ExecuteException {
        StringBuilder sb = new StringBuilder();
        if(!local) {
            sb.append("nolocal:");
        }
        sb.append(key);
        sb.append("=");
        sb.append(value);
        sendExeMesg("bridge_export",sb.toString());
    }

    /**
     * Send a text message to a IM client.
     * 
     * @param proto
     *            ex: sip
     * @param from
     *            ex: 1000@127.0.0.1
     * @param to
     *            ex: 1001@127.0.0.1
     * @param message
     *            ex: Hello chat from freeswitch
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_chat">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_chat
     *     </a>
     */
    public void chat(String proto, String from, String to, String message)
            throws ExecuteException {
        sendExeMesg("chat", proto + "|" + from + "|" + to + "|" + message);
    }

    /**
     * cng plc is just an app that says to perform plc on any lost packets and
     * execute on originate. It is like execute on answer, etc. but only for
     * outbound calls during originate.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_cng_plc">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_cng_plc
     *     </a>
     */
    public void cngPlc() throws ExecuteException {
        sendExeMesg("cng_plc");
    }

    /**
     * Start or join a conference
     */
    public void conference(String name) throws ExecuteException {
       conference(name,null,null,null);
    }
    
    /**
     * Start or join a conference
     */
    public void conference(String name, String profile) throws ExecuteException {
        conference(name,profile,null,null);
    }
    
    /**
     * Start or join a conference
     */
    public void conference(String name, String profile, String pin) throws ExecuteException {
        conference(name,profile,pin,null);
    }
    
    /**
     * Start or join a conference
     */
    public void conference(String name, String profile, String pin, String flags) throws ExecuteException {
        StringBuilder sb = new StringBuilder(name);
        if(nn(profile)) {
            sb.append("@").append(profile);
        }
        if(nn(pin)) {
            sb.append("+").append(pin);
        }
        if(nn(flags)) {
            sb.append("+flags{").append(flags).append("}");
        }
        sendExeMesg("conference", sb.toString());
    }
    
    /**
     * Deflect sends a Refer to the client. The deflect application allows
     * FreeSWITCH to be removed from the list of connection hops and tell the
     * originator to reroute the call. When using the deflect application,
     * FreeSWITCH first hangs up the channel and then send a REFER message and a
     * new INVITE message to the originator. The originator, which could be a
     * gateway or sip proxy, should read the INVITE and reroute the call
     * accordingly.
     * 
     * @param endpoint
     *            SIP URI to contact (with or without "sip:")
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_deflect">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_deflect
     *     </a>
     */
    public void deflect(String endpoint) throws ExecuteException {
        sendExeMesg("deflect", endpoint);
    }

    /**
     * Places the calling channel in delayed loopback mode. It simply returns
     * everything sent, including voice, DTMF, etc but with the specified delay
     * [ms]. It is generally useful for checking if RTP audio path works both
     * ways. Normal echo app can fail when tested on speaker phone.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_delay_echo">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_delay_echo
     *     </a>
     */
    public void delayEcho(long ms) throws ExecuteException {
        sendExeMesg("delay_echo", ms + "");
    }

    /**
     * Implements speech recognition.
     * 
     * @param args
     *            <mod_name> <gram_name> <gram_path> [<addr>] grammar
     *            <gram_name> [<path>] grammaron <gram_name> grammaroff
     *            <gram_name> grammarsalloff nogrammar <gram_name> param <name>
     *            <value> pause resume start_input_timers stop
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_detect_speech">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_detect_speech
     *     </a>
     */
    public void detectSpeech(String args) throws ExecuteException {
        sendExeMesg("detect_speech", args);
    }

    /**
     * Displace file. Plays a file or stream to a channel.
     * 
     * @param path
     *            any sound format FreeSWITCH supports, wav, local_steam, shout
     *            etc.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session
     *     </a>
     */
    public void displaceSession(String path) throws ExecuteException {
        displaceSession(path, null, 0);
    }
    
    /**
     * Displace file. Plays a file or stream to a channel.
     * 
     * @param path
     *            any sound format FreeSWITCH supports, wav, local_steam, shout
     *            etc.
     * @param flags
     *            flags to stream, null if none
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session
     *     </a>
     */
    public void displaceSession(String path, String flags)
            throws ExecuteException {
        displaceSession(path, flags, 0);
       
    }
    
    /**
     * Displace file. Plays a file or stream to a channel.
     * 
     * @param path
     *            any sound format FreeSWITCH supports, wav, local_steam, shout
     *            etc.
     * @param flags
     *            flags to stream, null if none
     * @param timeLimitMillis
     *            optional time limit, 0 for none
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_displace_session
     *     </a>
     */
    public void displaceSession(String path, String flags,
            long timeLimitMillis) throws ExecuteException {
        StringBuilder sb = new StringBuilder(path);
        if(nn(flags)) {
            sb.append(" ").append(flags);
        }
        if(timeLimitMillis > 0 ) {
            sb.append(" +").append(timeLimitMillis);
        }
        
        sendExeMesg("displace_session",sb.toString());
    }

    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid)
            throws ExecuteException {

        eavesdrop(uuid, false, null, null, null, null);
    }
    
    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid, boolean enableDTMF)
            throws ExecuteException {

        eavesdrop(uuid, enableDTMF, null, null, null, null);
    }

    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     * @param groupId
     *            if specified, eavesdrop only works with channels that have an
     *            "eavesdrop_group" channel variable set to the same name.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid, boolean enableDTMF, String groupId)
            throws ExecuteException {

        eavesdrop(uuid, enableDTMF, groupId, null, null, null);
    }

    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     * @param groupId
     *            if specified, eavesdrop only works with channels that have an
     *            "eavesdrop_group" channel variable set to the same name.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid, boolean enableDTMF, String groupId,
            String failedWav) throws ExecuteException {
        eavesdrop(uuid, enableDTMF, groupId, failedWav, null, null);
    }

    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     * @param groupId
     *            if specified, eavesdrop only works with channels that have an
     *            "eavesdrop_group" channel variable set to the same name.
     * @param failedWav
     *            ex: /sounds/failed.wav
     * @param newChannelWav
     *            ex: /sounds/new_chan_announce.wav
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid, boolean enableDTMF, String groupId,
            String failedWav, String newChannelWav) throws ExecuteException {

        eavesdrop(uuid, enableDTMF, groupId, failedWav, newChannelWav, null);

    }

    /**
     * Provides the ability to spy on a channel. It often referred to as call
     * barge. For persistent spying on a user see Mod_spy.
     * 
     * DTMF signals during eavesdrop, 2 to speak with the uuid, 1 to speak with
     * the other half, 3 to engage a three way, 0 to restore eavesdrop, * to
     * next channel.
     * 
     * @param uuid
     *            uuid of the call or 'all' for all
     * @param groupId
     *            if specified, eavesdrop only works with channels that have an
     *            "eavesdrop_group" channel variable set to the same name.
     * @param failedWav
     *            ex: /sounds/failed.wav
     * @param newChannelWav
     *            ex: /sounds/new_chan_announce.wav
     * @param idleWav
     *            ex: /sounds/idle.wav
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eavesdrop
     *     </a>
     */
    public void eavesdrop(String uuid, boolean enableDTMF, String groupId,
            String failedWav, String newChannelWav, String idleWav)
            throws ExecuteException {

        if (nn(groupId)) {
            set("eavesdrop_require_group", groupId);
        }
        if (nn(failedWav)) {
            set("eavesdrop_indicate_failed", failedWav);
        }
        if (nn(newChannelWav)) {
            set("eavesdrop_indicate_new", newChannelWav);
        }
        if (nn(idleWav)) {
            set("eavesdrop_indicate_idle", idleWav);
        }

        set("eavesdrop_enable_dtmf", String.valueOf(enableDTMF));

        sendExeMesg("eavesdrop", uuid);
    }
    

    /**
     * Places the calling channel in loopback mode. It simply returns everything
     * sent, including voice, DTMF, etc. Consider it an "echo test". It is
     * generally useful for checking delay in a call path.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_echo">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_echo
     *     </a>
     */
    public void echo() throws ExecuteException {
        sendExeMesg("echo");
    }

    /**
     * This application is used to play a file endlessly and the playing cannot
     * be stopped externally.
     * 
     * @param file
     *            to play
     */
    public void endlessPlayback(String file) throws ExecuteException {
        sendExeMesg("endless_playback", file);
    }

    /**
     * Eval can be used to execute an internal API or simply log some text to
     * the console.
     * 
     * @param string
     *            to eval
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eval">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_eval
     *     </a>
     */
    public void eval(String string) throws ExecuteException {
        sendExeMesg("eval", string);
    }

    /**
     * Event application can be used to fire aribtrary events.
     * 
     * @param event
     *            to send ex:
     *            Event-Subclass=myevent::notify,Event-Name=CUSTOM,key1
     *            =value1,key2=value2
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_event">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_event
     *     </a>
     */
    public void event(String event) throws ExecuteException {
        sendExeMesg("event", event);
    }

    /**
     * execute an extension from within another extension with this dialplan
     * application.
     * 
     * 
     * @param extension
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension
     *     </a>
     */
    public void executeExtension(String extension) throws ExecuteException {
        executeExtension(extension, null, null);
    }
    /**
     * execute an extension from within another extension with this dialplan
     * application.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension
     *     </a>
     */
    public void executeExtension(String extension, String dialplan) throws ExecuteException {
        executeExtension(extension, dialplan, null);
    }
    
    /**
     * execute an extension from within another extension with this dialplan
     * application.
     * 
     * @param context
     *            (will only be set if optionalDialplan is not null)
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_execute_extension
     *     </a>
     */
    public void executeExtension(String extension, String dialplan,
            String context) throws ExecuteException {
        StringBuilder sb = new StringBuilder(extension);
        if(nn(dialplan)) {
            sb.append(" ").append(dialplan);
            if(nn(context)) {
                sb.append(" ").append(context);
            }
        }
        sendExeMesg("execute_extension", sb.toString());
    }

    /**
     * Exports a channel variable from the A leg to the B leg. Variables and
     * their values will be replicated in any new channels created from the one
     * export was called.
     * 
     * @param key
     *            channel variable name
     * @param value
     *            channel variable value
     * @param local
     *            to only export to the B leg false, otherwise true
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_export">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_export
     *     </a>
     * @throws ExecuteException
     */
    public void export(String key, String value, boolean local)
            throws ExecuteException {
        StringBuilder sb = new StringBuilder();
        if(!local) {
            sb.append("nolocal:");
        }
        sb.append(key);
        sb.append("=");
        sb.append(value);
        sendExeMesg("export", sb.toString());
    }

    /**
     * When a fax is detected, the call will be routed to the ext in the context
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_fax_detect">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_fax_detect
     *     </a>
     */
    public void faxDetect(String context, String ext) throws ExecuteException {
        sendExeMesg("tone_detect", "fax 1100 r +5000 transfer " + ext + " XML "
                + context);
    }

    /**
     * Flushes DTMFs received on a channel. Useful in cases where callers may
     * have entered extra digits in one dialog and you want to flush them out
     * before sending them into another dialog.
     * 
     * @see #playAndGetDigits
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_flush_dtmf">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_flush_dtmf
     *     </a>
     */
    public void flushDTMF() throws ExecuteException {
        sendExeMesg("flush_dtmf");
    }

    /**
     * Generate TGML tones.
     * 
     * @param tone
     *            ex: Generate a 500ms beep at 800Hz, tone = "%(500,0,800)"
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_gentones">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_gentones
     *     </a>
     * @see <a href="http://wiki.freeswitch.org/wiki/TGML">
     *     http://wiki.freeswitch.org/wiki/TGML
     *     </a>
     */
    public void gentones(String tone)
            throws ExecuteException {
       gentones(tone, 0);
    }
    
    /**
     * Generate TGML tones.
     * 
     * @param tone
     *            ex: Generate a 500ms beep at 800Hz, tone = "%(500,0,800)"
     * @param loops
     *            set to a non zero nuber, -1 for infinate loop
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_gentones">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_gentones
     *     </a>
     * @see <a href="http://wiki.freeswitch.org/wiki/TGML">
     *     http://wiki.freeswitch.org/wiki/TGML
     *     </a>
     */
    public void gentones(String tone, int loops)
            throws ExecuteException {
        sendExeMesg("gentones", tone
                + (loops != 0 ? "|" + loops : ""));
    }

    /**
     * adds/deletes groups to/from the db(internal db or ODBC) and allows calls
     * to these groups in conjunction with the bridge-application. Depends on
     * mod_db and mod_dptools.
     * 
     * @param action
     *            (insert|delete|call )
     * @param groupName
     *            ex: :01@example.com
     * @param url
     *            ex: sofia/gateway/provider/0123456789
     * @throws ExecuteException
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_group">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_group
     *     </a>
     */
    public void group(String action, String groupName, String url)
            throws ExecuteException {
        sendExeMesg("group", action + ":" + groupName + ":" + url);
    }

    /**
     * Hangs up a channel, with an optional reason supplied.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_hangup">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_hangup
     *     </a>
     */
    public void hangup() throws ExecuteException {
        sendExeMesg("hangup", null);
    }
    
    /**
     * Hangs up a channel, with an optional reason supplied.
     * 
     * @param reason
     *            if not null the hangup reason, ex: USER_BUSY
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_hangup">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_hangup
     *     </a>
     */
    public void hangup(String reason) throws ExecuteException {
        sendExeMesg("hangup", reason);
    }

    /**
     * Dumps channel information to console.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_info">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_info
     *     </a>
     */
    public void info() throws ExecuteException {
        sendExeMesg("info", null);
    }
    
    /**
     * Dumps channel information to console.
     * 
     * @param level
     *            if not null the level to log. Ex: notice Ex:
     *            bridge_pre_execute_bleg_app=info
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_info">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_info
     *     </a>
     */
    public void info(String level) throws ExecuteException {
        sendExeMesg("info", level);
    }

    /**
     * Allows one channel to bridge itself to the a or b leg of another call.
     * The remaining leg of the original call gets hungup
     * 
     * @param bleg
     *            intercept the b leg of the call
     * @throws ExecuteException
     */
    public void intercept(String uuid, boolean bleg) throws ExecuteException {
        sendExeMesg("intercept", (bleg ? "-bleg " : "") + uuid);
    }

    /**
     * Logs a string of text to the console
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_log">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_log
     *     </a>
     * @see <a href="http://wiki.freeswitch.org/wiki/Mod_logfile">
     *     http://wiki.freeswitch.org/wiki/Mod_logfile
     *     </a>
     */
    public void log(String message)
            throws ExecuteException {
       log(null,message);
    }
    
    /**
     * Logs a string of text to the console
     * 
     * @param level
     *            ex: DEBUG, INFO
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_log">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_log
     *     </a>
     * @see <a href="http://wiki.freeswitch.org/wiki/Mod_logfile">
     *     http://wiki.freeswitch.org/wiki/Mod_logfile
     *     </a>
     */
    public void log(String level, String message)
            throws ExecuteException {
        sendExeMesg("log", (nn(level) ? level + " " : "")
                + message);
    }

    /**
     * Creates a directory. Also creates parent directories by default(When they
     * don't exist).
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_mkdir">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_mkdir
     *     </a>
     */
    public void mkdir(String path) throws ExecuteException {
        sendExeMesg("mkdir", path);
    }

    /**
     * Places a channel "on hold" in the switch, instead of in the phone. Allows
     * for a number of different options, including: Set caller in a place where
     * the channel won't be hungup on, while waiting for someone to talk to.
     * Generic "hold" mechanism, where you transfer a caller to it. Please note
     * that to retrieve a call that has been "parked", you'll have to bridge to
     * them or transfer the call to a valid location. Also, remember that
     * parking a call does *NOT* supply music on hold or any other media. Park
     * is quite literally a way to put a call in limbo until you you
     * bridge/uuid_bridge or transfer/uuid_transfer it. For a different means of
     * using 'park', see mod_fifo.
     * 
     * @throws ExecuteException
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_park">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_park
     *     </a>
     */
    public void park() throws ExecuteException {
        sendExeMesg("park");
    }

    /**
     * Speak a phrase of text using a predefined phrase macro. (For more
     * information on TTS see mod_cepstral and OpenMRCP.) See also the speech
     * phrase management page for more information and examples; This command
     * relies on the configuration in the phrases section of the freeswitch.xml
     * file and including xml files in lang/en/*.xml. Following is a sample of
     * phrases management:
     * 
     * @param macroName
     *            ex: spell, timespec, saydate
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_phrase">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_phrase
     *     </a>
     */
    public void phrase(String macroName, String data) throws ExecuteException {
        sendExeMesg("phrase", macroName + "," + data);
    }

    /**
     * Permits proper answering of multiple simultaneous calls to the same
     * pickup group.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_pickup">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_pickup
     *     </a>
     */
    public void pickup(String group) throws ExecuteException {
        sendExeMesg("pickup", group);
    }

    /**
     * Play while doing speech recognition. Result is stored in the
     * detect_speech_result channel variable.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_detect_speech">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_detect_speech
     *     </a>
     */
    public String playAndDetectSpeech(String file, String engine,
            String grammer) throws ExecuteException {
        return playAndDetectSpeech(file, engine, null, grammer);
    }
    
    /**
     * Play while doing speech recognition. Result is stored in the
     * detect_speech_result channel variable.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_detect_speech">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_detect_speech
     *     </a>
     */
    public String playAndDetectSpeech(String file, String engine,
            String grammer, String params) throws ExecuteException {
        CommandResponse resp = sendExeMesg("play_and_detect_speech",
                file + " detect:" + engine + " {" + (nn(params) ? params : "") + "}" + grammer);
        if (resp.isOk()) {
            EslMessage eslMessage = api.sendApiCommand("uuid_getvar", uuid
                    + " detect_speech_result");
            if (eslMessage.getBodyLines().size() > 0) {
                return eslMessage.getBodyLines().get(0);
            }
        } else {
            throw new ExecuteException(resp.getReplyText());
        }
        return null;
    }

    /**
     * Play a prompt and get digits.
     * 
     * @return collected digits or null if none
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_get_digits">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_play_and_get_digits
     *     </a>
     */
    public String playAndGetDigits(int min, int max, int tries, int timeout,
            String terminator, String file, String invalidFile, String regexp,
            int digitTimeout) throws ExecuteException {

        String id = UUID.randomUUID().toString();

        CommandResponse resp = sendExeMesg("play_and_get_digits",
                String.valueOf(min)
                        + " " + max
                        + " " + tries
                        + " " + timeout
                        + " " + terminator
                        + " " + file
                        + " " + invalidFile
                        + " " + id
                        + " " + regexp
                        + " " + digitTimeout);

        if (resp.isOk()) {
            EslMessage eslMessage = api.sendApiCommand("uuid_getvar", uuid
                    + " " + id);
            if (eslMessage.getBodyLines().size() > 0) {
                return eslMessage.getBodyLines().get(0);
            }
        } else {
            throw new ExecuteException(resp.getReplyText());
        }
        return null;
    }

    /**
     * Plays a sound file on the current channel.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_playback">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_playback
     *     </a>
     */
    public void playback(String file)
            throws ExecuteException {
        playback(file, null);
    }
    
    /**
     * Plays a sound file on the current channel.
     * 
     * @param data
     *            ex: var1=val1,var2=val2 adds specific vars that will be sent
     *            in PLAYBACK_START and PLAYBACK_STOP events
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_playback">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_playback
     *     </a>
     */
    public void playback(String file, String data)
            throws ExecuteException {
        StringBuilder sb = new StringBuilder(file);
        if(nn(data)) {
            sb.append(" {");
            sb.append(data);
            sb.append("}");
        }
        sendExeMesg("playback",sb.toString());
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @param step
     *            <+[step]>|<-[step]>
     *
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */
    public void playbackSpeed(int step) throws ExecuteException {
        playbackControl("speed " + step);
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @param step
     *            <+[step]>|<-[step]>
     *
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */
    public void playbackVolume(int step) throws ExecuteException {
        playbackControl("volume " + step);
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */
    public void playbackPause() throws ExecuteException {
        playbackControl("pause");
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */
    public void playbackTruncate() throws ExecuteException {
        playbackControl("truncate");
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */
    public void playbackRestart() throws ExecuteException {
        playbackControl("restart");
    }

    /**
     * Manage the audio being played into a channel from a sound file
     * 
     * @param samples
     *            <+[samples]>|<-[samples]> Samples are the literally the number
     *            of samples in the file to jump forward or backward. In an 8kHz
     *            file, 8000 samples would represent one second, in a 16kHz file
     *            16000 samples would be one second, etc
     *
     * @see <a
     *      href="http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman">http://wiki.freeswitch.org/wiki/Mod_commands#uuid_fileman</a>
     */

    public void playbackSeek(int samples) throws ExecuteException {
        playbackControl("seek " + samples);
    }

    private void playbackControl(String cmd) throws ExecuteException {
        api.sendApiCommand("uuid_getvar", uuid + " " + cmd);
    }

    /**
     * equivalent to a SIP status code 183 with SDP. (This is the same as cmd
     * Progress in Asterisk.) It establishes media (early media) but does not
     * answer. You can use this for example to send an in-band error message to
     * the caller before disconnecting them (pre_answer, playback, reject with a
     * cause code of xxxx).
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_pre_answer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_pre_answer
     *     </a>
     */
    public void preAnswer() throws ExecuteException {
        sendExeMesg("pre_answer");
    }
    
    /**
     * Sends an event of either type PRESENCE_IN or PRESENCE_OUT. Currently,
     * this function is not very useful in conjunction with sofia. This does not
     * affect the presence of hook state for use with BLF either, but sending an
     * event that expresses the user's hook state does.
     * 
     * @param in
     *            true if in, false if out
     * @param rpid
     *            ex: dnd, unavailable
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_presence">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_presence
     *     </a>
     */
    public void presence(String user, boolean in, String rpid, String message)
            throws ExecuteException {

        sendExeMesg("presence", in ? "in" : "out" + "|" + user + "|" + rpid + "|" + message);
    }

    /**
     * Set caller privacy on calls.
     * 
     * @param type
     *            ex: no, yes, name, full, member
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_privacy">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_privacy
     *     </a>
     */
    public void privacy(String type) throws ExecuteException {
        sendExeMesg("privacy", type);
    }
    
    /**
     * Send DTMF digits after a bridge is successful from the session using the
     * method(s) configured on the endpoint in use. use the character w for a .5
     * second delay and the character W for a 1 second delay.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_queue_dtmf">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_queue_dtmf
     *     </a>
     */
    public void queueDTMF(String digits) throws ExecuteException {
        queueDTMF(digits, 0);
    }

    /**
     * Send DTMF digits after a bridge is successful from the session using the
     * method(s) configured on the endpoint in use. use the character w for a .5
     * second delay and the character W for a 1 second delay.
     *
     * @param durationsMillis
     *            ignored if <=0
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_queue_dtmf">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_queue_dtmf
     *     </a>
     */
    public void queueDTMF(String digits, int durationsMillis)
            throws ExecuteException {
        sendExeMesg("dtmf_digits", digits
                + (durationsMillis > 0 ? "@" + durationsMillis : ""));
    }

    /**
     * Read DTMF (touch-tone) digits.
     * 
     * @param min
     *            Minimum number of digits to fetch.
     * @param max
     *            Maximum number of digits to fetch.
     * @param soundFile
     *            Sound file to play before digits are fetched.
     * @param timeout
     *            Number of milliseconds to wait on each digit
     * @param terminators
     *            Digits used to end input if less than <min> digits have been
     *            pressed. (Typically '#')
     *
     * @return read string or null
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_read">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_read
     *     </a>
     */
    public String read(int min, int max, String soundFile, long timeout,
            String terminators) throws ExecuteException {

        String id = UUID.randomUUID().toString();

        CommandResponse resp = sendExeMesg("read",
                String.valueOf(min) + " " + max + " " + soundFile + " " + id + " " + timeout + " " + terminators);

        if (resp.isOk()) {
            EslMessage eslMessage = api.sendApiCommand("uuid_getvar", uuid
                    + " " + id);
            if (eslMessage.getBodyLines().size() > 0) {
                return eslMessage.getBodyLines().get(0);
            }
        } else {
            throw new ExecuteException(resp.getReplyText());
        }
        return null;
    }
    
    /**
     * Record is used for recording messages, like in a voicemail system. This
     * application will record a file to file
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record
     *     </a>
     */
    public void record(String file) throws ExecuteException {
        record("record", null, 0, 0, 0, true, false, null, null, null, null,
                null, null, 0);
    }
    
    
    /**
     * Record is used for recording messages, like in a voicemail system. This
     * application will record a file to file
     * 
     * @param timeLimitSeconds
     *            the maximum duration of the recording.
     * @param silenceThreshold
     *            is the energy level.
     * @param wateResources
     *            By default record doesn't send RTP packets. This is generally
     *            acceptable, but for longer recordings or depending on the RTP
     *            timer of your gateway, your channel may hangup with cause
     *            MEDIA_TIMEOUT. Setting this variable will 'waste' bandwidth by
     *            sending RTP even during recording. The value can be
     *            true/false/<desired silence factor>. By default the silence
     *            factor is 1400 if true
     * @param silenceHits
     *            how many positive hits on being below that thresh you can
     *            tolerate to stop default hits are sample rate * 3 / the number
     *            of samples per frame so the default, if missing, is 3.
     * @param append
     *            append or overwite if file exists
     * @param recordTile
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordCopyright
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordSoftware
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordArtist
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordComment
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordDate
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordRate
     *            the sample rate of the recording.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record
     *     </a>
     */
    public void record(String file, boolean append, boolean wateResources,
            int timeLimitSeconds, int silenceThreshold, int silenceHits,
            String recordTile, String recordCopyright, String recordSoftware,
            String recordArtist, String recordComment, String recordDate,
            int recordRate) throws ExecuteException {
        record("record", file, timeLimitSeconds, silenceThreshold, silenceHits,
                wateResources, append, recordTile, recordCopyright,
                recordSoftware, recordArtist, recordComment, recordDate,
                recordRate);
    }
    
    /**
     *  Records an entire phone call or session.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record
     *     </a>
     */
    public void recordSession(String file) throws ExecuteException {
        record("record_session", null, 0, 0, 0, true, false, null, null, null, null,
                null, null, 0);
    }
    
    
    /**
     *  Records an entire phone call or session.
     * 
     * @param timeLimitSeconds
     *            the maximum duration of the recording.
     * @param silenceThreshold
     *            is the energy level.
     * @param wateResources
     *            By default record doesn't send RTP packets. This is generally
     *            acceptable, but for longer recordings or depending on the RTP
     *            timer of your gateway, your channel may hangup with cause
     *            MEDIA_TIMEOUT. Setting this variable will 'waste' bandwidth by
     *            sending RTP even during recording. The value can be
     *            true/false/<desired silence factor>. By default the silence
     *            factor is 1400 if true
     * @param silenceHits
     *            how many positive hits on being below that thresh you can
     *            tolerate to stop default hits are sample rate * 3 / the number
     *            of samples per frame so the default, if missing, is 3.
     * 
     * @param append
     *            append or overwite if file exists
     * @param recordTile
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordCopyright
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordSoftware
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordArtist
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordComment
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordDate
     *            store in the file header meta data (provided the file format
     *            supports meta headers).
     * @param recordRate
     *            the sample rate of the recording.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_record
     *     </a>
     */
    public void recordSession(String file, boolean append, boolean wateResources,
            int timeLimitSeconds, int silenceThreshold, int silenceHits,
            String recordTile, String recordCopyright, String recordSoftware,
            String recordArtist, String recordComment, String recordDate,
            int recordRate) throws ExecuteException {
        record("record_session", file, timeLimitSeconds, silenceThreshold, silenceHits,
                wateResources, append, recordTile, recordCopyright,
                recordSoftware, recordArtist, recordComment, recordDate,
                recordRate);
    }
   
    private void record(String action, String file, int optionalTimeLimitSeconds,
            int optionalSilenceThreshold, int optionalSilenceHits,
            boolean wateResources, boolean append, String optionalRecordTile,
            String optionalRecordCopyright, String optionalRecordSoftware,
            String optionalRecordArtist, String optionalRecordComment,
            String optionalRecordDate, int optionalRecordRate)
            throws ExecuteException {

        if (nn(optionalRecordTile)) {
            set("RECORD_TITLE", optionalRecordTile);
        }
        if (nn(optionalRecordCopyright)) {
            set("RECORD_COPYRIGHT", optionalRecordCopyright);
        }
        if (nn(optionalRecordSoftware)) {
            set("RECORD_SOFTWARE", optionalRecordSoftware);
        }
        if (nn(optionalRecordArtist)) {
            set("RECORD_ARTIST", optionalRecordArtist);
        }
        if (nn(optionalRecordComment)) {
            set("RECORD_COMMENT", optionalRecordComment);
        }
        if (nn(optionalRecordDate)) {
            set("RECORD_DATE", optionalRecordDate);
        }
        if (optionalRecordRate > 0) {
            set("record_sample_rate", String.valueOf(optionalRecordRate));
        }

        set("RECORD_APPEND", String.valueOf(append));
        set("record_waste_resources", String.valueOf(wateResources));

        StringBuilder sb = new StringBuilder(file);
        if (optionalTimeLimitSeconds > 0) {
            sb.append(" ").append(optionalTimeLimitSeconds);
            if (optionalSilenceThreshold > 0) {
                sb.append(" ").append(optionalSilenceThreshold);
                if (optionalSilenceHits > 0) {
                    sb.append(" ").append(optionalSilenceHits);
                }
            }
        }

        sendExeMesg(action, sb.toString());
    }
    
    /**
     * Can redirect a channel to another endpoint, you must take care to not
     * redirect incompatible channels, as that wont have the desired effect. Ie
     * if you redirect to a SIP url it should be a SIP channel. By providing a
     * single SIP URI FreeSWITCH will issue a 302 "Moved Temporarily":
     * 
     * @param endpoint
     *            ex:"sip:foo@bar.com " or "sip:foo@bar.com,sip:foo@end.com"
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_redirect">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_redirect
     *     </a>
     */
    public void redirect(String endpoint) throws ExecuteException {
        sendExeMesg("redirect", endpoint);
    }

    /**
     * Send SIP session respond code to the SIP device.
     * 
     * @param code
     *            ex: "407" or "480 Try again later"
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_respond">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_respond
     *     </a>
     */
    public void respond(String code) throws ExecuteException {
        sendExeMesg("respond", code);
    }
    
    /**
     * This causes an 180 Ringing to be sent to the originator.
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_ring_ready">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_ring_ready
     *     </a>
     */
    public void ringReady() throws ExecuteException {
        sendExeMesg("ring_ready");
    }
    
    /**
     * The say application will use the pre-recorded sound files to read or say
     * various things like dates, times, digits, etc. The say application can
     * read digits and numbers as well as dollar amounts, date/time values and
     * IP addresses. It can also spell out alpha-numeric text, including
     * punctuation marks. There's a transcript of the pre-recorded files in the
     * sources under docs/phrase/phrase_en.xml
     * 
     * @param moduleName
     *            Module name is usually the channel language, e.g. "en" or "es"
     * @param sayType
     *            Say type is one of the following NUMBER ITEMS PERSONS MESSAGES
     *            CURRENCY TIME_MEASUREMENT CURRENT_DATE CURRENT_TIME
     *            CURRENT_DATE_TIME TELEPHONE_NUMBER TELEPHONE_EXTENSION URL
     *            IP_ADDRESS EMAIL_ADDRESS POSTAL_ADDRESS ACCOUNT_NUMBER
     *            NAME_SPELLED NAME_PHONETIC SHORT_DATE_TIME
     * @param sayMethod
     *            Say method is one of the following N/A PRONOUNCED ITERATED
     *            COUNTED
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_say">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_say
     *     </a>
     */
    public void say(String moduleName, String text, String sayType,
            String sayMethod) throws ExecuteException {

        say(moduleName, text, sayType, sayMethod, null);
    }

    /**
     * The say application will use the pre-recorded sound files to read or say
     * various things like dates, times, digits, etc. The say application can
     * read digits and numbers as well as dollar amounts, date/time values and
     * IP addresses. It can also spell out alpha-numeric text, including
     * punctuation marks. There's a transcript of the pre-recorded files in the
     * sources under docs/phrase/phrase_en.xml
     * 
     * @param moduleName
     *            Module name is usually the channel language, e.g. "en" or "es"
     * @param sayType
     *            Say type is one of the following NUMBER ITEMS PERSONS MESSAGES
     *            CURRENCY TIME_MEASUREMENT CURRENT_DATE CURRENT_TIME
     *            CURRENT_DATE_TIME TELEPHONE_NUMBER TELEPHONE_EXTENSION URL
     *            IP_ADDRESS EMAIL_ADDRESS POSTAL_ADDRESS ACCOUNT_NUMBER
     *            NAME_SPELLED NAME_PHONETIC SHORT_DATE_TIME
     * @param sayMethod
     *            Say method is one of the following N/A PRONOUNCED ITERATED
     *            COUNTED
     * @param gender
     *            Say gender is one of the following (For languages with
     *            gender-specific grammar, like French and German) FEMININE
     *            MASCULINE NEUTER
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_say">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_say
     *     </a>
     */
    public void say(String moduleName, String text, String sayType,
            String sayMethod, String gender) throws ExecuteException {

        StringBuilder sb = new StringBuilder(moduleName);
        sb.append(" ").append(sayType);
        sb.append(" ").append(sayMethod);
        if (nn(gender)) {
            sb.append(" ").append(gender);
        }
        sb.append(" ").append(text);

        sendExeMesg("say", sb.toString());
    }
    
    /**
     * Schedule future broadcast.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     * @param path
     *            ex: /tmp/howdy.wav
     * @param leg
     *            can be aleg,bleg,both
     */
    public void schedBroadcast(long seconds, boolean interval, String path,
            String leg) throws ExecuteException {
        StringBuilder sb = new StringBuilder();
        if (interval) {
            sb.append('+');
        }
        sb.append(seconds);
        sb.append(" ").append(path);
        sb.append(" ").append(leg);
        sendExeMesg("sched_broadcast", sb.toString());
    }
    
    /**
     * The sched_hangup application allows you to schedule a hangup action for a
     * call, basically to limit call duration.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     */
    public void schedHangup(long seconds, boolean interval)
            throws ExecuteException {
       schedHangup(seconds, interval, null);
    }

    
    /**
     * The sched_hangup application allows you to schedule a hangup action for a
     * call, basically to limit call duration.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     * @param cause
     *            ex:allotted_timeout
     */
    public void schedHangup(long seconds, boolean interval, String cause)
            throws ExecuteException {
        StringBuilder sb = new StringBuilder();
        if (interval) {
            sb.append('+');
        }
        sb.append(seconds);
        if (nn(cause)) {
            sb.append(" ").append(cause);
        }
        sendExeMesg("sched_hangup", sb.toString());
    }

    /**
     * Schedule a transfer in the future.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer
     *     </a>
     */
    public void schedTransfer(long seconds, boolean interval, String extension)
            throws ExecuteException {
        schedTransfer(seconds, interval, extension, null, null);
    }

    /**
     * Schedule a transfer in the future.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer
     *     </a>
     */
    public void schedTransfer(long seconds, boolean interval, String extension,
            String dialPlan) throws ExecuteException {
        schedTransfer(seconds, interval, extension, dialPlan, null);
    }

    /**
     * Schedule a transfer in the future.
     * 
     * @param seconds
     *            the epoc time in the future, or the number of seconds in the
     *            future
     * @param interval
     *            is the param seconds an epoc time or interval
     *
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_sched_transfer
     *     </a>
     */
    public void schedTransfer(long seconds, boolean interval, String extension,
            String dialPlan, String context) throws ExecuteException {
        StringBuilder sb = new StringBuilder();
        if (interval) {
            sb.append('+');
        }
        sb.append(seconds);
        sb.append(" ").append(extension);
        if (nn(dialPlan)) {
            sb.append(" ").append(dialPlan);
            if (nn(context)) {
                sb.append(" ").append(context);
            }
        }
        sendExeMesg("sched_transfer", sb.toString());
    }

    /**
     * Send DTMF digits from the session using the method(s) configured on the
     * endpoint in use. Use the character w for a .5 second delay and the
     * character W for a 1 second delay.
     */
    public void sendDTMF(String digits)throws ExecuteException {
        sendDTMF(digits, 0);
    }
    
    /**
     * Send DTMF digits from the session using the method(s) configured on the
     * endpoint in use. Use the character w for a .5 second delay and the
     * character W for a 1 second delay.
     */
    public void sendDTMF(String digits, int durationMillis)
            throws ExecuteException {
        StringBuilder sb = new StringBuilder(digits);
        if (durationMillis > 0) {
            sb.append('@').append(durationMillis);
        }

        sendExeMesg("send_dtmf", sb.toString());
    }
    
    /**
     * Set a channel variable for the channel calling the application.
     * 
     * @param key
     *            channel_variable name
     * @param value
     *            channel_variable value
     */
    public void set(String key, String value) throws ExecuteException {
        sendExeMesg("set", key + "=" + value);
    }
    
    public void speak(String engine, String voice, String message) throws ExecuteException {
        sendExeMesg("speak", engine + "|" + voice + "|" + message);
    }

    /**
     * Immediately transfer the calling channel to a new context. If there
     * happens to be an xml extension named <destination_number> then control is
     * "warped" directly to that extension. Otherwise it goes through the entire
     * context checking for a match.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer
     *     </a>
     */
    public void transfer(String destinationNumber) throws ExecuteException {
        transfer(destinationNumber, null, null);
    }

    /**
     * Immediately transfer the calling channel to a new context. If there
     * happens to be an xml extension named <destination_number> then control is
     * "warped" directly to that extension. Otherwise it goes through the entire
     * context checking for a match.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer
     *     </a>
     */
    public void transfer(String destinationNumber, String dialplan)
            throws ExecuteException {
        transfer(destinationNumber, dialplan, null);
    }

    /**
     * Immediately transfer the calling channel to a new context. If there
     * happens to be an xml extension named <destination_number> then control is
     * "warped" directly to that extension. Otherwise it goes through the entire
     * context checking for a match.
     * 
     * @see <a href="http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer">
     *     http://wiki.freeswitch.org/wiki/Misc._Dialplan_Tools_transfer
     *     </a>
     */
    public void transfer(String destinationNumber, String dialplan,
            String context) throws ExecuteException {
        StringBuilder sb = new StringBuilder(destinationNumber);
        if (nn(dialplan)) {
            sb.append(" ").append(dialplan);
            if (nn(context)) {
                sb.append(" ").append(context);
            }
        }
        sendExeMesg("transfer", sb.toString());
    }
    
    public String ApiCommand(String command, String args) {
        EslMessage eslMessage = api.sendApiCommand(command, args);
        StringBuilder sb = new StringBuilder();
        for(String line : eslMessage.getBodyLines()) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    private CommandResponse sendExeMesg(String app) throws ExecuteException {
        return sendExeMesg(app, null);
    }

    private CommandResponse sendExeMesg(String app, String args)
            throws ExecuteException {
        SendMsg msg = new SendMsg();
        msg.addCallCommand("execute");
        msg.addExecuteAppName(app);
        if (nn(args)) {
            msg.addExecuteAppArg(args);
        }
        CommandResponse resp = api.sendMessage(msg);
        if (!resp.isOk()) {
            throw new ExecuteException(resp.getReplyText());
        } else {
            return resp;
        }
    }
    
    
    private boolean nn(Object obj) {return obj != null;}

}
