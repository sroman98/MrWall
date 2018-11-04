import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Obstaculo {
	private int x;
	private int y;
	private int tamano;
	private int vida;
	private String path;
	protected ImageIcon image;
	
	Obstaculo(int x, int y, int tamano, String path){
		this.x=x;
		this.y=y;
		this.tamano=tamano;
		vida=1;
		this.path=path;
		image = new ImageIcon(path);
	}	
	public void setX(int x){this.x=x;}
	public int getX() {return this.x;}
	public void setY(int y) {this.y=y;}
	public int getY() {return this.y;}
	public void setTamano(int tamano) {this.tamano=tamano;}
	public int getTamano() {return this.tamano;}
	public void setVida(int vida) {this.vida=vida;}
	public int getVida() {return this.vida;}
	public void setPath(String path) {this.path=path;}
	public String getPath() {return this.path;}
	public void paint(Graphics g) {image.paintIcon(null, g, this.x, this.y);}
}