import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Juanito {
	//Singleton
	private static Juanito instance;
	public static synchronized Juanito getInstance() {
	    if(instance == null) {
	      instance = new Juanito(0, 600, 90, 90);
	    }
	    return instance;
	 }
	//Variables	
	private JuanitoStateContext juanitoStateContext;

	private Rectangle rectangulo;
	
	private float dt;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int vida;
	protected int velx;
	protected int vely;
	private int gravity;
	private int referencia;
	private int puntaje;
	private int nivel;
	
	protected ImageIcon mygif;
	private ImageIcon jmoveizq;
	private ImageIcon jmoveder;
	private ImageIcon jstillizq;
	private ImageIcon jstillder;
	private ImageIcon jsaltader;
	private ImageIcon jsaltaizq;
	private ImageIcon jinvisible;
	
	//Constructor
	public Juanito(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.vida = 5;
		this.velx = 0;
		this.vely = 0;
		mygif = new ImageIcon("img/jstillder.png");
		
		dt = (float) 0.666;
		gravity = 2;	
		juanitoStateContext = new JuanitoStateContext();
		rectangulo = new Rectangle();
		referencia = this.getY();
		puntaje = 0;
		nivel = 0;
		
		jmoveder= new ImageIcon("img/jmoveder.gif");
		jmoveizq= new ImageIcon("img/jmoveizq.gif");
		jstillder= new ImageIcon("img/jstillder.png");
		jstillizq= new ImageIcon("img/jstillizq.png");
		jsaltader= new ImageIcon("img/jsaltader.png");
		jsaltaizq= new ImageIcon("img/jsaltaizq.png");
		jinvisible=new ImageIcon("img/juanitoinvisible");
		
	}
	
	//Moving methods
	public void move(Background fg, Background mg, Background bg, Obstaculos obs) {
		if(velx>0) { //moving right
			if(x<480 || (fg.getX()<=-3240 && x+width<=1070)) {
				x += velx;
			} else {
				fg.setX(-velx);
				if(fg.getX()!=-3240) {obs.avanzar(-velx);}
				if(fg.getX()>-3240) {
					mg.setX(-velx+1);
					bg.setX(-velx+2);
				}
			}
		}
			
		else if(velx<0) { //moving left
			if(x>480 || (fg.getX()>=0 && x>0)) {
				x += velx;
			}
			else {
				   fg.setX(-velx);
				   if(fg.getX()!=0) {obs.avanzar(-velx);}
				   if(fg.getX()>-3240) { 
					   mg.setX(-velx-1);
					   bg.setX(-velx-2);
				   }
			   }
		}
	}
	public void jump() {
		if(y < 600 || vely < 0) {
			y = (int)(y + vely*dt);
			vely = (int)(vely + gravity*dt);
		}
		else {
			vely = 0;
			y = 600;
			if(velx>0)
				juanitoStateContext.getCurrent().moveRight();
			else if(velx<0)
				juanitoStateContext.getCurrent().moveLeft();
			else
				juanitoStateContext.getCurrent().stop();
		}
			
	}
	
	
	
	//Drawing methods
	public void draw(Graphics g) {
		mygif.paintIcon(null,g, x, y);
		g.drawRect(x, y, this.width, this.height);
	}
	
	//setters and getters
		public JuanitoStateContext getJuanitoStateContext() {
			return juanitoStateContext;
		}

		public void setJuanitoStateContext(JuanitoStateContext juanitoStateContext) {
			this.juanitoStateContext = juanitoStateContext;
		}

		public float getDt() {
			return dt;
		}

		public void setDt(float dt) {
			this.dt = dt;
		}

		public int getGravity() {
			return gravity;
		}

		public void setGravity(int gravity) {
			this.gravity = gravity;
		}

		public Rectangle getRectangulo() {
			return rectangulo;
		}

		public void setRectangulo(Rectangle rectangulo) {
			this.rectangulo = rectangulo;
		}

		public int getReferencia() {
			return referencia;
		}

		public void setReferencia(int referencia) {
			this.referencia = referencia;
		}

		public int getPuntaje() {
			return puntaje;
		}
		
		public void setPuntaje(int puntaje) {
			this.puntaje = puntaje;
		}
		
		public int getNivel() {
			return nivel;
		}
		
		public void setNivel(int n) {
			if(n>this.nivel) {this.nivel = n;} //prevenir que se repita el mismo # de nivel y me den numero negativos
		}
		
		public ImageIcon getJmoveizq() {
			return jmoveizq;
		}

		public void setJmoveizq(ImageIcon jmoveizq) {
			this.jmoveizq = jmoveizq;
		}

		public ImageIcon getJmoveder() {
			return jmoveder;
		}

		public void setJmoveder(ImageIcon jmoveder) {
			this.jmoveder = jmoveder;
		}

		public ImageIcon getJstillizq() {
			return jstillizq;
		}

		public void setJstillizq(ImageIcon jstillizq) {
			this.jstillizq = jstillizq;
		}

		public ImageIcon getJstillder() {
			return jstillder;
		}

		public void setJstillder(ImageIcon jstillder) {
			this.jstillder = jstillder;
		}

		public ImageIcon getJsaltader() {
			return jsaltader;
		}

		public void setJsaltader(ImageIcon jsaltader) {
			this.jsaltader = jsaltader;
		}

		public ImageIcon getJsaltaizq() {
			return jsaltaizq;
		}

		public void setJsaltaizq(ImageIcon jsaltaizq) {
			this.jsaltaizq = jsaltaizq;
		}
		
		public void setJinvisible(ImageIcon jinvisible) {
			this.jinvisible = jinvisible;
		}

		public ImageIcon getJinvisible() {
			return jinvisible;
		}

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

		public ImageIcon getMygif() {
			return mygif;
		}

		public void setMygif(ImageIcon mygif) {
			this.mygif = mygif;
		}
		
		public void setVisible(boolean visible) {
			if(visible) {
				this.setMygif(this.jstillder);
			}
			else {
				this.setMygif(this.getJinvisible());
			}
		}
}