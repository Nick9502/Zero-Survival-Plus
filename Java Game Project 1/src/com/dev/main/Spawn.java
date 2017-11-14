package com.dev.main;

import java.util.Random;

/*
 * Spawn class handles creation of enemies
 */
public class Spawn {

	// Declare Needed Objects
	private Handler handler;
	private HUD hud;
	Game game;
	
	// Random Enemy Location
	private Random r = new Random();
	
	// Score determines when next wave starts
	private int scoreKeep = 0;

	// Intialize needed object variables
	public Spawn(Handler handler, HUD hud, Game game) {
														
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	/*
	 * Tick method updates enemy waves and increases level
	 * the higher the players score becomes.
	 * 2 Difficulties. Normal and Extreme Difficulties
	 */
	public void tick() {
		// ----------------------------------Normal Difficulty-------------------------------------------- 
		if (game.diff == 0) {
			scoreKeep++;// Same value as score.
			if (hud.getLevel()==1 && hud.getScore()==75) {
				handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
						ID.BasicEnemy, handler));
			}
			if (scoreKeep >= 250) {// Every 250 points you reach the next level.
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				  if (hud.getLevel() == 2) {
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.AddObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 8) {
					handler.AddObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 9) {
					handler.AddObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearAllEnemy();
					handler.AddObject(new BossEnemy((Game.WIDTH / 2) - 32, -100, ID.BossEnemy, handler));
				}
			}
		}

		// ----------------------------------Extreme Difficulty-------------------------------------------- 
		else if (game.diff == 1) {
			scoreKeep++;// Same value as score.
			
			if (hud.getLevel()==1 && hud.getScore()==75) {
				handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
						ID.HardEnemy, handler));
			}
			if (scoreKeep >= 200) {// Every 200 points you reach the next level.
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);

				  if (hud.getLevel() == 2) {
					handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
					handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.AddObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.AddObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (hud.getLevel() == 8) {
					handler.AddObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (hud.getLevel() == 9) {
					handler.AddObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearAllEnemy();
					handler.AddObject(new BossEnemy((Game.WIDTH / 2) - 32, -100, ID.BossEnemy, handler));
				}
			}
		}
	}
}
