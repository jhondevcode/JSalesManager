package com.jsm.img.png;

import java.awt.Image;
import java.awt.Toolkit;

import java.net.URL;

/**
 * This class provides the functionalities to be able to load images of png
 * format to the program and thus maintain a cleaner code.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class PNGLoader {

	/**
	 * Load a png image from the indicated path. If the image is not in the
	 * specified path, a default image will be returned.
	 * 
	 * @param image file path
	 */
	public static Image load(String path) {
		URL imgURL = PNGLoader.class.getResource(path);
		if (imgURL != null) {
			return Toolkit.getDefaultToolkit().getImage(imgURL);
		} else {
			return Toolkit.getDefaultToolkit().getImage(PNGLoader.class.getResource("/img/png/default/256x256.png"));
		}
	}

	/**
	 * Upload a png image but with the difference that the image size will be
	 * resized.
	 * 
	 * @param image file path
	 */
	public static Image load(String path, int width, int height) {
		URL imgURL = PNGLoader.class.getResource(path);
		if (imgURL != null) {
			return Toolkit.getDefaultToolkit().getImage(imgURL);
		} else {
			String imgPath = "";
			if (width >= 16 && width < 24) {
				imgPath = "/img/png/default/16x16.png";
			} else if (width >= 24 && width < 32) {
				imgPath = "/img/png/default/24x24.png";
			} else if (width >= 32 && width < 64) {
				imgPath = "/img/png/default/32x32.png";
			} else if (width >= 64 && width < 128) {
				imgPath = "/img/png/default/64x64.png";
			} else if (width >= 128 && width < 256) {
				imgPath = "/img/png/default/128x128.png";
			} else if (width >= 256 && width < 512) {
				imgPath = "/img/png/default/256x256.png";
			} else if (width >= 512) {
				imgPath = "/img/png/default/512x512.png";
			}
			return Toolkit.getDefaultToolkit().getImage(PNGLoader.class.getResource(imgPath));
		}
	}

}
