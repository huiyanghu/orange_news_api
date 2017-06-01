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

	public static void main(String[] args) throws IOException {
		InputStream is = new URL("https://s3.amazonaws.com/avos-cloud-vv7zert5utbf/rplL8OSssW6btXxqG0TH0RRDHsimF3a3r5EgjX4r.jpg").openStream();

		BufferedImage sourceImg = ImageIO.read(is);
		System.out.println(sourceImg.getWidth());
		System.out.println(sourceImg.getHeight());

		is.close();
	}
}
