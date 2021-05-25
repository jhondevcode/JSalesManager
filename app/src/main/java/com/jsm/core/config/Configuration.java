package com.jsm.core.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.tinylog.Logger;

/**
 * Provides a configuration abstraction of which features must be implemented in
 * the specific configuration classes.
 * 
 * The configuration files which will implement these functionalities can use
 * any file format to store the data, but the ways to consult them will be
 * implemented within each indicated abstract method.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public abstract class Configuration {

	private final File configFile;

	public Configuration(String configPath) {
		this.configFile = new File(configPath);
	}

	/**
	 * This method is in charge of creating a default configuration which will be
	 * dumped in the established configuration folder.
	 */
	public abstract void initializeDefault();

	/**
	 * Returns a value from the configuration file, in case the value is not found,
	 * a null value will be returned.
	 * 
	 * @param key configuration to which its value will be consulted
	 */
	public abstract String get(String key);

	/**
	 * Restores the default values of the configuration file.
	 */
	public abstract void reset();

	/**
	 * Modify the value of a key inside the configuration file.
	 * 
	 * @param key   setting to which you want to change the set value
	 * @param value new value which will be assigned to the indicated configuration.
	 */
	public abstract void set(String key, String value);

	protected String read(String k) {
		Properties config = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(this.configFile));
			config.load(bis);
			return config.getProperty(k);
		} catch (IOException ioe) {
			Logger.error(ioe);
		} finally {
			config.clear();
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ioe) {
					Logger.error(ioe);
				}
			}
		}
		return "null";
	}

	protected Map<String, String> read(List<String> ks) {
		if (ks.size() > 0) {
			if (ks.size() == 1) {
				return Map.of(ks.get(0), this.read(ks.get(0)));
			} else {
				Properties config = new Properties();
				BufferedInputStream bis = null;
				try {
					bis = new BufferedInputStream(new FileInputStream(this.configFile));
					Map<String, String> mapKV = new HashMap<>();
					config.load(bis);
					ks.forEach(key -> mapKV.put(key, String.valueOf(config.getProperty(key))));
					return mapKV;
				} catch (IOException ioe) {
					Logger.error(ioe);
				} finally {
					config.clear();
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException ioe) {
							Logger.error(ioe);
						}
					}
				}
				return Map.of();
			}
		} else {
			return Map.of();
		}
	}

	protected void write(String k, String v) {
		this.dumpMap(Map.of(k, v));
	}

	public void dumpMap(Map<String, String> properties) {
		Properties config = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(this.configFile));
			config.load(bis);
			properties.forEach(config::setProperty);
		} catch (IOException ioe) {
			Logger.error(ioe);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ioe) {
					Logger.error(ioe);
				}
			}
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(this.configFile));
				config.store(bos, "");
			} catch (IOException ioe) {
				Logger.error(ioe);
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException ioe) {
						Logger.error(ioe);
					}
				}
			}
			config.clear();
		}
	}

	protected File getConfigFileInstance() {
		return this.configFile;
	}

}
