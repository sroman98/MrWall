import javax.sound.sampled.*;
import java.util.*;

public class SoundLoader implements Runnable {
	
	private ArrayList<String> soundList;
	private int currentSongIndex;
	private Thread musicthread; // for the animation
	private boolean running = false; // stops the animation
	
	public SoundLoader(String... files) {
		soundList = new ArrayList<String>();
		for(String file: files) {
			soundList.add(file);//.add("/src"+file+".wav");
		}
	}
	
	private void playSound(String filename) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(filename));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			
			clip.start();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		playSound(soundList.get(currentSongIndex));
	}
	
	
	public void startMusic() {// initialize and start the thread
		if (musicthread==null || !running) {
			musicthread = new Thread(this);
			musicthread.start();
		}
	} //end of startGame()
}
