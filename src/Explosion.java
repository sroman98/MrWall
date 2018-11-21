import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Explosion {
	
	private int x;
	private int y;
	private ArrayList<ImageIcon> frames;
	private ImageIcon currentframe;

	private static Explosion instance;
	
	public static synchronized Explosion getInstance() {
	    if(instance == null) {
	      instance = new Explosion(0, 0);
	    }
	    return instance;
	}
	
	public Explosion(int x, int y) {
		this.x=x;
		this.y=y;
		frames = new ArrayList<ImageIcon>();
		ImageIcon ex1= createImageIcon("/ex1.png");
		frames.add(ex1);
		currentframe=ex1;
		ImageIcon ex2= createImageIcon("/ex2.png");
		frames.add(ex2);
		ImageIcon ex3= createImageIcon("/ex3.png");
		frames.add(ex3);
		ImageIcon ex4= createImageIcon("/ex4.png");
		frames.add(ex4);
		ImageIcon ex5= createImageIcon("/ex5.png");
		frames.add(ex5);
		ImageIcon ex6= createImageIcon("/ex6.png");
		frames.add(ex6);
		ImageIcon ex7= createImageIcon("/ex7.png");
		frames.add(ex7);
		ImageIcon ex8= createImageIcon("/ex8.png");
		frames.add(ex8);
		ImageIcon ex9= createImageIcon("/ex9.png");
		frames.add(ex9);
	}
	
	public void draw(Graphics g) {
		int i=0;
		do {
		currentframe=frames.get(i);
		try {
			Thread.sleep(1); // nano -> ms
		}catch(InterruptedException ex){}
		currentframe.paintIcon(null,g, Juanito.getInstance().getMychancla().getX(), Juanito.getInstance().getMychancla().getY());
		i++;
		}while(i<frames.size());
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public int getY() {
		return y;
	}
	
	//JAR adapted imageicon creator
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
