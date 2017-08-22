package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject {

	private Handler handler;
	private BufferedImage FastEnemy_image;
	
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=3;
		velY=9;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		FastEnemy_image=ss.grabImage(1, 4, 16, 16); //Row 1,Col 4, Height 16, Width 16
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.pink,16,16,0.1f,handler));
	}
	public void render(Graphics g) {
		if (id==ID.FastEnemy){
			//g.setColor(Color.pink);
			g.drawImage(FastEnemy_image,(int)x,(int)y,null);
			}
		//g.fillRect((int)x, (int)y, 16, 16);
	}

}
