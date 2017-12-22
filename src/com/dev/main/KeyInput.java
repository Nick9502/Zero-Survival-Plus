package com.dev.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dev.main.Game.STATE;

/*
 * KeyInput Class inherits from KeyAdapter
 * 
 */
public class KeyInput extends KeyAdapter{ 

	// Handler necessary to loop through and update objects after key input.
	private Handler handler;
	Game game;
	
	// Initialize necessary objects
	public KeyInput(Handler handler, Game game)
	{ 
		this.handler=handler;
		this.game=game;
	}
	/*
	 * keyPressed - when a key on the keyboard is pressed.
	 */
	public void keyPressed(KeyEvent e){ 
		int key = e.getKeyCode();
		
		// Check which object to register inputs for
		for(int i=0;i<handler.objects.size();i++)
		{ 
			// Temporary object to store current active object.
			GameObject tempObject = handler.objects.get(i); 
			
			// Checks for Player Object and Moves Player
			if (tempObject.getID()==ID.Player)
			{
				// Player Jumping
				if(key == KeyEvent.VK_W && tempObject.canJump==true)
				{
					tempObject.setvelY(-handler.spd);tempObject.jumping=true;tempObject.canJump=false;
				}
				// Move Left
				if(key == KeyEvent.VK_A )
				{
					tempObject.setvelX(-handler.spd);tempObject.movingLeft=true;tempObject.movingRight=false;
				}
				// Move Right
				if(key == KeyEvent.VK_D )
				{
					tempObject.setvelX(handler.spd);tempObject.movingRight=true;tempObject.movingLeft=false;
				}
			}
		}
		// Pause Button when P key is pressed
		if (key == KeyEvent.VK_P) 
		{ 
			if (game.gameState == STATE.Game)
			{
				// If Game paused already unpause
				if (Game.paused)
				{
					Game.paused = false;
				}
				// Else Pause Game
				else Game.paused=true;
			}
		
		}
		//Hit escape to exit the game.
		if (key== KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		} 
		
		// Hit Space to access Shop.
		if (key== KeyEvent.VK_SPACE)
		{
			// If not in Shop go shop
			if (game.gameState==STATE.Game)game.gameState=STATE.Shop;
			// Else exit shop and resume game
			else if (game.gameState==STATE.Shop)game.gameState=STATE.Game;
		} 
	}
	/*
	 * Events for when keyboard key is released
	 */
	public void keyReleased(KeyEvent e){ 
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.objects.size();i++)
		{ 
			GameObject tempObject = handler.objects.get(i); //Temporary object to store id.
			
			// Checks for Player Object and Stops
			if (tempObject.getID()==ID.Player)
			{
				// Player Falls after Jumping(Gravity)
				if(key == KeyEvent.VK_W)
				{
					tempObject.setvelY(0);tempObject.falling=true;
				}
				// Player Stop Moving Left
				if(key == KeyEvent.VK_A && tempObject.movingLeft==true)
				{
					tempObject.setvelX(0);tempObject.movingLeft=false;
				}
				// Player Stops Moving Right.
				if(key == KeyEvent.VK_D && tempObject.movingRight==true)
				{
					tempObject.setvelX(0);tempObject.movingRight=false;
				}
			}
		}
	}
	 

	
	}
