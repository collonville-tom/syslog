package org.tc.osgi.bundle.syslog.server.interf.core;

import org.tc.osgi.bundle.utils.interf.pattern.observer.ISubject;

public interface ISyslogServerWrapper extends ISubject {

	void addSyslog2Log4JEventHandler();

	void restart();

	void stop();

}
