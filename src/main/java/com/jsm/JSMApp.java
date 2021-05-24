package com.jsm;

import com.jsm.img.svg.SVGLoader;
import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;
import java.awt.*;

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
		Image spotify = null;
		try {
			spotify = SVGLoader.toImage("/img/svg/spotify.svg", 64, 64);
		} catch (TranscoderException e) {
			e.printStackTrace();
		}
		if (spotify != null) {
			JFrame frame = new JFrame("Hello");
			frame.setSize(200, 200);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setIconImage(spotify);
			frame.setVisible(true);
		} else {
			System.out.println("Error: unrecognized image");
		}
	}

}
