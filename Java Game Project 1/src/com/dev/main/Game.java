package com.dev.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{ // Runnable allows game.start() thread to work.
	
	private static final long serialVersionUID = 2634874163305979749L;
	
	public static final int WIDTH=640, HEIGHT = WIDTH/12 *9; //640 X 480. 16:9 Ratio
	
	private Thread thread; //Entire Game will be Run in thread. Single thread. (Not Recommended)
	private Boolean running =false; // Game is not Started..Yet
	
	// Game Settings
	public static boolean paused = false;
	public int diff=0; // 0 - Normal, 1 - Extreme
	
	// Declare all necessary objects for game to run.
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	private Random r;
	
	// List of Game States and Windows
	public enum STATE{
		Menu,
		Select,
		Shop,
		Game,
		Help,
		Quit,
		GameOver,
	};
	
	// Start at Menu
	public STATE gameState = STATE.Menu;
	
	// Call from this one same sprite sheet. Hence static.
	public static BufferedImage sprite_sheet; 
	
	/*
	 * IMPORTANT
	 * Game constructor initializes all necessary game objects
	 * imports setups and effects
	 */
	public Game()
	{
		// Load Sprite Sheet
		BufferedImageLoader loader = new BufferedImageLoader(); // Creates instance of loader object in Game class.
		sprite_sheet= loader.loadImage("/Sprite_Sheet.png"); // Load Sprite sheet into game
		System.out.println("Loaded Sprite Sheet");
		
		// Initialize all needed game objects.
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler,hud,this);
		menu = new Menu(this,handler,hud);
		spawner=new Spawn(handler,hud,this);
		
		// Add KeyInput and Mouse Listeners to Game.
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		// Load Music using AudioPlayer
		AudioPlayer.load();
		AudioPlayer.getMusic("Music").loop();
		
		// Create Window and Display Menu Particles
		new Window(WIDTH, HEIGHT, "Zero Survival+",this);
		createParticle();

	}
	//Stops objects from exceeding boundaries. "Clamps" them down.
	public static float clamp(float var,float min, float max)
	{ 
		if(var > max) return max; //If X exceeds max. X stops at max
		else if(var < min) return min;//If Y exceeds max. X stops at max
		return var; //Return value of coordinate.
	}
	
	// Create Menu Particle Effect on Screen
	public void createParticle()
	{
		// Create Menu Particle
		r = new Random();
		
		if (gameState!= STATE.Game)
		{
			for (int i=0;i<20;i++)
			{
				handler.AddObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
			}
		}
	}
	// Runs Game Thread
	public synchronized void start()
	{
		thread = new Thread(this);// Initializes thread as new. "this" refers to our game instance.
		thread.start(); //Start method for thread objects.
		running = true;
	}
	
	// Try and Catch to test if program stopped.
	public synchronized void stop(){ 
		try{ 
			thread.join(); // Stops thread.
			running = false;
		}catch(Exception e){// If program not running. tell us why.
			e.printStackTrace();
		}
		
	}
	
	/*
    Very Popular Game Loop. Refreshes and Updates Game.
    Part of Runnable Interface
	We need a loop that performs 2 things: it checks whether enough time has passed (1/60 sec) 
	to refresh the game, and checks whether enough time has passed (1 sec) to refresh the FPS counter;
	while 'running' it adds the time it took to go through one iteration of the loop it self and 
	adds it to delta (which is simplified to 1) so once it reaches 1 delta it means enough time has passed to go forward one tick
	 */
	public void run() {
		this.requestFocus();//Dont have to click on screen for keyboard input to work.
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
                frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
           
        }
        stop();
    }
	/* 
	 * Checks which state game is currently in and displays 
	 * appropriate output
	 */
	private void tick()
	{
		if (gameState == STATE.Game)
		{
			if (!paused){
				hud.tick(); //HUD is not Game Object. Needs to update separately.//Start HUD
				spawner.tick(); //Start the spawner
				handler.tick();
				
				if (HUD.HEALTH<=0){
					HUD.HEALTH=100;
					handler.clearAllObjects();
					gameState=STATE.GameOver;
					for (int i=0;i<20;i++){
						handler.AddObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
					}
				}}
			
		}
		else if (gameState == STATE.Shop)hud.update();
		else if (gameState == STATE.Menu|| gameState == STATE.Help||gameState == STATE.GameOver|| gameState == STATE.Select)
		{
			handler.tick();
			menu.tick(); //Start the menu
		}
	}
	
	//Render Objects to the Game.
	private void render()
	{ 
		// Efficient buffering strategy
		BufferStrategy bs = this.getBufferStrategy(); // Starts at null
		if(bs == null)
		{
			this.createBufferStrategy(3); // 3 is an optimal performance 
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
				
		if (paused)
		{
			g.setColor(Color.white);
			g.drawString("Paused", 120, 84);
		}
		
		if (gameState == STATE.Game)
		{
		hud.render(g); // HUD is not Game Object. Needs to render separately.
		handler.render(g);
		}
		
		else if (gameState ==STATE.Shop)
		{
			shop.render(g); // Display Shop
		}
		
		else if (gameState == STATE.Menu || gameState ==STATE.Help ||gameState == STATE.GameOver|| gameState == STATE.Select)
		{
			menu.render(g); // Display Menu
			handler.render(g); // Render Handler to Display new Game Object Updates
		}
		g.dispose();
		bs.show(); // Shows the buffer.
		
	}
	
}
