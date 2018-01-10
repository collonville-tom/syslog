package org.tc.osgi.bundle.syslog.client.interf.module.service;

import org.tc.osgi.bundle.syslog.client.interf.core.ISyslogClientWrapper;
import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

public interface ISyslogClientService {

	public ISyslogClientWrapper getDefaultClient() throws FieldTrackingAssignementException;

	public ISyslogClientWrapper getSyslogClient(SyslogProtocole prot, String addr, int port);

}
