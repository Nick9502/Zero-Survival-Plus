package com.dev.main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
 * Player class extends abstract class GameObject
 * Abstract methods Rectangle getBounds(), void tick(), void render()
 */
public class Player extends GameObject {
	
	Handler handler;
	private BufferedImage player_image;
	private float gravity = 0.2f;
	private final float MAX_SPEED = 10;
	Random r = new Random();

	public Player(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player_image=ss.grabImage(1, 1, 32, 32); // Col 1,Row 1, Height 32, Width 32
	}
	
	// Creates rectangle bounds. Not good with objects that arent rectangles.
	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32); // Bounds of rectangle is same as player object
	}

	// Tick methods so all game objects can update with FPS
	public void tick() 
	{
		// Move player if velocity is changed
		x += velX;
		y += velY;
		
		// Player cannot leave game window bounds.
		x = Game.clamp((int) x, 0, Game.WIDTH - 38);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 61);

		// Set gravity to work
		if (falling || jumping) 
		{
			velY += gravity;
			if (velY >= MAX_SPEED) 
			{
				velY = MAX_SPEED;
			}
		}

		// Check for collisions.
		collison();
		
		// Add Trail Effect Here. If same player as color will override color.
		// handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.white,32,32,0.1f,handler));
	}
	
	// Render GameObject to Window.
	public void render(Graphics g) 
	{
		if (id == ID.Player) 
		{
			g.drawImage(player_image,(int)x,(int)y,null);
			//g.setColor(Color.blue); Blue Rectangle
		}
		
		//g.fillRect((int) x, (int) y, 32, 32);
		

		/* Draw Collision bounds using Graphics 2D.
		 * Graphics2D g2d = (Graphics2D) g; g.setColor(Color.GREEN);
		 * g2d.draw(getBounds());
		 * Unique to Graphics 2D. Take getbounds function as parameter?
		 */

	}

	/* Detects for collision with specific object.
	 * Uses Handler to handle game objects. 
	 */
	private void collison() 
	{ 
		for (int i = 0; i < handler.object.size(); i++) { // Checks for which object is colliding
			GameObject tempObject = handler.object.get(i);
			
			// If the object is the Block
			if (tempObject.getID() == ID.Block) 
			{
				// Collision code If the object intersects
				if (getBounds().intersects(tempObject.getBounds())) 
				{ 
					canJump = true;
					falling = false;
					jumping = false;
					y = tempObject.getY() - 32;
					velY = 0;
				} 
				else 
				{
					falling = true;
				}
			}
			// If the object is an enemy.
			// Possible cohesion improvement. Have damage be controlled by enemy class not handler.
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SmartEnemy|| tempObject.getID() == ID.HardEnemy) 
			{									
				// Collision code If the object intersects
				if (getBounds().intersects(tempObject.getBounds())) 
				{ 	
					handler.Damage();
					handler.RemoveObject(tempObject);
				}
			}

		}
	}



}
