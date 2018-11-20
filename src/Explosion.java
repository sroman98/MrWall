import java.awt.Graphics;
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
		ImageIcon ex1= new ImageIcon("img/ex1.png");
		frames.add(ex1);
		currentframe=ex1;
		ImageIcon ex2= new ImageIcon("img/ex2.png");
		frames.add(ex2);
		ImageIcon ex3= new ImageIcon("img/ex3.png");
		frames.add(ex3);
		ImageIcon ex4= new ImageIcon("img/ex4.png");
		frames.add(ex4);
		ImageIcon ex5= new ImageIcon("img/ex5.png");
		frames.add(ex5);
		ImageIcon ex6= new ImageIcon("img/ex6.png");
		frames.add(ex6);
		ImageIcon ex7= new ImageIcon("img/ex7.png");
		frames.add(ex7);
		ImageIcon ex8= new ImageIcon("img/ex8.png");
		frames.add(ex8);
		ImageIcon ex9= new ImageIcon("img/ex9.png");
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
	
	public void setY(int x) {
		this.y=y;
	}
	
	public int getY() {
		return y;
	}
	
}
