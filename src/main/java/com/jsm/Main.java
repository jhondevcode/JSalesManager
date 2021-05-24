package com.jsm;

import com.jsm.log.LoggerConfigurator;
import org.tinylog.Logger;

public class Main {

    static {
        LoggerConfigurator.configure();
    }

    public static void main(String[] args) {
        Logger.info("Starting...");
        JSMApp.start(args);
    }

}
