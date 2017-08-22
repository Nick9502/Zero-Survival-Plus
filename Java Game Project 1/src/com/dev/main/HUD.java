package com.dev.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public int bounds = 0;
	public static float HEALTH = 100;
	private float greenVal=255f;
	
	private int score = 0;
	private int level = 1;
	
	public void tick(){
		HEALTH=Game.clamp(HEALTH, 0, 100+(bounds/2));//Clamps health. Between 0-100. (Bounds/2)-Line 25 (*2)
		greenVal=(int) Game.clamp(greenVal, 0, 255);
		greenVal=(int) (HEALTH*2);
		score++;
	}
	public void render(Graphics g){
		g.setColor(Color.gray);//Background of health bar
		g.fillRect(15, 15, 200+bounds, 32);
		g.setColor(new Color(75,(int)greenVal,0));//Represents health amount.
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		g.setColor(Color.WHITE);//White border
		g.drawRect(15, 15, 200+bounds, 32);
		
		g.drawString("Level: " + level, 15, 64);
		g.drawString("Score: " + score, 75, 64);
		g.drawString("Space for Shop", 15, 84);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void update(){
		greenVal=(int) Game.clamp(greenVal, 0, 255);
		//greenVal=(int) (HEALTH*2);

	}
}
