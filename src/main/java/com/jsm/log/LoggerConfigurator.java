package com.jsm.log;

import com.jsm.JSMApp;
import org.tinylog.configuration.Configuration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class LoggerConfigurator {

    private static final String LOG_OUTPUT = JSMApp.WORKSPACE + JSMApp.PATH_SEPARATOR + "logs";
    public static final String LOG_FILE = LOG_OUTPUT + JSMApp.PATH_SEPARATOR + "log_" + LocalDate.now() + ".log";

    private static void checkOutput() {
        File output = new File(LOG_OUTPUT);
        if (!output.exists()) {
            output.mkdirs();
        }
    }

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

    public static void configure() {
        checkOutput();
        checkLogFile();
        Configuration.set("writer", "file");
        Configuration.set("writer.file", LOG_FILE);
    }

}
