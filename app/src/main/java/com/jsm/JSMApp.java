package com.jsm;

import com.jsm.core.Workspace;

import java.util.Map;

/**
 * This class is in charge of executing the visual part of the program as well
 * as loading the configurations and preparing the workspace for the internal
 * components of the program.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class JSMApp {

	public static final String APP_VERSION = "0.1.0";

	/**
	 * This method contains the call to other methods which are in charge of
	 * configuring and displaying the program.
	 */
	public static void start(String[] args) {
		new com.jsm.core.Workspace().startChecking();
		Map<String, String> properties = Map.of(
				"name", "English",
				"url", "https://github.com/jhondev-code/JSalesManager/lang/EN_en.dict",
				"path", "${WORKSPACE}" + Workspace.PATH_SEPARATOR + "lang" + Workspace.PATH_SEPARATOR + "EN_en.dict");
		properties.forEach((key, value) -> System.out.println(key + ": " + value));
	}

}
