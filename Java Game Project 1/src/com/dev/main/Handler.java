package com.dev.main;

import java.awt.Graphics;
import java.util.LinkedList;

//Loops through and individually updates and renders game objects in the room.

public class Handler {
	public int spd=5; //Speed of the Player.
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){ //Loops through every game object on list and updates/refreshes.
		for (int i = 0; i <object.size();i++){
			GameObject tempObject =object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g){ //Loops through every game object on list and updates/renders.
		for (int i = 0; i <object.size();i++){
			GameObject tempObject =object.get(i);
			tempObject.render(g);
	}
	}
	public void clearAllEnemy(){ // Used to clear all enemies off stage.
		for (int i = 0; i <object.size();i++){
			GameObject tempObject =object.get(i);
			if (tempObject.getID()==ID.BasicEnemy ||tempObject.getID()==ID.FastEnemy ||tempObject.getID()==ID.SmartEnemy
					||tempObject.getID()==ID.MenuParticle||tempObject.getID()==ID.BossEnemy) {
				RemoveObject(tempObject);
				i--; //list size decrease, cycling through the enemies
			}
	}
	}
	public void clearAllObjects(){ // Used to clear all enemies off stage.
		for (int i = 0; i <object.size();i++){
			GameObject tempObject =object.get(i);
			if (tempObject.getID()==ID.Player || tempObject.getID()==ID.Block || tempObject.getID()==ID.BasicEnemy 
					||tempObject.getID()==ID.FastEnemy ||tempObject.getID()==ID.SmartEnemy 
					||tempObject.getID()==ID.MenuParticle ||tempObject.getID()==ID.BossEnemy 
					||tempObject.getID()==ID.EnemyBullet) {
				RemoveObject(tempObject);
				i--; //list size decrease, cycling through the enemies
			}
	}
	}
	public void Damage(){
		for (int i = 0; i <object.size();i++){
		GameObject tempObject = object.get(i);
		if (tempObject.getID() == ID.BasicEnemy){HUD.HEALTH-=5;}
		else if (tempObject.getID() == ID.SmartEnemy){HUD.HEALTH-=14;}
		else if (tempObject.getID() == ID.FastEnemy){HUD.HEALTH-=10;}
		else if (tempObject.getID() == ID.EnemyBullet){HUD.HEALTH-=10;}
		}
	}
	
	public void AddObject(GameObject object){ //Adds Objects to the list.
		this.object.add(object);
	}
	public void RemoveObject(GameObject object){ //Removes Objects from the list.
		this.object.remove(object);
	}
	
	}
