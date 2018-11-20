import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

public class Enemigos implements Observer{

private ArrayList<Enemigo> enemigos;
	
	public Enemigos() {
		enemigos = new ArrayList<Enemigo>();
		Juanito.getInstance().addObserver(this);
	}
	
	public void addEnemigo(int x, int y, int width, int height){
		enemigos.add(new Enemigo(x, y, width, height));
		Juanito.getInstance().addObserver(this);
	}
	
	@Override
	public synchronized void update(Observable o, Object arg) {
		if(o == Juanito.getInstance()) {
			ListIterator<Enemigo> itr = enemigos.listIterator();
			while(itr.hasNext()) {
				Enemigo en = itr.next();
				if(en.isColliding()) {
					en.setCollisions();
					return;
				}
				Juanito.getInstance().setCr(false);
				Juanito.getInstance().setCl(false);
				Juanito.getInstance().setCd(false);
			}
		}	
	}
	
	public void draw(Graphics g) {
		ListIterator<Enemigo> apt = enemigos.listIterator(); 
		while(apt.hasNext()) {
			Enemigo en = apt.next();
			en.draw(g);
		}//While
	}
	public void avanzar(int n) {
		ListIterator<Enemigo> apt = enemigos.listIterator(); 
		
		while(apt.hasNext()) {
			Enemigo en = apt.next();
			en.setX(en.getX()+n);
		}//While		
	}
	public void eliminar (int index) {
		ListIterator<Enemigo> apt = enemigos.listIterator(); 
		while(apt.hasNext()) {
			Enemigo en = apt.next();
			if(en instanceof Enemigo) {enemigos.remove(index);}
		}//While
	}
	
	//Getters & setters
	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}
}
