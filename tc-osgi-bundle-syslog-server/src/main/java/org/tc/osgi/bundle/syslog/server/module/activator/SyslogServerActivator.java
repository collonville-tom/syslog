package org.tc.osgi.bundle.syslog.server.module.activator;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.syslog.server.interf.module.service.ISyslogServerService;
import org.tc.osgi.bundle.syslog.server.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.syslog.server.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.syslog.server.module.service.impl.SyslogServerServiceImpl;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IPropertyUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.interf.module.utils.TcOsgiProxy;

/**
 * SyslogServerActivator.java.
 * 
 * @author Collonville Thomas
 * @version 0.1.0
 */
public class SyslogServerActivator extends AbstractTcOsgiActivator {

	private TcOsgiProxy<ILoggerUtilsService> iLoggerUtilsService;
	private TcOsgiProxy<IPropertyUtilsService> iPropertyUtilsService;
	private TcOsgiProxy<ISyslogServerService> iSyslogServerService;

	@Override
	protected void checkInitBundleUtilsService(BundleContext context) throws TcOsgiException {
		throw new TcOsgiException("checkInitBundleUtilsService not implemented");

	}

	@Override
	protected void initProxys(BundleContext context) throws TcOsgiException {
		this.iPropertyUtilsService = new TcOsgiProxy<IPropertyUtilsService>(context, IPropertyUtilsService.class);
		PropertyServiceProxy.getInstance().setService(this.iPropertyUtilsService.getInstance());
		this.iLoggerUtilsService = new TcOsgiProxy<ILoggerUtilsService>(context, ILoggerUtilsService.class);
		LoggerServiceProxy.getInstance().setService(this.iLoggerUtilsService.getInstance());

	}

	@Override
	protected void initServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().registerService(ISyslogServerService.class, new SyslogServerServiceImpl(), context, this);

	}

	@Override
	protected void detachProxys(BundleContext context) throws TcOsgiException {
		this.iLoggerUtilsService.close();
		this.iPropertyUtilsService.close();

	}

	@Override
	protected void detachServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().unregister(ISyslogServerService.class, this);

	}

	@Override
	protected void beforeStart(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeStop(BundleContext context) throws TcOsgiException {
		this.iSyslogServerService.getInstance().getDefaultServer().stop();

	}

	@Override
	protected void afterStart(BundleContext context) throws TcOsgiException {
		this.iSyslogServerService = new TcOsgiProxy<ISyslogServerService>(context, ISyslogServerService.class);

		this.iSyslogServerService.getInstance().getDefaultServer().addSyslog2Log4JEventHandler();
		this.iSyslogServerService.getInstance().getDefaultServer().restart();
	}

	@Override
	protected void afterStop(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

}
