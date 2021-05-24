package com.jsm;

/**
 * This class is in charge of executing the visual part of the program as well
 * as loading the configurations and preparing the workspace for the internal
 * components of the program.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class JSMApp {

	public static final String PATH_SEPARATOR = System.getProperty("file.separator");
	public static final String WORKSPACE = System.getProperty("user.home") + PATH_SEPARATOR + ".jsm";
	public static final String APP_VERSION = "0.1.0";

	/**
	 * This method contains the call to other methods which are in charge of
	 * configuring and displaying the program.
	 */
	public static void start(String[] args) {
	}

}
