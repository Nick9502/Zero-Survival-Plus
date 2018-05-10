package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;


public class Menu extends MouseAdapter{
	
	// Declare necessary Objects.
	private Game game;
	private Handler handler;
	private HUD hud;
	
	private boolean hover;
	
	public Menu(Game game, Handler handler, HUD hud)
	{ 
		this.game=game;
		this.handler=handler;
		this.hud=hud;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mX=e.getX();
		int mY=e.getY();
		
		AudioPlayer.getSound("Click Sound").play();
		
		//Normal Difficulty Button
		if (mouseOver(mX,mY,210,150,200,64)&& game.gameState==STATE.Select){
			game.gameState = STATE.Game;
			game.diff=0;
			handler.AddObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			handler.clearAllEnemy();
			
			for(int xx=0;xx<Game.WIDTH+32;xx+=32)
			{
				handler.AddObject(new Block(xx,Game.HEIGHT-62,ID.Block));
			}
		}
	}
	
	// Mouse Released Used for Smoother Transition
	public void mouseReleased(MouseEvent e)
	{ 
		int mX=e.getX();
		int mY=e.getY();
		
		//Play Button. Choose Difficulty next.
		if (mouseOver(mX,mY,210,150,200,64)&& game.gameState==STATE.Menu)
		{
			game.gameState = STATE.Select;
			return; 
		}
			
		//Help Button	
		if (mouseOver(mX,mY,210,250,200,64)&& game.gameState==STATE.Menu)
		{
			game.gameState = STATE.Help;
		}
		
		//Back Button for HELP
		if (mouseOver(mX,mY,455,385,175,60)&& game.gameState==STATE.Help)
		{
			game.gameState = STATE.Menu;
			return;
		}
		//Quit Button
		if (mouseOver(mX,mY,210,350,200,64)&& game.gameState==STATE.Menu)
		{
			game.gameState = STATE.Quit;
			System.exit(1);
		}
		//Menu Button for Game Over
		if (mouseOver(mX,mY,210,350,200,64)&& game.gameState==STATE.GameOver)
		{
		    game.gameState = STATE.Menu;
		    hud.setScore(0);
		    hud.setLevel(1);
		}
		//--------------------Difficulty Select---------------------------//
		
		//Extreme Difficulty Button	
		if (mouseOver(mX,mY,210,250,200,64)&& game.gameState==STATE.Select)
		{
			game.gameState = STATE.Game;
			game.diff=1;
			handler.AddObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			handler.clearAllEnemy();
			
			for(int xx=0;xx<Game.WIDTH+32;xx+=32)
			{
				handler.AddObject(new Block(xx,Game.HEIGHT-62,ID.Block));
			}
		}
		
		//Back Button for Difficulty
		if (mouseOver(mX,mY,455,385,175,60)&& game.gameState==STATE.Select)
		{
			game.gameState = STATE.Menu;
			return;
		}
	}
	
	//If Mouse is inside selected area.(Menu Button for example) return true. Else false.
	private boolean mouseOver(int mX, int mY, int x, int y, int width, int height){
		if (mX>x && mX < x+width){
			if (mY>y && mY< y+height){
				return true;
			}else {return false; }
		}else return false;
	}
	
	public void tick()
	{
		// TODO Improve menu cohesion. Get mouse coordinates in tick.
	}
	
	public void render(Graphics g)
	{
		if (game.gameState==STATE.Menu)
		{
			Font title = new Font("arial", 1, 50);
			Font options = new Font("arial", 1, 30);
			
			g.setFont(title);
			g.setColor(Color.blue);
			g.drawString("Zero Survival+", (Game.WIDTH/4)-10, 100);
			
			g.setFont(options);
			if (hover){g.setColor(Color.blue);}
			else if (!hover){g.setColor(Color.white);}
			
			//Play Button
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 190);
			
			//Help Button
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 295);
	
			//Quit Button
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 275, 395);
		}
	
		// Help Menu Screen
		else if (game.gameState==STATE.Help)
		{ 
			Font title = new Font("arial", 1, 50);
			Font options = new Font("arial", 1, 25);
			Font text = new Font("arial", 1, 20);
			
			g.setFont(title);
			g.setColor(Color.blue);
			g.drawString("Help & Information", Game.WIDTH/7, 70);
			
			g.setFont(text);
			g.setColor(Color.white);
			g.drawString("Welcome to Zero Survival+", Game.WIDTH/7, 170);
			g.drawString("Nicolas Hunter Java Project", Game.WIDTH/7, 220);
			g.drawString("Use WASD Keys to Dodge Enemies get a High Score.", Game.WIDTH/7, 270);
			
			//Back Button
			g.setFont(options);
			g.setColor(Color.white);
			g.drawRect(455, 385, 175, 60);
			g.drawString("Back", 515, 425);
		}
		// Game Over Screen
		else if (game.gameState==STATE.GameOver)
		{ 
			Font title = new Font("arial", 1, 50);
			Font options = new Font("arial", 1, 25);
			Font text = new Font("arial", 1, 20);
			
			g.setFont(title);
			g.setColor(Color.blue);
			g.drawString("Game Over", Game.WIDTH/7, 70);
			
			g.setFont(text);
			g.setColor(Color.white);
			g.drawString("You lost with a Score of: "+ hud.getScore(), Game.WIDTH/7, 170);
			g.drawString("You made it to Wave:  "+ hud.getLevel(), Game.WIDTH/7, 220);
			g.drawString("Try Again or go back to the Main Menu", Game.WIDTH/7, 270);
			
			//Menu Button on Game Over
			g.setFont(options);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Menu", 275, 395);
		}
		
		//Difficulty Select
		else if (game.gameState==STATE.Select)
		{
			Font title = new Font("arial", 1, 50);
			Font options = new Font("arial", 1, 30);
			
			g.setFont(title);
			g.setColor(Color.blue);
			g.drawString("Select Difficulty", (Game.WIDTH/4)-20, 100);
			
			g.setFont(options);
			if (hover){g.setColor(Color.blue);}
			else if (!hover){g.setColor(Color.white);}
			
			//Normal Difficulty
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 260, 190);
			
			//Extreme Difficulty
			g.drawRect(210, 250, 200, 64);
			g.drawString("Extreme", 250, 295);

			//Back Button
			g.setFont(options);
			g.setColor(Color.white);
			g.drawRect(455, 385, 175, 60);
			g.drawString("Back", 515, 425);
		}
	}
}
