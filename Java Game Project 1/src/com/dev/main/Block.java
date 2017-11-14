package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Block class represents in game surface.
 */
public class Block extends GameObject{

	// Block Constructor inherired from GameObject
	public Block(float x, float y, ID id) 
	{
		super(x, y, id);
	}

	public void tick() 
	{
		// TODO implement color change effect when player near
		// TODO handler collision through individual objects.
	}

	// Abstract Render method inherited from GameObject
	public void render(Graphics g) 
	{
		if (id==ID.Block){g.setColor(Color.white);}
		g.drawRect((int)x, (int)y, 32,32);
	}
	
	// Get bounds of 32 x 32 Rectangle
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x,(int)y,32,32); 
	}

}
