package org.tc.osgi.bundle.syslog.client.conf;

import org.tc.osgi.bundle.utils.interf.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

/**
 * AptConfiguration.java.
 * 
 * @author collonville thomas
 * @version 0.0.1
 */
public final class SyslogClientPropertyFile extends AbstractPropertyFile {
	/**
	 * String BUNDLE_RACINE.
	 */
	public final static String BUNDLE_RACINE = "tc.osgi.bundle.syslog-client.";

	/**
	 * DefaultConfig conf.
	 */
	private static SyslogClientPropertyFile instance = null;

	/**
	 * String EQUINOXLOADERFILE.
	 */
	public static final String SYSLOG_CLIENT_FILE = "syslog-client";

	/**
	 * getInstance.
	 * 
	 * @return DefaultConfig
	 * @throws EquinoxConfigException
	 * @throws FieldTrackingAssignementException
	 */
	public static SyslogClientPropertyFile getInstance() {
		if (SyslogClientPropertyFile.instance == null) {
			SyslogClientPropertyFile.instance = new SyslogClientPropertyFile();
		}
		return SyslogClientPropertyFile.instance;
	}

	/**
	 * SyslogClientPropertyFile constructor.
	 */
	private SyslogClientPropertyFile() {
		super(SyslogClientPropertyFile.SYSLOG_CLIENT_FILE, SyslogClientPropertyFile.class.getClassLoader());
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getBundleRacine()
	 */
	@Override
	public String getBundleRacine() {
		return SyslogClientPropertyFile.BUNDLE_RACINE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getConfFile()
	 */
	@Override
	public String getConfFile() {
		return SyslogClientPropertyFile.SYSLOG_CLIENT_FILE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getXMLFile()
	 */
	@Override
	public String getXMLFile() {
		return SyslogClientPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

	@Override
	public String getYamlFile() {
		// TODO Auto-generated method stub
		return SyslogClientPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

}
