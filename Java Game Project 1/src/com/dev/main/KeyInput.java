package com.dev.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dev.main.Game.STATE;

public class KeyInput extends KeyAdapter{ //Extends to allow access to methods.

	private Handler handler;
	
	Game game;
	
	public KeyInput(Handler handler, Game game){ //Handler necessary to loop through and update objects after key input.
		this.handler=handler;
		this.game=game;
	}
	public void keyPressed(KeyEvent e){ //Moving player after key is pressed.
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){ // Loop to manage input.
			GameObject tempObject = handler.object.get(i); //Temporary object to store current active object.
			
			if (tempObject.getID()==ID.Player){//Key Inputs for Player 1.
				if(key == KeyEvent.VK_W && tempObject.canJump==true){tempObject.setvelY(-handler.spd);tempObject.jumping=true;tempObject.canJump=false;}
				if(key == KeyEvent.VK_A ){tempObject.setvelX(-handler.spd);tempObject.movingLeft=true;tempObject.movingRight=false;}
				if(key == KeyEvent.VK_D ){tempObject.setvelX(handler.spd);tempObject.movingRight=true;tempObject.movingLeft=false;}
			}
		}
		if (key == KeyEvent.VK_P) //Pause Button when P key is pressed
		{ 
			if (game.gameState == STATE.Game){
				if (Game.paused){Game.paused = false;}
				else Game.paused=true;
		}}
		if (key== KeyEvent.VK_ESCAPE){System.exit(1);} //Hit escape to exit the game.
		if (key== KeyEvent.VK_SPACE){//Hit Space to access Shop.
			if (game.gameState==STATE.Game)game.gameState=STATE.Shop;
			else if (game.gameState==STATE.Shop)game.gameState=STATE.Game;
		} 
	}
	public void keyReleased(KeyEvent e){ // Stopping Player after Key is released
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){ // Loop to manage input.
			GameObject tempObject = handler.object.get(i); //Temporary object to store id.
			
			if (tempObject.getID()==ID.Player){//Key Inputs for Player 1.
				if(key == KeyEvent.VK_W){tempObject.setvelY(0);tempObject.falling=true;}
				if(key == KeyEvent.VK_A && tempObject.movingLeft==true){tempObject.setvelX(0);tempObject.movingLeft=false;}
				if(key == KeyEvent.VK_D && tempObject.movingRight==true){tempObject.setvelX(0);tempObject.movingRight=false;}
			}
		}
	}
	 

	
	}
