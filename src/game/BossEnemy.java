package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {

	private Handler handler;
	Random r = new Random();
	private int timer = 100;
	private int timer2= 50;
	
	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=0;
		velY=2;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,48,48); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if (timer<=0)velY=0;
		else timer--;
		
		if(timer<=0)timer2--;
		if(timer2<=0){
			if (velX==0)velX=5;
			int spawn= r.nextInt(30);
			if (spawn==0){handler.AddObject(new EnemyBullet((int) x,(int) y, ID.EnemyBullet,handler));
		}
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		//if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.white,48,48,0.1f,handler));
	}}

	public void render(Graphics g) {
		if (id==ID.BossEnemy){g.setColor(Color.white);}
		g.fillRect((int)x, (int)y, 48, 48);
	}

}
