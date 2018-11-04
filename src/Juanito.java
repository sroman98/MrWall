import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Juanito extends Personaje {
	private JuanitoStateContext juan;
	private boolean right;
	private boolean left;
	private boolean up;
	private float dt;
	private int gravity;
	
	public Juanito(int x, int y, int w, int h, String path) {
		super(x,y,w,h, 5, 3, 0, path);
		right = false;
		left = false;
		dt = (float) 0.666;
		gravity = 2;	
		juan = new JuanitoStateContext();
		System.out.println("Creaste un juanito e inicializaste su contexto");
	}
	public JuanitoStateContext getContextoEstado() {return juan;}
	public void jump() {
		if((vely > 0 && y <600-10) || vely <= 0) {
			y = (int)(y + vely*dt);
			vely = (int)(vely + gravity*dt);
		}
	}
	public void move(Background fg, Background mg, Background bg) {
		if(right) {
	      if(x<480 || (fg.getX()<=-3240 && x+width<=1000)) {x += velx;}
	      else {
	    	  fg.setX(-3);
	    	  if(fg.getX()>-3240) {
	    		mg.setX(-2);
	    		bg.setX(-1);
	    	  }
	      }
	   }if(left) {
		   if(x>480 || (fg.getX()>=0 && x>0)) {x -= velx;}
		   else {
			   fg.setX(3);
			   if(fg.getX()>-3240) {
				   mg.setX(2);
				   bg.setX(1);
			   }
		   }
	   }if(up) {jump();}
	}
	public void move(KeyEvent e) {
		int key = e.getKeyCode();
		if(!(right || left)) {juan.move();}	
		switch(key) {
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			juan.jump();
			if(vely != -25) {vely = -25;}
			break;
		}
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
		}if(!(right || left)) {juan.stop();}
	}
	public void hurt() {juan.hurt();}
	public void heal() {juan.heal();}
	public void die() {juan.die();}
	public void draw(Graphics g) {mygif.paintIcon(null,g, x, y);}
	//Wont use but need to override
	@Override
	public void move() {/*TODO Auto-generated method stub*/}
	@Override
	public void stop() {/*TODO Auto-generated method stub*/}
}