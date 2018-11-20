import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemigo implements Runnable{

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
	private Thread enemythread; // for the animation
	private boolean running=false;
	
	
	public Enemigo(int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		vida=3;
		rectangulo = new Rectangle(x,y,width,height);
		emoveder= new ImageIcon("img/emoveder.gif");
		emoveizq= new ImageIcon("img/emoveizq.gif");
		estillder= new ImageIcon("img/estillder.png");
		estillizq= new ImageIcon("img/estillizq.png");
		einvisible=new ImageIcon("img/juanitoinvisible");	
		currentimage=estillizq;
		juanitocercano=true;
		perfilizq=true;
		perfilder=true;
		startEnemigo();
		running =true;
	}	
	
	
	public void startEnemigo() {// initialize and start the thread
		if (enemythread==null || !running) {
			enemythread = new Thread(this);
			enemythread.start();
		}
	} //end of startGame()
	
	public void move() {
		if(Juanito.getInstance().getX()+200>=this.getX()) {
			perfilizq=true;
			this.setX(this.getX()-1);
			System.out.println("THERE IS A JUANITO AT THE DISTANCE");
		}
	}
	
	//Collision methods
	public boolean isColliding() {
		if(rectangulo.intersects(Juanito.getInstance().getRectangulo())) {
			return true;
		}
		return false;
	}
	
	public void setCollisions() {
        if(Juanito.getInstance().getRectangulo().getX() <= x+width && Juanito.getInstance().getRectangulo().getX() >= x)
    		Juanito.getInstance().setCl(true);
        if(Juanito.getInstance().getRectangulo().getX()+Juanito.getInstance().getRectangulo().getWidth() >= x && Juanito.getInstance().getRectangulo().getX()+Juanito.getInstance().getRectangulo().getWidth() <= x+width)
    		Juanito.getInstance().setCr(true);
        if(Juanito.getInstance().getRectangulo().getY()+Juanito.getInstance().getRectangulo().getHeight() >= y && Juanito.getInstance().getRectangulo().getY()+Juanito.getInstance().getRectangulo().getHeight() <= y+height) {
    		Juanito.getInstance().setCd(true); 
    		Juanito.getInstance().setCl(false);
    		Juanito.getInstance().setCr(false);
        }
	}
	
	
	//Drawing method
	public void draw(Graphics g) {
		currentimage.paintIcon(null, g, this.x, this.y);
		g.drawRect(x, y, width, height);
	}
	
	//Getter & setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		rectangulo.setLocation(this.x, this.y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		rectangulo.setLocation(x, y);
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
	
	public void updateImage() {
		if(Juanito.getInstance().getX()<this.getX()) {

		}
	}

	@Override
	public void run() {
		while(running) {
			try {
				enemythread.sleep(20);
				move();
				updateImage();
			}catch(InterruptedException ex){}
			
		}
	}
	
}
