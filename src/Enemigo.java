import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;

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
		vida = 3;
		rectangulo = new Rectangle(x+25,y,width-20,height);
		emoveder= createImageIcon("/emoveder.gif");
		emoveizq= createImageIcon("/emoveizq.gif");
		estillder= createImageIcon("/estillder.png");
		estillizq= createImageIcon("/estillizq.png");
		einvisible=createImageIcon("/invisiblejuanito.png");
		
		currentimage=estillizq;
		juanitocercano=false;
		perfilizq=true;
		perfilder=false;
	}	
	
	public void move() {
		if(Juanito.getInstance().getX()>=this.x-200) { // si Juanito está a la izquierda del enemigo
			
		}
		
		if(Juanito.getInstance().getX()>this.getX()){ // Si Juanito está a derecha del enemigo
			perfilizq=false;
			perfilder=true; 
			currentimage=estillder;
			if( (this.getX()+600 > Juanito.getInstance().getX()) && (Juanito.getInstance().getX() > this.getX())) {
				setX(x+2);
				currentimage=emoveder;
			}
		}
		else {
			perfilizq=true;
			perfilder=false;
			currentimage=estillizq;
			if( (this.getX()-600 < Juanito.getInstance().getX()) && (Juanito.getInstance().getX() < this.getX())) {
				setX(x-2);
				currentimage=emoveizq;
			}
		}

		
			
	}
	
	public boolean atrapoJuanito() {
		if(rectangulo.getCenterX()-30<=Juanito.getInstance().getRectangulo().getCenterX()+30) {
			Juanito.getInstance().setVida(Juanito.getInstance().getVida()-1);
			return true;
		}
		if(rectangulo.getCenterX()+30<=Juanito.getInstance().getRectangulo().getCenterX()-30) {
			Juanito.getInstance().setVida(Juanito.getInstance().getVida()-1);
			return true;
		}
		return false;
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
		//g.drawRect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);	
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
	
	public void die() {
		ImageIcon edieizq= createImageIcon("/edieizq.png");
		setCurrentImage(edieizq);
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
