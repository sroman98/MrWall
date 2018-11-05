import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Obstaculo {
	private int x;
	private int y;
	private int width;
	private int height;
	private int vida;
	private String path;
	protected ImageIcon image;
	protected Rectangle rectangulo;
	
	Obstaculo(int x, int y, int width, int height, String path){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		vida=1;
		this.path=path;
		image = new ImageIcon(path);
		rectangulo = new Rectangle();
	}	
	public void setX(int x){this.x=x;}
	public int getX() {return this.x;}
	public void setY(int y) {this.y=y;}
	public int getY() {return this.y;}
	public void setWidth(int w) {this.width=w;}
	public int getWidth() {return this.width;}
	public void setHieght(int h) {this.height=h;}
	public int getHeight() {return this.height;}
	public void setVida(int vida) {this.vida=vida;}
	public int getVida() {return this.vida;}
	public void setPath(String path) {this.path=path;}
	public String getPath() {return this.path;}
	public void draw(Graphics g) {
		image.paintIcon(null, g, this.x, this.y);
		g.drawRect(x, y, width, height);
	}
}