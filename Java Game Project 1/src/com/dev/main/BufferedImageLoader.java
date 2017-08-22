package com.dev.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader { // Class to Load Images(Sprite sheet)

	BufferedImage image; //Create BufferedImage object instance
	
	public BufferedImage loadImage(String path){
		try {
			image=ImageIO.read(getClass().getResource(path)); //Load Image by entering path
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
