package org.tc.osgi.bundle.syslog.server.interf.module.service;

import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;
import org.tc.osgi.bundle.syslog.server.interf.core.ISyslogServerWrapper;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

public interface ISyslogServerService {

	public ISyslogServerWrapper getDefaultServer() throws FieldTrackingAssignementException;

	public ISyslogServerWrapper getDefaultServer(SyslogProtocole prot) throws FieldTrackingAssignementException;

	public ISyslogServerWrapper getSyslogServer(String key) throws FieldTrackingAssignementException;

	public ISyslogServerWrapper getSyslogServer(SyslogProtocole prot, String addr, int port) throws FieldTrackingAssignementException;

}
