package com.jsm.core.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.tinylog.Logger;

import com.jsm.core.Workspace;

/**
 * This configurator provides specializes in working with the translations used
 * to name the visual components within the application. This gives the program
 * the ability to be multilanguage.
 * 
 * It also has the functionalities to request language files on the network,
 * with which it will be possible to extend the number of languages that already
 * had previously, also said file will be downloaded locally within the
 * workspace.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class LangConfiguration extends Configuration {

	/**
	 * Sets the path where the language configuration file is located.
	 */
	LangConfiguration() {
		super(Workspace.CONFIG_DIR + Workspace.PATH_SEPARATOR + "lang_config.properties");
	}

	/**
	 * It checks if the configuration file is on the disk, if it is, an empty file
	 * will be created and then the corresponding basic properties will be
	 * established.
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
	 * Reset the language settings.
	 */
	@Override
	public void reset() {
		Map<String, String> properties = Map.of(
				"name", "English",
				"url", "https://github.com/jhondev-code/JSalesManager/lang/EN_en.dict",
				"path", "${WORKSPACE}" + Workspace.PATH_SEPARATOR + "lang" + Workspace.PATH_SEPARATOR + "EN_en.dict");
		this.dumpMap(properties);
	}

	@Override
	public Map<String, String> get(List<String> keys) {
		return this.read(keys);
	}

}
