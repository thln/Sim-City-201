package application;


import java.io.FileInputStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class RadioStation 
{
	//AudioStream BGM;
	//AudioStream raveMusic;
	//AudioPlayer MGP = AudioPlayer.player;
	Clip standardCityClip;
	Clip raveCityClip;
	Timer musicTimer = new Timer();

	RadioStation()
	{
		initMusic();
	}
	
	private void initMusic() 
	{
		//URL testURL = this.getClass().getClassLoader().getResource("res/audio/raveAudioMode.wav");
		//URL standardCityURL = this.getClass().getClassLoader().getResource("res/audio/FurElise.wav");
		//URL raveCityURL = this.getClass().getClassLoader().getResource("res/audio/ScaryMonstersAndNiceSprites.wav");
		URL testURL = this.getClass().getClassLoader().getResource("src/res/audio/raveAudioMode.wav");
		URL standardCityURL = this.getClass().getClassLoader().getResource("res/audio/FurElise.wav");
		URL raveCityURL = this.getClass().getClassLoader().getResource("res/audio/ScaryMonstersAndNiceSprites.wav");
		//System.out.println(standardCityURL.getFile() + " " + raveCityURL.getFile() + " " + testURL.getFile());
		//testURL.
		try 
		{
			//AudioInputStream standardCityIn = new AudioInputStream(new FileInputStream("res/audio/FurElise.wav"));
			AudioInputStream standardCityIn = AudioSystem.getAudioInputStream(testURL);
			System.out.println("1");
			//AudioInputStream standardCityIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("res/audio/FurElise.wav"));
			System.out.println("2");
			standardCityClip = AudioSystem.getClip();
			System.out.println("3");
			standardCityClip.open(standardCityIn);
			System.out.println("4");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
 
		try 
		{
			AudioInputStream raveCityIn = AudioSystem.getAudioInputStream(raveCityURL);
			//AudioInputStream raveCityIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("res/audio/ScaryMonstersAndNiceSprites.wav"));
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
		
		System.out.println("1111111111111111111111111111111111111111111111111111");
	}
	
	public void stopBGMusic() 
	{
		if (standardCityClip.isRunning()) 
		{
			standardCityClip.stop();
		}
	}
	
	//@Override
	public void startBGMusic() 
	{
		//stopCompleted();
		//stopPokeflute();
		//stopRecovery();
		stopRaveMusic();
		
		if (standardCityClip != null) 
		{
			standardCityClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void startRaveMusic() 
	{
		if (raveCityClip != null) 
		{
			stopBGMusic();
			//stopCompleted();
			//stopRecovery();
 
			System.out.println("RAVE"); // !!! EXTREMELY IMPORTANT
			raveCityClip.setFramePosition(0);
			raveCityClip.start();
			musicTimer.schedule(new TimerTask() 
			{
				@Override
				public void run() 
				{
					startBGMusic();
				}
			}, 4224);
		}
	}
 
	public void stopRaveMusic() 
	{
		if (raveCityClip.isRunning()) 
		{
			raveCityClip.stop();
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
	
	