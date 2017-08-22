package com.dev.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss){
		this.sprite=ss;
	}
	public BufferedImage grabImage(int col, int row, int width, int height){ //Use to Access our spritesheet
		BufferedImage img = sprite.getSubimage((row*32)-32,(col*32)-32,width,height);  //32 x 32 sprites
		return img;
	}
}
