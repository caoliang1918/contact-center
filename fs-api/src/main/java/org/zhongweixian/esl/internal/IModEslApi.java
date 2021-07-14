package org.zhongweixian.esl.internal;

import org.zhongweixian.esl.transport.event.EslEvent;
import org.zhongweixian.esl.transport.CommandResponse;
import org.zhongweixian.esl.transport.SendMsg;
import org.zhongweixian.esl.transport.message.EslMessage;

import java.util.concurrent.CompletableFuture;

public interface IModEslApi {

	enum EventFormat {

		PLAIN("plain"),
		XML("xml"),
		JSON("json");

		private final String text;

		EventFormat(String txt) {
			this.text = txt;
		}

		@Override
		public String toString() {
			return text;
		}

	}

	enum LoggingLevel {

		CONSOLE("console"),
		DEBUG("debug"),
		INFO("info"),
		NOTICE("notice"),
		WARNING("warning"),
		ERR("err"),
		CRIT("crit"),
		ALERT("alert");

		private final String text;

		LoggingLevel(String txt) {
			this.text = txt;
		}

		@Override
		public String toString() {
			return text;
		}

	}

	boolean canSend();

	EslMessage sendApiCommand(String command, String arg);

	CompletableFuture<EslEvent> sendBackgroundApiCommand(String command, String arg);

	CommandResponse setEventSubscriptions(EventFormat format, String events);

	CommandResponse cancelEventSubscriptions();

	CommandResponse addEventFilter(String eventHeader, String valueToFilter);

	CommandResponse deleteEventFilter(String eventHeader, String valueToFilter);

	CommandResponse sendMessage(SendMsg sendMsg);

	CommandResponse setLoggingLevel(LoggingLevel level);

	CommandResponse cancelLogging();

	boolean isActivate();

	void close();
}
