import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Wall implements Observer {
	//Singleton
	private static Wall instance;
	public static synchronized Wall getInstance() {
	    if(instance == null) {
	      instance = new Wall();
	    }
	    return instance;
	}
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int vida;
	private ImageIcon wall;
	private Rectangle rectangulo;

	public Wall() {
		width=260;
		height=300;
		x=7320-width;
		y=688-height;
		vida=5;
		rectangulo = new Rectangle(x,y,width,height);
		
		wall = new ImageIcon("img/wall.png");
		
		Juanito.getInstance().getMychancla().addObserver(this);
	}
	
	//Drawing method
		public void draw(Graphics g) {
			wall.paintIcon(null, g, x, y);
			//g.drawRect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
		}
		
		@Override
		public void update(Observable o, Object arg) {
			if(o == Juanito.getInstance().getMychancla()) {
				if(Juanito.getInstance().getMychancla().colliding(rectangulo)){
					vida--;
					y+=78;
					if(vida==0);
						//Something happens, you win
				}
			}
		}
		public void avanzar(int n) {
			this.setX(x+n);	
		}
	
	//Getter & setters
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

	public ImageIcon getWall() {
		return wall;
	}

	public void setWall(ImageIcon wall) {
		this.wall = wall;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

	
}
