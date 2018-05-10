package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.util.Random;

public class HardEnemy extends GameObject {

	private Handler handler;
	private BufferedImage HardEnemy_image;
	
	//private Random r = new Random();//Design Random Speed Equation
	
	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=8;
		velY=8;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		HardEnemy_image=ss.grabImage(1, 3, 16, 16); //Col 2,Row 1, Height 16, Width 16
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16); // Bounds of rectangle is same as enemy.
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<=0||x>=Game.WIDTH-16){velX*=-1;}
		if(y<=0||y>=Game.HEIGHT-80){velY*=-1;}
		
		handler.AddObject(new Trail((int)x,(int)y,ID.Trail,Color.yellow,16,16,0.1f,handler));
	}
	public void render(Graphics g) {
		if (id==ID.HardEnemy){
			//g.setColor(Color.yellow);
			g.drawImage(HardEnemy_image,(int)x,(int)y,null);
			}
		//g.fillRect((int)x, (int)y, 16, 16);
	}

}
