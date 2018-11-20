import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;

public class Chancla extends Observable {
	private float dt;
	private boolean right;
	
	private int x;
	private int y;
	private int velx;
	private int width;
	private int height;
	private String path;
	private Sprite chanclasprite;
	private Sprite invisiblechancla;
	private Sprite current;

	private Explosion explosion;
	private boolean collision=false;

	
	public Chancla(){
		dt = (float) 0.666;
		right = false;
		
		x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
		y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2)-6;
		setVelx(0);
		width = 20;
		height = 20;
		
		chanclasprite = new Sprite();
		chanclasprite.load("/chanclasheet.png", 3, 4, 38, 37);
		chanclasprite.position = new Point(x,y);
	    chanclasprite.frameDelay = 0;
	    chanclasprite.totalFrames = 1;
	    chanclasprite.rotationRate = 1.0;
	    
	    invisiblechancla = new Sprite();
	    invisiblechancla.load("/invisiblejuanito.png", 3, 4, 38, 37);
	    invisiblechancla.position = new Point(x,y);
	    invisiblechancla.frameDelay = 0;
	    invisiblechancla.totalFrames = 1;
	    invisiblechancla.rotationRate = 1.0;
	    
	    current = invisiblechancla;

		explosion= new Explosion(0,0);
	}
	
	public void throwChancla() {
		chanclasprite.totalFrames = 10;
		if(right)
			throwChanclaRight();
		else
			throwChanclaLeft();
		
		if(this.hasChanged())
			notifyObservers(this);
	}
	
	public boolean colliding(Rectangle rect) {
		if(chanclasprite.getBounds().intersects(rect)) {
			velx = -velx;
			chanclasprite.animationDirection = -chanclasprite.animationDirection;

			collision=true;
			return true;
		}
		collision=false;
		return false;
		
	}
	
	public void throwChanclaRight() {
		if(x >  Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2)|| velx > 0) {
			this.setChanged();
			x = (int)(x + velx*dt);
			chanclasprite.position = new Point(x,y);
			velx = (int)(velx + (-3)*dt);
			chanclasprite.velocity = new Point(velx,0);
		}
		else {
			chanclasprite.totalFrames = 1;
			velx = 0;
			chanclasprite.velocity = new Point(velx,0);
			x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
			y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2)-6;
			chanclasprite.position = new Point(x,y);
		}
		
	}
	public void throwChanclaLeft() {
		if(x <  Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2)|| velx < 0) {
			this.setChanged();
			x = (int)(x + velx*dt);
			chanclasprite.position = new Point(x,y);
			velx = (int)(velx + 3*dt);
			chanclasprite.velocity = new Point(velx,0);
		}
		else {
			chanclasprite.totalFrames = 1;
			velx = 0;
			chanclasprite.velocity = new Point(velx,0);
			x = Juanito.getInstance().getX()+(int)(Juanito.getInstance().getWidth()/2);
			y = Juanito.getInstance().getY()+(int)(Juanito.getInstance().getHeight()/2)-6;
			chanclasprite.position = new Point(x,y);
		}
	}
	
	public void draw(Graphics g) {
		current.draw(g);
		//g.drawRect(x, y, width, height);

		if(collision) {
			explosion.setX(x);
			explosion.setY(y);
			explosion.draw(g);
		}
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
	
	public void setVisible(boolean visible) {
		if(visible) {
			current=chanclasprite;
		}
		else {
			current=invisiblechancla;
			System.out.println("INVISIBLE CHANCLA");
		}
			
	}
}
