package org.tc.osgi.bundle.syslog.server.module.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;
import org.tc.osgi.bundle.syslog.server.conf.SyslogServerPropertyFile;
import org.tc.osgi.bundle.syslog.server.core.SyslogServerWrapper;
import org.tc.osgi.bundle.syslog.server.interf.core.ISyslogServerWrapper;
import org.tc.osgi.bundle.syslog.server.interf.module.service.ISyslogServerService;
import org.tc.osgi.bundle.syslog.server.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.syslog.server.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

public class SyslogServerServiceImpl implements ISyslogServerService {

	private static Map<String, SyslogServerWrapper> servers = Collections.synchronizedMap(new HashMap<String, SyslogServerWrapper>());
	private String defaultHost;
	private String defaultPort;

	public SyslogServerServiceImpl() {

	}

	public String getDefaultHost() throws FieldTrackingAssignementException {
		if (defaultHost == null) {
			PropertyServiceProxy.getInstance().getYamlPropertyFile(SyslogServerPropertyFile.getInstance().getYamlFile()).fieldTraking(this, "defaultHost");
		}
		return defaultHost;
	}

	public String getDefaultPort() throws FieldTrackingAssignementException {
		if (defaultPort == null) {
			PropertyServiceProxy.getInstance().getYamlPropertyFile(SyslogServerPropertyFile.getInstance().getYamlFile()).fieldTraking(this, "defaultPort");
		}
		return defaultPort;
	}

	@Override
	public ISyslogServerWrapper getDefaultServer() throws FieldTrackingAssignementException {
		return this.getSyslogServer(SyslogProtocole.UDP, getDefaultHost(), Integer.parseInt(getDefaultPort()));
	}

	@Override
	public ISyslogServerWrapper getDefaultServer(final SyslogProtocole prot) throws FieldTrackingAssignementException {
		return this.getSyslogServer(prot, getDefaultHost(), Integer.parseInt(getDefaultPort()));
	}

	@Override
	public ISyslogServerWrapper getSyslogServer(final String key) throws FieldTrackingAssignementException {
		if (SyslogServerServiceImpl.servers.containsKey(key)) {
			return SyslogServerServiceImpl.servers.get(key);
		}
		return this.getDefaultServer();
	}

	@Override
	public ISyslogServerWrapper getSyslogServer(final SyslogProtocole prot, final String addr, final int port) throws FieldTrackingAssignementException {
		final SyslogServerWrapper wrapper = new SyslogServerWrapper(prot, addr, port);
		final String key = wrapper.getKey();
		if (!SyslogServerServiceImpl.servers.containsKey(key)) {
			LoggerServiceProxy.getInstance().getLogger(SyslogServerServiceImpl.class).debug("Le server syslog demande n'existe pas, il est cree avec la clef " + wrapper.getKey());
			SyslogServerServiceImpl.servers.put(key, wrapper);
			final Thread t = new Thread(wrapper, "SyslogThread-" + key);
			wrapper.setThreadContainer(t);
		}
		LoggerServiceProxy.getInstance().getLogger(SyslogServerServiceImpl.class).debug("Recuperation du server syslog de clef " + wrapper.getKey());
		return this.getSyslogServer(key);
	}

}
