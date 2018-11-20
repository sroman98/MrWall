import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;

import javax.swing.ImageIcon;

public class Chancla extends Observable {
	private float dt;
	private boolean right;
	
	private int x;
	private int y;
	private int velx;
	private int width;
	private int height;
	private String path;
	private ImageIcon image;
	private Rectangle rectangulo;
	
	public Chancla(){
		dt = (float) 0.666;
		right = false;
		
		x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
		y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2);
		setVelx(0);
		width = 20;
		height = 20;
		path = "img/chancla.png";
		image = new ImageIcon(path);
		rectangulo = new Rectangle(x,y,width,height);
	}
	
	public void throwChancla() {
		if(right)
			throwChanclaRight();
		else
			throwChanclaLeft();
		
		rectangulo.setLocation(x, y);
		if(this.hasChanged())
			notifyObservers(this);
	}
	
	public void throwChanclaRight() {
		if(x >  Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2)|| velx > 0) {
			this.setChanged();
			x = (int)(x + velx*dt);
			velx = (int)(velx + (-3)*dt);
		}
		else {
			velx = 0;
			x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
			y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2);
		}
		
	}
	public void throwChanclaLeft() {
		if(x <  Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2)|| velx < 0) {
			this.setChanged();
			x = (int)(x + velx*dt);
			velx = (int)(velx + 3*dt);
		}
		else {
			velx = 0;
			x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
			y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2);
		}
	}
	
	public void draw(Graphics g) {
		image.paintIcon(null, g, this.x, this.y);
		g.drawRect(x, y, width, height);
	}
	
	//Setters & getters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		if(this.velx == 0)
			this.velx = velx;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

	public float getDt() {
		return dt;
	}

	public void setDt(float dt) {
		this.dt = dt;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}	
}
