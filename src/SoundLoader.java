import javax.sound.sampled.*;
import java.util.*;

public class SoundLoader implements Runnable {
	
	private ArrayList<String> soundList;
	private int currentSongIndex;
	private Thread musicthread; // for the animation
	private boolean running = false; // stops the animation
	private static SoundLoader instance;
	
	public static synchronized SoundLoader getInstance() {
	    if(instance == null) {
	      instance = new SoundLoader("/mariachi.wav", "/daño2.wav", "/throw.wav", "/jump.wav");
	    }
	    return instance;
	}
	
	public SoundLoader(String... files) {
		soundList = new ArrayList<String>();
		for(String file: files) {
			soundList.add(file);//.add("/src"+file+".wav");
		}
		running=true;
	}
	
	public void playSound(String filename) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(filename));
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			if(filename == "/mariachi.wav")
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-2);
			
			clip.start();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		boolean flagplay0=true;
		while(running) {
			
			if(flagplay0) {
				playSound(soundList.get(currentSongIndex));
				flagplay0=false;
			}
			
			if(currentSongIndex==1) {
				playSound(soundList.get(currentSongIndex));
			}
		}
	}
	
	public void setCurrentIndex(int i){
		this.currentSongIndex=i;
	}
	
	public void startMusic() {// initialize and start the thread
		if (musicthread==null || !running) {
			musicthread = new Thread(this);
			musicthread.start();
		}
	} //end of startGame()
}
