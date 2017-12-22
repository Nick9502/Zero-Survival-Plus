package com.dev.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Class to Load Buffered Images
 * Using BufferedImage and ImageIO
 * Use in Game Class to Load Spritsheet
 * or other images into game.
 */
public class BufferedImageLoader { 

	//Create BufferedImage object instance
	BufferedImage image; 
	
	/*
	 * Try and Catch to get Buffered Image from path.
	 */
	public BufferedImage loadImage(String path){
		try {
			//Load Resource from inputted path. getResource method of Class
			image=ImageIO.read(getClass().getResource(path)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
