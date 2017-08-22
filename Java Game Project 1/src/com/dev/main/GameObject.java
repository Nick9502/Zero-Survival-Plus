package com.dev.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//Abstract class for every object in the game.
public abstract class GameObject {
	
	protected float x,y; //Protected is inherited by class.
	protected ID id;
	protected float velX, velY;
	protected boolean falling;
	protected boolean jumping;
	protected boolean canJump;
	protected boolean movingRight;
	protected boolean movingLeft;
	
	public GameObject(float x, float y, ID id){ //Sets Parameters to the protected variables.
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	//So objects will refresh and be rendered on the screen.
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();// Creates rectangle bounds. Not good with objects that arent rectangles.
	
	//Setters and Getters for Game Objects.	
	public void setX(float x){ // Used to change location of objects positions in game.
		this.x=x;
	}
	public void setY(float  y){ // Used to change location of objects positions in game.
		this.y=y;
	}
	public float  getX(){ 
		return x;
	}
	public float  getY(){
		return y;
	}
	public void setID(ID id){//Set ID of object. Enemy? Player? Power up? etc.
		this.id=id;
	}
	public ID getID(){
		return id;
	}
	public void setvelX(float velX){ //Sets velocity of object
		this.velX=velX;
	}
	public void setvelY(float velY){ //Sets velocity of object
		this.velY=velY;
	}
	public float  getvelX(){ 
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

