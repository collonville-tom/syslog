package org.tc.osgi.bundle.syslog.test.bean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.tc.osgi.bundle.syslog.client.interf.module.service.ISyslogClientService;
import org.tc.osgi.bundle.syslog.server.interf.module.service.ISyslogServerService;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserver;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserverEvent;
import org.tc.osgi.bundle.utils.interf.pattern.observer.ISubject;

public class SyslogProducteurConsomateurTestBean implements ActionListener, IObserver {

	private ISyslogClientService syslogClientService;

	private ISyslogServerService syslogServerService;
	private final Timer timer = new Timer(10000, this);

	private ILoggerUtilsService iLoggerUtilsService;

	public void setSyslogClientService(ISyslogClientService syslogClientService) {
		this.syslogClientService = syslogClientService;
	}

	public ISyslogClientService getSyslogClientService() {
		if (syslogClientService == null) {
			iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).error("erreur enregistrement syslogClient dans le bean");
		}
		return syslogClientService;
	}

	public ISyslogServerService getSyslogServerService() {
		return syslogServerService;
	}

	public void setSyslogServerService(ISyslogServerService syslogServerService) {
		this.syslogServerService = syslogServerService;
		try {
			getSyslogServerService().getDefaultServer().addObserver(this);
		} catch (final FieldTrackingAssignementException e1) {
			iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).error("erreur enregistrement syslogServer dans le bean", e1);
		}
	}

	public ILoggerUtilsService getiLoggerUtilsService() {
		return iLoggerUtilsService;
	}

	public void setiLoggerUtilsService(ILoggerUtilsService iLoggerUtilsService) {
		this.iLoggerUtilsService = iLoggerUtilsService;
	}

	public SyslogProducteurConsomateurTestBean() {

	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		final String message = "Envoie syslog message";
		iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).debug("Envoie syslog message");

		try {
			getSyslogClientService().getDefaultClient().alert(message);
			getSyslogClientService().getDefaultClient().critical(message);
			getSyslogClientService().getDefaultClient().debug(message);
			getSyslogClientService().getDefaultClient().emergency(message);
			getSyslogClientService().getDefaultClient().error(message);
			getSyslogClientService().getDefaultClient().info(message);
		} catch (final FieldTrackingAssignementException e1) {
			iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).error("Echec envoie message", e1);
		}

	}

	/**
	 * startTimer.
	 */
	public void startTimer() {
		timer.start();
	}

	/**
	 * stopTimer.
	 */
	public void stopTimer() {
		timer.stop();
	}

	@Override
	public void update(final ISubject _subject) {
		iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).debug("Reception du message syslog sur le server");

	}

	@Override
	public void update(final ISubject _subject, final IObserverEvent _event) {
		iLoggerUtilsService.getLogger(SyslogProducteurConsomateurTestBean.class).debug("Reception du message syslog sur le server");

	}

}
