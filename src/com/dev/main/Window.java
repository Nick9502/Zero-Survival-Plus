package com.dev.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{ //Creates Window for viewing. Extends canvas so user can trap inputs and drawings.
	private static final long serialVersionUID = -4379797028302318486L;
	
	public Window(int width, int height, String title, Game game) // Create Window in constructor.
	{
		JFrame frame= new JFrame(title); //Create JFrame Object. Frame of Window
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X button in Corner works.
		frame.setResizable(false); //Cant Resize window.
		frame.setLocationRelativeTo(null );// Window starts in middle of screen. Not top left.
		frame.add(game); //Adds game class to screen.
		frame.setVisible(true);//You can see
		game.start();//Running start method
	}

}
