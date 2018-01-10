package org.tc.osgi.bundle.syslog.client.core;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogConstants;
import org.productivity.java.syslog4j.SyslogIF;
import org.tc.osgi.bundle.syslog.client.interf.core.ISyslogClientWrapper;
import org.tc.osgi.bundle.syslog.client.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;

public class SyslogClientWrapper implements ISyslogClientWrapper {

	private SyslogIF syslog;

	public SyslogClientWrapper(final SyslogProtocole prot, final String addr, final int port) {
		final StringBuilder debutmsg = new StringBuilder("Init SyslogClientWrapper ").append(prot.name()).append(":").append(addr).append(":").append(
			String.valueOf(port));
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug(debutmsg.toString());
		if (prot.equals(SyslogProtocole.TCP)) {
			syslog = Syslog.getInstance(SyslogConstants.TCP);
		} else {
			syslog = Syslog.getInstance(SyslogConstants.UDP);
		}
		syslog.getConfig().setHost(addr);
		syslog.getConfig().setPort(port);
	}

	public void alert(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message alert " + prepareLog(message));
		syslog.alert(message);

	}

	public void critical(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message critical " + prepareLog(message));
		syslog.critical(message);

	}

	public void debug(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message debug " + prepareLog(message));
		syslog.debug(message);

	}

	public void emergency(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message emergency " + prepareLog(message));
		syslog.emergency(message);

	}

	public void error(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message error " + prepareLog(message));
		syslog.error(message);

	}

	public void info(final String message) {
		LoggerServiceProxy.getInstance().getLogger(SyslogClientWrapper.class).debug("Envoie du message info " + prepareLog(message));
		syslog.info(message);

	}

	private String prepareLog(final String message) {
		// cette primitive devra disparaitre
		final StringBuilder debutmsg = new StringBuilder(message);
		debutmsg.append(" on ");
		debutmsg.append(syslog.getConfig().getHost());
		debutmsg.append(":");
		debutmsg.append(syslog.getConfig().getPort());
		return debutmsg.toString();
	}

}
