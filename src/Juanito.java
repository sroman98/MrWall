import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Juanito extends Personaje {
	private JuanitoStateContext juanitoStateContext;
	private boolean right;
	private boolean left;
	private boolean up;
	private float dt;
	private int gravity;
	private Rectangle rectangulo;
	private int referencia;
	public Juanito(int x, int y, int w, int h, String path) {
		super(x,y,w,h, 5, 3, 0, path);
		right = false;
		left = false;
		dt = (float) 0.666;
		gravity = 2;	
		juanitoStateContext = new JuanitoStateContext();
		rectangulo = new Rectangle();
		System.out.println("Creaste un juanito e inicializaste su contexto");
		referencia = this.getY();
	}
	public JuanitoStateContext getContextoEstado() {return this.juanitoStateContext;}
	public void movingWithLandscape(Background fg, Background mg, Background bg, Obstaculos obs) {
		if(right) {
			if(x<480 || (fg.getX()<=-3240 && x+width<=1070)) {x += velx;}
			else {
				fg.setX(-3);
				if(fg.getX()!=-3240) {obs.avanzar(-3);}
				if(fg.getX()>-3240) {
					mg.setX(-2);
					bg.setX(-1);
				}
			}
	   }if(left) {
		   if(x>480 || (fg.getX()>=0 && x>0)) {x -= velx;}
		   else {
			   fg.setX(3);
			   if(fg.getX()!=0) {obs.avanzar(3);}
			   if(fg.getX()>-3240) { 
				   mg.setX(2);
				   bg.setX(1);
			   }
		   }
	   }if(up || this.y < referencia-10){ // -10 due to relative error
		   juanitoStateContext.setCurrent(juanitoStateContext.getMovingState());
		   juanitoStateContext.getCurrent().jump(this);
	   }
	}
	public void move(KeyEvent e) {
		int key = e.getKeyCode();	
		switch(key) {
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			juanitoStateContext.getCurrent().jump(this);
			if(vely != -25 && this.y>=590) {vely = -25;} //Complemented with -&& 590-
			break;
		}
		if(right || left || up){juanitoStateContext.getCurrent().move();}
		else {juanitoStateContext.getCurrent().stop();}
	}
	public void stop(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		}if(!(right && left && up)) {
			juanitoStateContext.setCurrent(juanitoStateContext.getStaticState());
			juanitoStateContext.getCurrent().stop();
		}
	}
	public void hurt() {juanitoStateContext.getCurrent().hurt();}
	public void heal() {juanitoStateContext.getCurrent().heal();}
	public void die() {juanitoStateContext.getCurrent().die();}
	//getters
	public float getDt() {return this.dt;}
	public int getGravity() {return this.gravity;}
	@Override
	public void draw(Graphics g) {
		mygif.paintIcon(null,g, x, y);
		g.drawRect(x, y, this.width, this.height);
	}
}