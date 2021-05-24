package com.jsm.core.config;

import com.jsm.core.Workspace;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * It allows managing the configuration properties for the views and components
 * of the graphic part of the program, such as: colors, positions,
 * characteristics, etc.
 *
 * This gives the program the ability to fully customize the graphics.
 *
 * @author jhondev-code
 * @version 1.0.0
 */
public class ViewConfiguration extends Configuration {

	/**
	 * The path where the configuration for the graphic part is found is
	 * established.
	 */
	ViewConfiguration() {
		super(Workspace.CONFIG_DIR + Workspace.PATH_SEPARATOR + "view_config.properties");
	}

	/**
	 * It performs the verification of the configuration file for the graphic part
	 * of the program, if it has not been found, it proceeds to create one with
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Modify the value of a property in the configuration file.
	 */
	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
	}

	/**
	 * This method resets all the properties of the graphical part of the program.
	 */
	@Override
	public void reset() {
		// in advance...
	}

}
