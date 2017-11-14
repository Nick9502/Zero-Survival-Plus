package com.dev.main;

import java.awt.image.BufferedImage;

/*
 * SpriteSheet class makes use of 
 * Java BufferedImage(lots of features) class to 
 * load and access spritesheet
 *  
 */
public class SpriteSheet {

	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss){
		this.sprite=ss;
	}
	/*
	 * grabImage to get specific 32 X 32 sprite image
	 * @param col column in spritesheet
	 * @param row row in spritesheet
	 * @param width width of subImage to grab
	 * @param height height of subImage to grab
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = sprite.getSubimage((row*32)-32,(col*32)-32,width,height);
		return img;
	}
}
