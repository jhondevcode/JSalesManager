package com.jsm.core;

import com.jsm.core.config.Configuration;
import com.jsm.core.config.LangConfiguration;
import com.jsm.core.config.NetworkConfiguration;
import com.jsm.core.config.ViewConfiguration;

/**
 * Provides a configuration manager with which you can access the different
 * configuration managers. The instance of this class is obtained using the
 * static getInstance() method, also the handlers are started inside the
 * constructor.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class ConfigurationManager {

	private static ConfigurationManager localInstance;
	private LangConfiguration langConfig;
	private NetworkConfiguration networkConfig;
	private ViewConfiguration viewConfig;

	/**
	 * Here the configuration drivers are started, which must be used to access the
	 * corresponding configuration files.
	 */
	private ConfigurationManager() {
		this.langConfig = new LangConfiguration();
		this.langConfig.initializeDefault();

		this.networkConfig = new NetworkConfiguration();
		this.networkConfig.initializeDefault();

		this.viewConfig = new ViewConfiguration();
		this.viewConfig.initializeDefault();
	}

	/**
	 * @return the instance of the program's language manager
	 */
	public Configuration getLangConfig() {
		return this.langConfig;
	}

	/**
	 * @return the instance of the network configuration manager
	 */
	public Configuration getNetworkConfig() {
		return this.networkConfig;
	}

	/**
	 * @return the instance of the configuration manager of the program's views and
	 *         widgets
	 */
	public Configuration getViewConfig() {
		return this.viewConfig;
	}

	/**
	 * This method is used to obtain an instance of the configuration manager,
	 * previously the verification is carried out to later return a usable object.
	 */
	public static ConfigurationManager getInstance() {
		if (localInstance == null) {
			localInstance = new ConfigurationManager();
		}
		return localInstance;
	}

}
