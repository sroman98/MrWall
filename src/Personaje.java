import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Personaje {
	//Variables
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int vida;
	protected int velx;
	protected int vely;
	protected String path;
	protected ImageIcon mygif;
	
	//Constructor
	public Personaje(int x, int y, int w, int h, int vida, int velx, int vely, String path) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.vida = vida;
		this.velx = velx;
		this.vely = vely;
		this.path = path;
		mygif = new ImageIcon(path);
	}
	
	//Setters & getters
	public int getX() {
		return x;
	}
	 public void setX(int n) {
		 this.x += n;
	 }
	public int getY() {
		return y;
	}
	public void setY(int n) {
		 this.y += n;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getVelx() {
		return velx;
	}
	public void setVelx(int velx) {
		this.velx = velx;
	}
	public int getVely() {
		return vely;
	}
	public void setVely(int vely) {
		this.vely = vely;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	//methods
	public abstract void move();

	public abstract void stop();

	public abstract void hurt();
	public abstract void heal();

	public abstract void die();
	
	public abstract void draw(Graphics g);
	
}
