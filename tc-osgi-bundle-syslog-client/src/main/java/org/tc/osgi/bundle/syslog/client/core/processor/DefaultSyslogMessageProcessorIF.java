package org.tc.osgi.bundle.syslog.client.core.processor;

import org.productivity.java.syslog4j.SyslogMessageProcessorIF;
import org.productivity.java.syslog4j.impl.message.processor.SyslogMessageProcessor;

public class DefaultSyslogMessageProcessorIF implements SyslogMessageProcessorIF {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 1869975523979352335L;

    @Override
    public byte[] createPacketData(final byte[] header, final byte[] message, final int start, final int length) {
        // new SyslogMessageProcessor();
        // Integrer ici des variation sur les valeurs ...
        return SyslogMessageProcessor.getDefault().createPacketData(header, message, start, length);
    }

    @Override
    public byte[] createPacketData(final byte[] header, final byte[] message, final int start, final int length, final byte[] splitBeginText, final byte[] splitEndText) {
        // new SyslogMessageProcessor();
        // Integrer ici des variation sur les valeurs ...
        return SyslogMessageProcessor.getDefault().createPacketData(header, message, start, length, splitBeginText, splitEndText);
    }

    @Override
    public String createSyslogHeader(final int facility, final int level, final String localName, final boolean sendLocalTimestamp, final boolean sendLocalName) {
        // new SyslogMessageProcessor();
        // Integrer ici des variation sur les valeurs ...
        return SyslogMessageProcessor.getDefault().createSyslogHeader(facility, level, localName, sendLocalTimestamp, sendLocalName);
    }

}
