package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer 
{
	
	// Use HashMap to store a key(audio name) and its corresponding sound(value).
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
	/*
	 * Loads music and sound into the Maps
	 * Static so we can call AudioPlayer straight from class one time. -> AudioPlayer.Load()
	 */ 
	public static void load()
	{ 
		try {
			soundMap.put("Click Sound", new Sound("res/click_sound.wav"));
			
			musicMap.put("Music", new Music("res/music_bg.wav"));
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
