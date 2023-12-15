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
package org.voice9.cc.fs.esl.transport.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.TooLongFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voice9.cc.fs.esl.transport.HeaderParser;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Decoder used by the IO processing pipeline. Client consumers should never need to use
 * this class.
 * <p/>
 * Follows the following decode algorithm (from FreeSWITCH wiki)
 * <pre>
 *    Look for \n\n in your receive buffer
 *
 *    Examine data for existence of Content-Length
 *
 *    If NOT present, process event and remove from receive buffer
 *
 *    IF present, Shift buffer to remove 'header'
 *    Evaluate content-length value
 *
 *    Loop until receive buffer size is >= Content-length
 *    Extract content-length bytes from buffer and process
 * </pre>
 */
public class EslFrameDecoder extends ReplayingDecoder<EslFrameDecoder.State> {
    /**
     * Line feed character
     */
    static final byte LF = 10;

    protected enum State {
        READ_HEADER, READ_BODY,
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final int maxHeaderSize;
    private EslMessage currentMessage;
    private boolean treatUnknownHeadersAsBody = false;

    public EslFrameDecoder(int maxHeaderSize) {
        super(State.READ_HEADER);
        if (maxHeaderSize <= 0) {
            throw new IllegalArgumentException("maxHeaderSize must be a positive integer: " + maxHeaderSize);
        }
        this.maxHeaderSize = maxHeaderSize;
    }

    public EslFrameDecoder(int maxHeaderSize, boolean treatUnknownHeadersAsBody) {
        this(maxHeaderSize);
        this.treatUnknownHeadersAsBody = treatUnknownHeadersAsBody;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        State state = state();

        log.trace("decode() : state [{}]", state);
        switch (state) {
            case READ_HEADER:
                if (currentMessage == null) {
                    currentMessage = new EslMessage();
                }
                /*
                 *  read '\n' terminated lines until reach a single '\n'
                 */
                boolean reachedDoubleLF = false;
                while (!reachedDoubleLF) {
                    // this will read or fail
                    String headerLine = readToLineFeedOrFail(buffer, maxHeaderSize);
                    log.debug("read header line [{}]", headerLine);
                    if (!headerLine.isEmpty()) {
                        // split the header line
                        String[] headerParts = HeaderParser.splitHeader(headerLine);
                        EslHeaders.Name headerName = EslHeaders.Name.fromLiteral(headerParts[0]);
                        if (headerName == null) {
                            if (treatUnknownHeadersAsBody) {
                                // cache this 'header' as a body line <-- useful for Outbound client mode
                                currentMessage.addBodyLine(headerLine);
                            } else {
                                throw new IllegalStateException("Unhandled ESL header [" + headerParts[0] + ']');
                            }
                        }
                        currentMessage.addHeader(headerName, headerParts[1]);
                    } else {
                        reachedDoubleLF = true;
                    }
                    // do not read in this line again
                    checkpoint();
                }
                // have read all headers - check for content-length
                if (currentMessage.hasContentLength()) {
                    checkpoint(State.READ_BODY);
                    log.debug("have content-length, decoding body ..");
                    //  force the next section

                    break;
                } else {
                    // end of message
                    checkpoint(State.READ_HEADER);
                    // send message upstream
                    EslMessage decodedMessage = currentMessage;
                    currentMessage = null;

                    out.add(decodedMessage);
                    break;
                }

            case READ_BODY:
                /*
                 *   read the content-length specified
                 */
                int contentLength = currentMessage.getContentLength();
                ByteBuf bodyBytes = buffer.readBytes(contentLength);
                //log.debug("read [{}] body bytes", bodyBytes.writerIndex());
                // most bodies are line based, so split on LF
                while (bodyBytes.isReadable()) {
                    String bodyLine = readLine(bodyBytes, contentLength);
                    log.debug("read body line [{}]", bodyLine);
                    currentMessage.addBodyLine(bodyLine);
                }

                try {
                    if (bodyBytes.refCnt() == 1) {
                        bodyBytes.release();
                        bodyBytes = null;
                    }
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                }

                // end of message
                checkpoint(State.READ_HEADER);
                // send message upstream
                EslMessage decodedMessage = currentMessage;
                currentMessage = null;

                out.add(decodedMessage);
                break;

            default:
                throw new Error("Illegal state: [" + state + ']');
        }
    }

    private String readToLineFeedOrFail(ByteBuf buffer, int maxLineLength) throws TooLongFrameException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream(maxLineLength);

        while (true) {
            // this read might fail
            byte nextByte = buffer.readByte();
            if (nextByte == LF) {
                return byteArrayToString(bos);
            } else {
                // Abort decoding if the decoded line is too large.
                if (bos.size() >= maxLineLength) {
                    throw new TooLongFrameException("ESL header line is longer than " + maxLineLength + " bytes.");
                }
                bos.write(nextByte);
            }
        }
    }

    private String byteArrayToString(final ByteArrayOutputStream bos) {
        try {
            return bos.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("unsupported UTF-8 encoding", e);
        }
        return null;
    }

    private String readLine(ByteBuf buffer, int maxLineLength) throws TooLongFrameException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream(maxLineLength);

        while (buffer.isReadable()) {
            // this read should always succeed
            byte nextByte = buffer.readByte();
            if (nextByte == LF) {
                return byteArrayToString(bos);
            } else {
                // Abort decoding if the decoded line is too large.
                if (bos.size() >= maxLineLength) {
                    throw new TooLongFrameException("ESL message line is longer than " + maxLineLength + " bytes.");
                }
                bos.write(nextByte);
            }
        }

        return byteArrayToString(bos);
    }
}
