package com.dev.main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;

	private float gravity = 0.2f;
	private final float MAX_SPEED = 10;
	
	private BufferedImage player_image;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		player_image=ss.grabImage(1, 1, 32, 32); //Col 1,Row 1, Height 32, Width 32
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32); // Bounds of rectangle is same as player object
	}

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp((int) x, 0, Game.WIDTH - 38);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 61);

		if (falling || jumping) {// Set gravity to work
			velY += gravity;
			if (velY >= MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		collison();
		// handler.AddObject(new
		// Trail((int)x,(int)y,ID.Trail,Color.white,32,32,0.1f,handler));//If
		// same player as color will override color.
	}

	private void collison() { // Detects for collision with specific object. Handler needed
		for (int i = 0; i < handler.object.size(); i++) { // Checks for which object is colliding
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Block) {// If the object is the Block
				// Collision code
				if (getBounds().intersects(tempObject.getBounds())) { // If the object intersects
					canJump = true;
					falling = false;
					jumping = false;
					y = tempObject.getY() - 32;
					velY = 0;
				} else {
					falling = true;
				}
			}
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SmartEnemy|| tempObject.getID() == ID.HardEnemy) {//If the object is the Enemy											
				// Collision code
				if (getBounds().intersects(tempObject.getBounds())) { // If the object intersects
					handler.Damage();
					handler.RemoveObject(tempObject);
				}
			}

		}
	}

	public void render(Graphics g) {
		if (id == ID.Player) {
			g.drawImage(player_image,(int)x,(int)y,null);
			//g.setColor(Color.blue);
		}
		//g.fillRect((int) x, (int) y, 32, 32);
		

		// Draw Collision bounds using Graphics 2D.
		/*
		 * Graphics2D g2d = (Graphics2D) g; g.setColor(Color.GREEN);
		 * g2d.draw(getBounds());
		 */ // Unique to Graphics 2D. Take getbounds function as parameter?

	}

}
