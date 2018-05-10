package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

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
			//image = ImageIO.read(new FileImageInputStream(new File(path)));
			image = ImageIO.read(new FileInputStream(new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
