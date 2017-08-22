package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;
	
	Random r = new Random(); // Particle Effect Idea
	private Color col;


	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=(r.nextInt(8 - -8)+ -8);
		velY=(r.nextInt(8 - -8)+ -8);
		if(velX==0){velX=1;}
		if(velY==0){velY=1;}
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,col,16,16,0.04f,handler));
	}
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
