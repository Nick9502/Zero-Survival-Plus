package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;

/*
 *  Upgrade Shop / Pause Menu
 *  User will be able to spend earned points in shop
 *  to buy upgrades for player 
 */
public class Shop extends MouseAdapter{
	
	// Default Upgrade Costs.
	private int B1, B2, B3 = 200;
	
	// List of Items in the Shop
	public enum ITEM
	{
		Null,
		UpgradeHealth,
		RefillHealth,
		UpgradeSpeed
	};
	
	// Current Item Choice
	public ITEM choice = ITEM.Null;

	// Declare necessary Objects.
	Handler handler;
	HUD hud;
	Game game;



	// Initialize Objects for Access
	public Shop(Handler handler, HUD hud, Game game)
	{ 
		this.handler=handler;
		this.hud=hud;
		this.game=game;
	}
	
	// Draw out how shop looks
	public void render(Graphics g)
	{
		// Title Font
		g.setColor(Color.white);
		g.setFont(new Font("arial",1,48));
		g.drawString("SHOP", (Game.WIDTH/2)-70, 50);
		
		//Box 1 - Upgrade Health
		if (choice==ITEM.UpgradeHealth){g.setColor(Color.green);}
		else if (choice!=ITEM.UpgradeHealth){g.setColor(Color.white);}
		g.setFont(new Font("arial",0,12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost: "+ B1, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		//Box 2 - Refill Health
		if (choice==ITEM.RefillHealth){g.setColor(Color.green);}
		else if (choice!=ITEM.RefillHealth){g.setColor(Color.white);}
		g.drawString("Refill Health", 260, 120);
		g.drawString("Cost: "+ B1, 260, 140);
		g.drawRect(250, 100, 100, 80);
		
		//Box 3 - Upgrade Speed
		if (choice==ITEM.UpgradeSpeed){g.setColor(Color.green);}
		else if (choice!=ITEM.UpgradeSpeed){g.setColor(Color.white);}
		g.drawString("Upgrade Speed", 410, 120);
		g.drawString("Cost: "+ B1, 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		g.drawString("SCORE: "+ hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Press Space to Resume Game", (Game.WIDTH/2)-100, 330);
		
	}
	// Using Mouse to Navigate Shop
	public void mousePressed(MouseEvent e)
	{
		int mX=e.getX();
		int mY=e.getY();
		
		
		//Box 1 - Upgrade Health
		if (mouseOver(mX,mY,100,100,100,80)&& game.gameState==STATE.Shop)
		{
			AudioPlayer.getSound("Click Sound").play();
			if (hud.getScore()>=B1){
				hud.setScore(hud.getScore()-B1);
				B1+=200;
				hud.bounds+=20;
				//hud.HEALTH=(100+(hud.bounds/2));
			}
		}
		//Box 2 - Refill Health
		if (mouseOver(mX,mY,250,100,100,80)&& game.gameState==STATE.Shop)
		{
			AudioPlayer.getSound("Click Sound").play();
			if (hud.getScore()>=B2){
				hud.setScore(hud.getScore()-B2);
				B2+=200;
				hud.HEALTH=(100+(hud.bounds/2));
			}
		}
		//Box 3 - Upgrade Speed
		if (mouseOver(mX,mY,400,100,100,80)&& game.gameState==STATE.Shop)
		{
			AudioPlayer.getSound("Click Sound").play();
			if (hud.getScore()>=B3){
				hud.setScore(hud.getScore()-B3);
				handler.spd++;
			}
		}
	}
   /*
	* If Mouse is inside selected area.(Menu Button for example) return true. Else false.
	* Use in Conjunction with mouseEvent e. mX, mY = e.getX, e.getY
	*/
	public boolean mouseOver(int mX, int mY, int x, int y, int width, int height)
	{
		
		if (mX>x && mX < x+width)
		{
			if (mY>y && mY< y+height)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else return false;
	}

}


