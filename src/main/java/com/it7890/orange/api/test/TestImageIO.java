package com.it7890.orange.api.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Astro on 17/6/1.
 */
public class TestImageIO {

	public static void main(String[] args) {
		InputStream is = null;
		try {
			is = new URL("https://s3.amazonaws.com/avos-cloud-vv7zert5utbf/ANbVM924mJUbAtzO8ycb5cO5JcrlJh6ntczf7Qch.jpg").openStream();
			BufferedImage sourceImg = ImageIO.read(is);
			System.out.println(sourceImg.getWidth());
			System.out.println(sourceImg.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
