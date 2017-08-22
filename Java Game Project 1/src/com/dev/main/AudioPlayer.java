package com.dev.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
	//Loads music and sound into the Maps
	public static void load(){ //Static so we can call AudioPlayer straight from class one time. -> AudioPlayer.Load()
		try {
			soundMap.put("Click Sound", new Sound("res/click_sound.wav"));
			
			musicMap.put("Music", new Music("res/ParagonX9_ChaozFantasy.wav"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Gets Music and Sounds from the map.
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
}
