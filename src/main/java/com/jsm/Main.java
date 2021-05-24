package com.jsm;

import com.jsm.log.LoggerConfigurator;

import org.tinylog.Logger;

/**
 * This class has the simple task of configuring the loger and launching the
 * program.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class Main {

	/**
	 * Launch the program
	 */
	public static void main(String[] args) {
		new com.jsm.core.Workspace().startChecking();
		LoggerConfigurator.configure();
		Logger.info("Starting...");
		JSMApp.start(args);
	}

}
