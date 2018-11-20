import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemigo {

	private int x;
	private int y;
	private int width;
	private int height;
	private int vida;
	private ImageIcon currentimage;
	private Rectangle rectangulo;
	private ImageIcon emoveizq;
	private ImageIcon emoveder;
	private ImageIcon estillizq;
	private ImageIcon estillder;
	private ImageIcon einvisible;
	private boolean perfilder;
	private boolean perfilizq;
	private boolean juanitocercano;
	
	
	public Enemigo(int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		vida=2;
		rectangulo = new Rectangle(x+25,y,width-20,height);
		emoveder= new ImageIcon("img/emoveder.gif");
		emoveizq= new ImageIcon("img/emoveizq.gif");
		estillder= new ImageIcon("img/estillder.png");
		estillizq= new ImageIcon("img/estillizq.png");
		einvisible=new ImageIcon("img/juanitoinvisible");	
		currentimage=estillizq;
		juanitocercano=true;
		perfilizq=true;
		perfilder=true;
	}	
	
	public void move() {
		if(Juanito.getInstance().getX()+200 >= this.x) {
			perfilizq=true;
			setX(x-1);
		}
	}
	
	//Collision methods
	public boolean isColliding() {
		if(rectangulo.intersects(Juanito.getInstance().getRectangulo())) {
			return true;
		}
		return false;
	}
	
	public void updateImage() {
		if(Juanito.getInstance().getX()<this.getX()) {

		}
	}
	
	//Drawing method
	public void draw(Graphics g) {
		currentimage.paintIcon(null, g, this.x, this.y);
		g.drawRect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
	}
	
	//Getter & setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		rectangulo.setLocation(x+25, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		rectangulo.setLocation(x+25, y);
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

	public ImageIcon getCurrentImage() {
		return currentimage;
	}

	public void setCurrentImage(ImageIcon currentimage) {
		this.currentimage = currentimage;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}
	
	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}	
	
	public ImageIcon getEmoveizq() {
		return emoveizq;
	}

	public void setEmoveizq(ImageIcon emoveizq) {
		this.emoveizq = emoveizq;
	}

	public ImageIcon getEmoveder() {
		return emoveder;
	}

	public void setEmoveder(ImageIcon emoveder) {
		this.emoveder = emoveder;
	}

	public ImageIcon getEstillizq() {
		return estillizq;
	}

	public void setEstillizq(ImageIcon estillizq) {
		this.estillizq = estillizq;
	}

	public ImageIcon getEstillder() {
		return estillder;
	}

	public void setEstillder(ImageIcon estillder) {
		this.estillder = estillder;
	}
	
	public void setEinvisible(ImageIcon einvisible) {
		this.einvisible = einvisible;
	}

	public ImageIcon getEinvisible() {
		return einvisible;
	}
	
	public ImageIcon getCurrentimage() {
		return currentimage;
	}


	public void setCurrentimage(ImageIcon currentimage) {
		this.currentimage = currentimage;
	}


	public boolean isPerfilder() {
		return perfilder;
	}


	public void setPerfilder(boolean perfilder) {
		this.perfilder = perfilder;
	}


	public boolean isPerfilizq() {
		return perfilizq;
	}


	public void setPerfilizq(boolean perfilizq) {
		this.perfilizq = perfilizq;
	}


	public boolean isJuanitocercano() {
		return juanitocercano;
	}


	public void setJuanitocercano(boolean juanitocercano) {
		this.juanitocercano = juanitocercano;
	}
	
}
