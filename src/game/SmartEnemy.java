package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		//Runs through Handler(All objects) If player is found set object to player.
		for (int i =0;i<handler.objects.size();i++){
			if (handler.objects.get(i).getID()==ID.Player){player=handler.objects.get(i);}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		float diffX = x-player.getX() - 8; //Difference in X. Aligned with center of player
		float diffY = y-player.getY() - 8; //Difference in Y. Aligned with center of player
		float distance = (float)Math.sqrt(Math.pow(x-player.getX(), 2)+ Math.pow(y-player.getY(), 2)); //Distance Formula
		
		velX= (float)((-1.0/distance) * diffX);
		velY= (float)((-1.0/distance) * diffY);
		
		x+=velX;
		y+=velY;
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.green,16,16,0.1f,handler));
	}
	
	public void render(Graphics g) {
		if (id==ID.SmartEnemy){g.setColor(Color.green);}
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
