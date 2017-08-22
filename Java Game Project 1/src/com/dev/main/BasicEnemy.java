package com.dev.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

	private Handler handler;
	private BufferedImage enemy_image;
	
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=5;
		velY=5;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_image=ss.grabImage(1, 2, 16, 16); //Row 1,Col 2, Height 16, Width 16
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16,0.1f,handler));
	}
	public void render(Graphics g) {
		if (id==ID.BasicEnemy){
			//g.setColor(Color.red);
			g.drawImage(enemy_image,(int)x,(int)y,null);
		}
		//g.fillRect((int)x, (int)y, 16, 16);
	}

}
