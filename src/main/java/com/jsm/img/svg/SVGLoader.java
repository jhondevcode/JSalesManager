package com.jsm.img.svg;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.awt.Image;
import java.io.InputStream;

/**
 * This class provides functionalities to be able to load svg graphics to the
 * program and provide an image buffer for the purposes that are convenient.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class SVGLoader {

	/**
	 * Loads an svg chart from an input stream and also requires the dimensions to
	 * which the image is to be taken.
	 * 
	 * @param svgFile byte stream of svg graphic file
	 * @param width   output width for image
	 * @param height  output height for image
	 */
	public static Image toImage(InputStream svgFile, float width, float height) throws TranscoderException {
		BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();
		imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
		imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);
		TranscoderInput input = new TranscoderInput(svgFile);
		imageTranscoder.transcode(input, null);
		return imageTranscoder.getBufferedImage();
	}

	/**
	 * It is responsible for loading an svg graphic but with the difference that it
	 * only requires the path of the svg graphic.
	 * 
	 * @param svgFile svg file path
	 * @param width   output width for image
	 * @param height  output height for image
	 */
	public static Image toImage(String svgPath, float width, float height) throws TranscoderException {
		BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();
		imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
		imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);
		TranscoderInput input = new TranscoderInput(SVGLoader.class.getResourceAsStream(svgPath));
		imageTranscoder.transcode(input, null);
		return imageTranscoder.getBufferedImage();
	}

}
