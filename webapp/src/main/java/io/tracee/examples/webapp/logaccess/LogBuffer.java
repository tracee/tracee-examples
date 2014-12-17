package io.tracee.examples.webapp.logaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Fixed size, round robin buffer for log messages.
 */
public final class LogBuffer {

    private final static int LOG_MESSAGE_BUFFER_SIZE = 100000;

    private final static LogBuffer instance = new LogBuffer();

    private int currentIndex = 0;

    private LogMessage[] logMessageBuffer = new LogMessage[LOG_MESSAGE_BUFFER_SIZE];

    /**
     * Private constructor. Used to create the singleton.
     */
    private LogBuffer() {

    }

    /**
     * Adds a single log message to the buffer.
     *
     * @param logMessage the log message to add.
     */
    public synchronized void addLogMessage(final LogMessage logMessage) {

        if (logMessage != null) {

            // goto next available index
            currentIndex = getNextIndex();
            logMessageBuffer[currentIndex] = logMessage;

        }

    }

    /**
     * Gets all LogMessages related to passed request or session id.
     *
     * @param requestOrSessionId the request or session id to be used to filter log messages
     * @return a list of sorted log messages
     */
    public List<LogMessage> filterLogMessages(final String requestOrSessionId) {

        List<LogMessage> result = new ArrayList<LogMessage>();

        if (requestOrSessionId != null) {

            int index = currentIndex;
            Long lastTimestamp = null;
            for (int i = 0; i < LOG_MESSAGE_BUFFER_SIZE - 1; i++) {

                LogMessage logMessage = logMessageBuffer[index];

                // log message must be ignored if it doesn't exist
                if (logMessage != null) {

                    if (requestOrSessionId.equals(logMessage.getRequestId()) || requestOrSessionId.equals(logMessage.getSessionId())) {
                        result.add(logMessage);
                    }

                }

                // get next index to check
                index = getPreviousIndex(index);
            }

        }

        // sort result by timestamp
        Collections.sort(result, new Comparator<LogMessage>() {

            @Override
            public int compare(final LogMessage o1, final LogMessage o2) {
                return ((Long)o1.getTimestamp()).compareTo(o2.getTimestamp());
            }
        });

        return result;
    }

    /**
     * Returns the next buffer index for log message entries.
     *
     * @return
     */
    private int getNextIndex() {
        return currentIndex + 1 < LOG_MESSAGE_BUFFER_SIZE ? currentIndex + 1 : 0;
    }

    /**
     * Returns the next buffer index for log message entries.
     *
     * @return
     */
    private int getPreviousIndex(final int index) {
        return index - 1 > 0 ? index - 1 : LOG_MESSAGE_BUFFER_SIZE - 1;
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton log buffer instance.
     */
    public static LogBuffer getInstance() {
        return instance;
    }

}
