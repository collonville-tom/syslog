package org.tc.osgi.bundle.syslog.client.module.service.impl;

import org.tc.osgi.bundle.syslog.client.conf.SyslogClientPropertyFile;
import org.tc.osgi.bundle.syslog.client.core.SyslogClientWrapper;
import org.tc.osgi.bundle.syslog.client.interf.core.ISyslogClientWrapper;
import org.tc.osgi.bundle.syslog.client.interf.module.service.ISyslogClientService;
import org.tc.osgi.bundle.syslog.client.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

public class SyslogClientServiceImpl implements ISyslogClientService {

	private String defaultHost;
	private String defaultPort;

	public SyslogClientServiceImpl() {

	}

	@Override
	public ISyslogClientWrapper getDefaultClient() throws NumberFormatException, FieldTrackingAssignementException {
		return getSyslogClient(SyslogProtocole.UDP, getDefaultHost(), Integer.parseInt(getDefaultPort()));
	}

	public String getDefaultHost() throws FieldTrackingAssignementException {
		if (defaultHost == null) {
			PropertyServiceProxy.getInstance().getYamlPropertyFile(SyslogClientPropertyFile.getInstance().getYamlFile()).fieldTraking(this, "defaultHost");
		}
		return defaultHost;
	}

	public String getDefaultPort() throws FieldTrackingAssignementException {
		if (defaultPort == null) {
			PropertyServiceProxy.getInstance().getYamlPropertyFile(SyslogClientPropertyFile.getInstance().getYamlFile()).fieldTraking(this, "defaultPort");
		}
		return defaultPort;
	}

	@Override
	public ISyslogClientWrapper getSyslogClient(final SyslogProtocole prot, final String addr, final int port) {
		return new SyslogClientWrapper(prot, addr, port);
	}

}
