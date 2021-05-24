package com.jsm.img.svg;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.image.BufferedImage;

/**
 * The class has been specifically designed to provide a transcoder of svg
 * images to a byte buffer, with which it can be used within Swing components or
 * saved in a path with a standard image format.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class BufferedImageTranscoder extends ImageTranscoder {

	private BufferedImage bufferedImage;

	public BufferedImageTranscoder() {
		super();
		this.bufferedImage = null;
	}

	@Override
	public BufferedImage createImage(int width, int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void writeImage(BufferedImage bufferedImage, TranscoderOutput transcoderOutput) {
		this.bufferedImage = bufferedImage;
	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImage;
	}

}
