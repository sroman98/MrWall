import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Observable;

import javax.swing.ImageIcon;

public class Juanito extends Observable {
	//Singleton
	private static Juanito instance;
	public static synchronized Juanito getInstance() {
	    if(instance == null) {
	      instance = new Juanito(0, 600, 90, 90);
	      mychancla = new Chancla();
	    }
	    return instance;
	}
	//Variables	
	private JuanitoStateContext juanitoStateContext;

	private Rectangle rectangulo;
	
	private float dt;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int vida;
	private int velx;
	private int vely;
	private int gravity;
	private int referencia;
	private int puntaje;
	private int nivel;
	
	private boolean der;
	private boolean izq;
	private boolean cr, cl, cd;
	private boolean active;
	
	private static Chancla mychancla;
	
	private ImageIcon mygif;
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
		
		cr = cl = cd = false;
		der=true;
		izq=false;
		
		active = false;
		
		dt = (float) 0.666;
		gravity = 2;	
		juanitoStateContext = new JuanitoStateContext();
		rectangulo = new Rectangle(x+25,y,width-40,height-5);
		referencia = this.getY();
		puntaje = 0;
		nivel = 0;
		
		jmoveder= createImageIcon("/jmoveder.gif");
		jmoveizq= createImageIcon("/jmoveizq.gif");
		jstillder= createImageIcon("/jstillder.png");
		jstillizq= createImageIcon("/jstillizq.png");
		jsaltader= createImageIcon("/jsaltader.png");
		jsaltaizq= createImageIcon("/jsaltaizq.png");
		jinvisible= createImageIcon("/invisiblejuanito.png");	
		
		mygif = jstillder;
	}
	
	//Moving methods
	public void move(Background fg, Background mg, Background bg, Obstaculos obs, Enemigos ens) {
		if(velx>0) { //moving right
			if(!cr) {
				this.setChanged();
				if(x<480 || (fg.getX()<=-3240 && x+width<=1070)) {
					x += velx;
				} else {
					fg.setX(-velx);
					if(fg.getX()!=-3240) {obs.avanzar(-velx); ens.avanzar(-velx);}
					if(fg.getX()>-3240) {
						mg.setX(-velx+1);
						bg.setX(-velx+2);
					}
				}
			}
		}
		else if(velx<0) { //moving left
			if(!cl) {
				this.setChanged();
				if(x>480 || (fg.getX()>=0 && x>0)) {
					x += velx;
				}
				else {
					fg.setX(-velx);
					if(fg.getX()!=0) {obs.avanzar(-velx); ens.avanzar(-velx);}
					if(fg.getX()>-3240) { 
						mg.setX(-velx-1);
						bg.setX(-velx-2);
					}
				}
			}
		}
		mychancla.throwChancla();
		rectangulo.setLocation(x+25, y);
		if(this.hasChanged())
			notifyObservers(this);
	}
	public void jump() {
		if(y < 600 || vely < 0) {
			if(!cd) {
				this.setChanged();
				y = (int)(y + vely*dt);
				vely = (int)(vely + gravity*dt);
			} else {
				
				vely = 0;
				y = 640-height;
				if(velx>0 && !cr)
					juanitoStateContext.getCurrent().moveRight();
				else if(velx<0 && !cl)
					juanitoStateContext.getCurrent().moveLeft();
				else
					juanitoStateContext.getCurrent().stop();
			}
		}
		else {
			vely = 0;
			y = 600;
			if(velx>0 && !cr)
				juanitoStateContext.getCurrent().moveRight();
			else if(velx<0 && !cl)
				juanitoStateContext.getCurrent().moveLeft();
			else
				juanitoStateContext.getCurrent().stop();
		}
		rectangulo.setLocation(x+25, y);
		if(this.hasChanged())
			notifyObservers(this);
	}
	
	//Drawing methods
	public void draw(Graphics g) {
		mygif.paintIcon(null,g, x, y);
		//g.drawRect(rectangulo.x,rectangulo.y,rectangulo.width,rectangulo.height);
		mychancla.draw(g);
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
		
		public void setPuntaje(int p) {
			if(active)
				puntaje = p;
			if(puntaje<0)
				puntaje=0;
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
			if(vida>=0)
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
		
		public boolean isCr() {
			return cr;
		}

		public void setCr(boolean cr) {
			this.cr = cr;
		}

		public boolean isCl() {
			return cl;
		}

		public void setCl(boolean cl) {
			this.cl = cl;
		}

		public boolean isCd() {
			return cd;
		}

		public void setCd(boolean cd) {
			this.cd = cd;
		}
		
		public void setPerfilIzq(Boolean b) {
			this.izq=b;
		}
		public boolean getPerfilIzq() {
			return this.izq;
		}
		
		public void setPerfilDer(Boolean b) {
			this.der=b;
		}
		public boolean getPerfilDer() {
			return this.der;
		}
		
		public void setVisible(boolean visible) {
			if(visible) {
				this.setMygif(this.jstillder);
				mychancla.setVisible(true);
			}
			else {
				this.setMygif(this.getJinvisible());
				mychancla.setVisible(false);
			}
		}

		public Chancla getMychancla() {
			return mychancla;
		}
		
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean a) {
			this.active = a;
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