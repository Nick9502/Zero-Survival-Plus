package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject{

	
	public Block(float x, float y, ID id) 
	{
		super(x, y, id);
	}

	public void tick() 
	{
		// TODO implement color change effect when player near
	}

	public void render(Graphics g) 
	{
		if (id==ID.Block){g.setColor(Color.white);}
		g.drawRect((int)x, (int)y, 32,32);
	}
	// 32 x 32 Rectangle
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,32,32); 
	}

}
