package com.dev.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//Abstract class for every game Object.
public abstract class GameObject {
	
	//Instance variables with protected are inherited by subclass.
	protected float x,y;
	protected ID id;
	protected float velX, velY;
	protected boolean falling;
	protected boolean jumping;
	protected boolean canJump;
	protected boolean movingRight;
	protected boolean movingLeft;
	
	// Initializes necessary variables.
	/*
	 * Every GameObject has an 
	 * @param x - x coordinate on screen
	 * @param y - y coordinate on screen
	 * @param id - An ID to represent itself.
	 */
	public GameObject(float x, float y, ID id)
	{ 
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
   /* Abstract methods must be implemented by child
	* So objects will refresh and be rendered on the screen.
	* Creates rectangle bounds. Not good with objects that arent rectangles.
	*/
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// Set and Get Methods to Return Game Object Data
	public void setX(float x){ 
		this.x=x;
	}
	public void setY(float  y){
		this.y=y;
	}
	public float  getX(){ 
		return x;
	}
	public float  getY(){
		return y;
	}
	public void setID(ID id){ // Set ID of object. Enemy? Player? Power up? etc.
		this.id=id;
	}
	public ID getID(){
		return id;
	}
	public void setvelX(float velX){ // Sets velocity of object
		this.velX=velX;
	}
	public void setvelY(float velY){ // Sets velocity of object
		this.velY=velY;
	}
	public float getvelX(){ 
		return velX;
	}
	public float getvelY(){
		return velY;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public boolean isJumping() {
		return jumping;
	}
	public boolean isFalling() {
		return falling;
	}

	
	
}

