package com.jsm.core.config;

import com.jsm.core.Workspace;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class allows handling the properties configured for the network such as
 * the update url, the proxy, the server path, etc. This capacity makes the
 * program capable of working connected to the network sending and receiving
 * data in real time.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class NetworkConfiguration extends Configuration {

	/**
	 * Sets the path where the network configuration file is located.
	 */
	NetworkConfiguration() {
		super(Workspace.CONFIG_DIR + Workspace.PATH_SEPARATOR + "network_config.properties");
	}

	/**
	 * It verifies if the configuration file is on the disk. If there is no
	 * configuration file, it will proceed to create a configuration file with
	 * default properties.
	 */
	@Override
	public void initializeDefault() {
		if (!this.getConfigFileInstance().exists()) {
			try {
				this.getConfigFileInstance().createNewFile();
				this.reset();
			} catch (IOException ioe) {
				Logger.error(ioe);
				System.exit(1);
			}
		}
	}

	/**
	 * @return the value of a property
	 */
	@Override
	public String get(String key) {
		return this.read(key);
	}

	/**
	 * Modify the value of a property in the configuration file.
	 */
	@Override
	public void set(String key, String value) {
		this.write(key, value);
	}

	/**
	 * Reset network settings
	 */
	@Override
	public void reset() {
		// in advance ...
	}

	@Override
	public Map<String, String> get(List<String> keys) {
		return this.get(keys);
	}
}
