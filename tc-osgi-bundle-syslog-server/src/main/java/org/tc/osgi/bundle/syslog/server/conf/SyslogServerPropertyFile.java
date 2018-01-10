package org.tc.osgi.bundle.syslog.server.conf;

import org.tc.osgi.bundle.utils.interf.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

/**
 * AptConfiguration.java.
 * @author collonville thomas
 * @version 0.0.1
 */
public final class SyslogServerPropertyFile extends AbstractPropertyFile {
	/**
	 * String BUNDLE_RACINE.
	 */
	public final static String BUNDLE_RACINE = "tc.osgi.bundle.syslog-server.";

	/**
	 * DefaultConfig conf.
	 */
	private static SyslogServerPropertyFile instance = null;

	/**
	 * String EQUINOXLOADERFILE.
	 */
	public static final String SYSLOG_SERVER_FILE = "syslog-server";

	/**
	 * getInstance.
	 * @return DefaultConfig
	 * @throws EquinoxConfigException
	 * @throws FieldTrackingAssignementException
	 */
	public static SyslogServerPropertyFile getInstance() {
		if (SyslogServerPropertyFile.instance == null) {
			SyslogServerPropertyFile.instance = new SyslogServerPropertyFile();
		}
		return SyslogServerPropertyFile.instance;
	}

	/**
	 * SyslogClientPropertyFile constructor.
	 */
	private SyslogServerPropertyFile() {
		super(SyslogServerPropertyFile.SYSLOG_SERVER_FILE, SyslogServerPropertyFile.class.getClassLoader());
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getBundleRacine()
	 */
	@Override
	public String getBundleRacine() {
		return SyslogServerPropertyFile.BUNDLE_RACINE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getConfFile()
	 */
	@Override
	public String getConfFile() {
		return SyslogServerPropertyFile.SYSLOG_SERVER_FILE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getXMLFile()
	 */
	@Override
	public String getXMLFile() {
		return SyslogServerPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

}
