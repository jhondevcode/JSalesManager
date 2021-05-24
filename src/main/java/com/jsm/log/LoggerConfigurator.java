package com.jsm.log;

import com.jsm.JSMApp;

import org.tinylog.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This class has the functions of managing the settings for the tinylog logger,
 * with which the events that occur while the program is running will be
 * recorded. A particular feature is the creation of a log file per day, each
 * day a different log file is created.
 *
 * @author jhondev-code
 * @version 1.0.0
 */
public class LoggerConfigurator {

	private static final String LOG_OUTPUT = JSMApp.WORKSPACE + JSMApp.PATH_SEPARATOR + "logs";
	public static final String LOG_FILE = LOG_OUTPUT + JSMApp.PATH_SEPARATOR + "log_" + LocalDate.now() + ".log";

	/**
	 * It does a verification of the output directory in which the log file will be
	 * placed
	 */
	private static void checkOutput() {
		File output = new File(LOG_OUTPUT);
		if (!output.exists()) {
			output.mkdirs();
		}
	}

	/**
	 * Makes a review of the log file created on the day of program execution
	 */
	private static void checkLogFile() {
		File logFile = new File(LOG_FILE);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the settings for the tinylog framework logger
	 */
	public static void configure() {
		checkOutput();
		checkLogFile();
		Configuration.set("writer", "file");
		Configuration.set("writer.file", LOG_FILE);
	}

}
