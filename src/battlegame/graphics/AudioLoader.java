package battlegame.graphics;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import battlegame.Game;

public class AudioLoader {

	private static AudioInputStream in;
	private static Clip currentSong;
	
	public static AudioInputStream loadMusic(String filePath) {
        try {
        	if(currentSong != null)
        		currentSong.close();
			in = AudioSystem.getAudioInputStream(AudioLoader.class.getResourceAsStream(filePath));
			currentSong = AudioSystem.getClip();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public static void playMusic() {
        try {
			currentSong = AudioSystem.getClip();
			currentSong.open(in);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        currentSong.loop(100);
	}
	
	public static void stopMusic() {
		currentSong.stop();
		currentSong.close();
	}
	
}
