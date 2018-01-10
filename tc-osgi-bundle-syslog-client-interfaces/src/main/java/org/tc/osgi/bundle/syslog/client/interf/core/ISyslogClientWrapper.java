package org.tc.osgi.bundle.syslog.client.interf.core;

public interface ISyslogClientWrapper {

	void alert(String message);

	void critical(String message);

	void debug(String message);

	void emergency(String message);

	void error(String message);

	void info(String message);

}
