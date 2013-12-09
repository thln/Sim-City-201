package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.Clip;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class RadioStation 
{
	AudioStream BGM;
	AudioStream raveMusic;
	AudioPlayer MGP = AudioPlayer.player;

	RadioStation()
	{
		initMusic();
	}
	
	private void initMusic() 
	{
		try {
			InputStream standardCityMusic = new FileInputStream("res/audio/Fur Elise.wav");
			BGM = new AudioStream(standardCityMusic);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Won't work1.");
			return;
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Won't work.");
			return;
		}

		try {
			InputStream raveCityMusic = new FileInputStream("res/audio/Scary Monster And Nice Sprites.wav");
			raveMusic = new AudioStream(raveCityMusic);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Won't work1.");
			return;
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Won't work.");
			return;
		}

		
//		if (BGM != null) {
//			BGM.loop(Clip.LOOP_CONTINUOUSLY);
//		}
	}
	
	public void StartBGM()
	{
        MGP.start(BGM);
	}
	
	
//		URL url = ("res/audio/goldenrod.wav");
//		URL fluteURL = this.getClass().getClassLoader().getResource("audio/pokeflute.wav");
//		URL recoveryURL = this.getClass().getClassLoader().getResource("audio/recovery.wav");
//		URL completedURL = this.getClass().getClassLoader().getResource("audio/item_get.wav");
//		URL messageToneURL = this.getClass().getClassLoader().getResource("audio/ping.wav");
// 
//		try {
//			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//			music = AudioSystem.getClip();
//			music.open(audioIn);
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//		try {
//			AudioInputStream pokeAudioIn = AudioSystem.getAudioInputStream(fluteURL);
//			pokeflute = AudioSystem.getClip();
//			pokeflute.open(pokeAudioIn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//		try {
//			AudioInputStream recoverAudioIn = AudioSystem.getAudioInputStream(recoveryURL);
//			recovery = AudioSystem.getClip();
//			recovery.open(recoverAudioIn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//		try {
//			AudioInputStream audioIn = AudioSystem.getAudioInputStream(completedURL);
//			completed = AudioSystem.getClip();
//			completed.open(audioIn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//		try {
//			AudioInputStream audioIn = AudioSystem.getAudioInputStream(messageToneURL);
//			messageTone = AudioSystem.getClip();
//			messageTone.open(audioIn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
// 
//		if (music != null) {
//			music.loop(Clip.LOOP_CONTINUOUSLY);
//		}
 
//	}
	

//	public void stopMusic() {
//		if (music.isRunning()) {
//			music.stop();
//		}
//	}
// 
//	@Override
//	public void startMusic() {
//		stopCompleted();
//		stopPokeflute();
//		stopRecovery();
// 
//		if (music != null) {
//			music.loop(Clip.LOOP_CONTINUOUSLY);
//		}
//	}
// 
//	@Override
//	public void startPokeflute() {
//		if (pokeflute != null) {
//			stopMusic();
//			stopCompleted();
//			stopRecovery();
// 
//			System.out.println("plays flute"); // !!! EXTREMELY IMPORTANT
//			pokeflute.setFramePosition(0);
//			pokeflute.start();
//			musicTimer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					startMusic();
//				}
//			}, 4224);
//		}
//	}
// 
//	public void stopPokeflute() {
//		if (pokeflute.isRunning()) {
//			pokeflute.stop();
//		}
//	}
// 
//	@Override
//	public void startRecovery() {
//		if (recovery != null) {
//			stopMusic();
//			stopPokeflute();
//			stopCompleted();
// 
//			System.out.println("plays recovery"); // !!! EXTREMELY IMPORTANT
//			recovery.setFramePosition(0);
//			recovery.start();
//			musicTimer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					startMusic();
//				}
//			}, 2000);
//		}
//	}
// 
//	public void stopRecovery() {
//		if (recovery.isRunning()) {
//			recovery.stop();
//		}
//	}
// 
//	public void startCompleted() {
//		if (completed != null) {
//			stopMusic();
//			stopPokeflute();
//			stopRecovery();
// 
//			System.out.println("plays completed"); // !!! EXTREMELY IMPORTANT
//			completed.setFramePosition(0);
//			completed.start();
//			musicTimer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					startMusic();
//					((ConveyorGraphicsDisplay) devices.get(Constants.CONVEYOR_TARGET)).setExit(true);
//				}
//			}, 2000);
//		}
//	}
// 
//	public void stopCompleted() {
//		if (completed.isRunning()) {
//			completed.stop();
//		}
//	}
}
