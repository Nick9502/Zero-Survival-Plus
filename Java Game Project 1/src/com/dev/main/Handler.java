package com.dev.main;

import java.awt.Graphics;
import java.util.LinkedList;

/* 
 * Loops through and individually updates and renders game objects in the room.
 * Handles all GameObjects 
 */
public class Handler {
	public int spd=5; //Speed of the Player used for Upgrading.
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void Damage()
	{
		for (int i = 0; i <object.size();i++)
		{
		GameObject tempObject = object.get(i);
		if (tempObject.getID() == ID.BasicEnemy){HUD.HEALTH-=5;}
		else if (tempObject.getID() == ID.SmartEnemy){HUD.HEALTH-=14;}
		else if (tempObject.getID() == ID.FastEnemy){HUD.HEALTH-=10;}
		else if (tempObject.getID() == ID.EnemyBullet){HUD.HEALTH-=10;}
		}
	}
	
	// Loops through every game object on list and updates/refreshes.
	public void tick()
	{ 
		for (int i = 0; i <object.size();i++){
			GameObject tempObject =object.get(i);
			tempObject.tick();
		}
	}
	
	// Loops through every game object on list and updates/renders.
	public void render(Graphics g)
	{ 
		for (int i = 0; i <object.size();i++)
		{
			GameObject tempObject =object.get(i);
			tempObject.render(g);
		}
	}
	
	// Used to clear all enemies off stage.
	public void clearAllEnemy()
	{ 
		for (int i = 0; i <object.size();i++)
		{
			GameObject tempObject =object.get(i);
			if (tempObject.getID()==ID.BasicEnemy ||tempObject.getID()==ID.FastEnemy ||tempObject.getID()==ID.SmartEnemy
					||tempObject.getID()==ID.MenuParticle||tempObject.getID()==ID.BossEnemy) {
				RemoveObject(tempObject);
				i--; //list size decrease, cycling through the enemies
			}
		}
	}
	
	// Used to clear all Objects off the stage
	public void clearAllObjects()
	{ 
		for (int i = 0; i <object.size();i++)
		{
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
	

	
	// Adds Objects to the list.
	public void AddObject(GameObject object)
	{ 
		this.object.add(object);
	}
	
	// Removes Objects from the list.
	public void RemoveObject(GameObject object)
	{ 
		this.object.remove(object);
	}
	
	}
