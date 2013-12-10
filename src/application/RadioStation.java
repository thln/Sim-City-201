package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
//import javax.print.DocFlavor.URL;
import javax.sound.sampled.Clip;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class RadioStation 
{
	AudioStream BGM;
	AudioStream raveMusic;
	AudioPlayer MGP = AudioPlayer.player;
	Clip standardCityClip;
	Clip raveCityClip;

	RadioStation()
	{
		initMusic();
	}
	
	private void initMusic() 
	{
		URL standardCityURL = this.getClass().getClassLoader().getResource("res/audio/Fur Elise.wav");
		URL raveCityURL = this.getClass().getClassLoader().getResource("res/audio/Fur Elise.wav");
		
		try 
		{
			AudioInputStream standardCityIn = AudioSystem.getAudioInputStream(standardCityURL);
			standardCityClip = AudioSystem.getClip();
			standardCityClip.open(standardCityIn);
 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
 
		try 
		{
			AudioInputStream raveCityIn = AudioSystem.getAudioInputStream(raveCityURL);
			raveCityClip = AudioSystem.getClip();
			raveCityClip.open(raveCityIn);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (standardCityClip != null) 
		{
			standardCityClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stopMusic() 
	{
		if (standardCityClip.isRunning()) 
		{
			standardCityClip.stop();
		}
	}
		
//		try {
//			InputStream standardCityMusic = new FileInputStream("res/audio/Fur Elise.wav");
//			BGM = new AudioStream(standardCityMusic);
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("Won't work1.");
//			return;
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Won't work.");
//			return;
//		}
//
//		try {
//			InputStream raveCityMusic = new FileInputStream("res/audio/Scary Monster And Nice Sprites.wav");
//			raveMusic = new AudioStream(raveCityMusic);
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("Won't work1.");
//			return;
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Won't work.");
//			return;
//		}

		
//		if (BGM != null) {
//			BGM.loop(Clip.LOOP_CONTINUOUSLY);
//		}
	}
	
//	public void StartBGM()
//	{
//        MGP.start(BGM);
//	}
	
	