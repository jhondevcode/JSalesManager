package com.jsm;

import com.jsm.core.Workspace;
import com.jsm.core.lang.DictionaryManager;
import com.jsm.img.png.PNGLoader;
import com.jsm.ui.laf.ProgressBarUI;
import com.jsm.ui.widget.JSMFrame;

import java.util.Map;

import javax.swing.JProgressBar;
import javax.swing.JButton;

import java.util.HashMap;

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
		System.out.println("Hello world");
	}
}
