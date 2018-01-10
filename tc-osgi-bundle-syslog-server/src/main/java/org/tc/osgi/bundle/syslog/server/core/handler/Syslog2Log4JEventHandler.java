package org.tc.osgi.bundle.syslog.server.core.handler;

import java.net.SocketAddress;

import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.SyslogServerSessionEventHandlerIF;
import org.tc.osgi.bundle.syslog.server.core.SyslogServerWrapper;
import org.tc.osgi.bundle.syslog.server.module.service.LoggerServiceProxy;

public class Syslog2Log4JEventHandler implements SyslogServerSessionEventHandlerIF {

	/**
	 * long serialVersionUID.
	 */
	private static final long serialVersionUID = 480408050804900654L;

	private SyslogServerWrapper syslogServerWrapper;

	public Syslog2Log4JEventHandler(SyslogServerWrapper syslogServerWrapper) {
		this.syslogServerWrapper = syslogServerWrapper;
	}

	@Override
	public void destroy(final SyslogServerIF syslogServer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void event(final Object session, final SyslogServerIF syslogServer, final SocketAddress socketAddress, final SyslogServerEventIF _event) {
		final StringBuilder b = new StringBuilder("Facility : ");
		b.append(String.valueOf(_event.getFacility()));
		b.append(",Level : ");
		b.append(String.valueOf(_event.getLevel()));
		b.append(",Message : ");
		b.append(String.valueOf(_event.getMessage()));

		LoggerServiceProxy.getInstance().getLogger(Syslog2Log4JEventHandler.class).debug(b.toString());

		this.syslogServerWrapper.notifyObservers();

	}

	@Override
	public void exception(final Object session, final SyslogServerIF syslogServer, final SocketAddress socketAddress, final Exception exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(final SyslogServerIF syslogServer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionClosed(final Object session, final SyslogServerIF syslogServer, final SocketAddress socketAddress, final boolean timeout) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object sessionOpened(final SyslogServerIF syslogServer, final SocketAddress socketAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
