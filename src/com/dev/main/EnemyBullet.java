package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBullet extends GameObject {

	Handler handler;
	Random r = new Random();
	
	public EnemyBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velY=6;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		collision();
		//if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		//if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16,0.1f,handler));
	}
	private void collision(){ //Detects for collision with specific object. Handler needed.
		for (int i=0;i<handler.objects.size();i++){ //Checks for which object is colliding.
			GameObject tempObject=handler.objects.get(i);
			if (tempObject.getID()==ID.Block){//If the object is the Block
				if(getBounds().intersects(tempObject.getBounds())){ //If the objects intersect.
					handler.RemoveObject(this);
				}
			
				}}}	

	public void render(Graphics g) {
		if (id==ID.EnemyBullet){g.setColor(Color.white);}
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
