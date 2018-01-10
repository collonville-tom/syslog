package org.tc.osgi.bundle.syslog.server.core;

import java.io.IOException;

import org.productivity.java.syslog4j.SyslogConstants;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.impl.event.printstream.FileSyslogServerEventHandler;
import org.tc.osgi.bundle.syslog.common.enumerate.SyslogProtocole;
import org.tc.osgi.bundle.syslog.server.core.handler.Syslog2Log4JEventHandler;
import org.tc.osgi.bundle.syslog.server.interf.core.ISyslogServerWrapper;
import org.tc.osgi.bundle.syslog.server.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.utils.interf.pattern.observer.AbstractSubject;

public class SyslogServerWrapper extends AbstractSubject implements ISyslogServerWrapper, Runnable {

	private SyslogServerIF server;
	private Thread threadContainer;

	public SyslogServerWrapper(final SyslogProtocole protocole, final String addr, final int port) {

		if (protocole.equals(SyslogProtocole.TCP)) {
			server = SyslogServer.getThreadedInstance(SyslogConstants.TCP);
		} else {
			server = SyslogServer.getThreadedInstance(SyslogConstants.UDP);
		}

		server.getConfig().setHost(addr);
		server.getConfig().setPort(port);

		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Creation et Init de SyslogServerIF: " + getKey());
	}

	public void addFileSyslogServerEventHandler(final String outputFile) throws IOException {
		server.getConfig().addEventHandler(new FileSyslogServerEventHandler(outputFile));
		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Redirection flux syslog vers fichier " + outputFile);
	}

	public void addSyslog2Log4JEventHandler() {
		server.getConfig().addEventHandler(new Syslog2Log4JEventHandler(this));
		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Redirection flux syslog vers log4j");
	}

	public String getKey() {
		final StringBuilder b = new StringBuilder(server.getProtocol());
		b.append(":").append(server.getConfig().getHost()).append(":").append(server.getConfig().getPort());
		return b.toString();
	}

	public Thread getThreadContainer() {
		return threadContainer;
	}

	public void restart() {
		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Redemarrage du server s'il fonctionnait" + getKey());
		stop();
		runServer();
	}

	@Override
	public void run() {
		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Lancement du server " + getKey());
		server.run();

	}

	public void runServer() {
		threadContainer.start();
	}

	public void setThreadContainer(final Thread t) {
		threadContainer = t;
	}

	public void stop() {
		LoggerServiceProxy.getInstance().getLogger(SyslogServerWrapper.class).debug("Arret du server " + getKey());
		server.shutdown();
	}

}
