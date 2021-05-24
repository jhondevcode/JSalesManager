package com.jsm.core.config;

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
	 * Modify the value of a key inside the configuration file.
	 * 
	 * @param key   setting to which you want to change the set value
	 * @param value new value which will be assigned to the indicated configuration.
	 */
	public abstract String set(String key, String value);

}
