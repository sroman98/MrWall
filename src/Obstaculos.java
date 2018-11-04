import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

public class Obstaculos {
	private ArrayList obstaculos;
	
	Obstaculos(){obstaculos = new ArrayList<Obstaculo>(5);}
	
	public void paint(Graphics g) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			obstaculo.paint(g);
		}//While
	}
	public void avanzar(int n) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			obstaculo.setX(obstaculo.getX()+n);
		}//While		
	}
	public void eliminar (int index) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			if(obstaculo instanceof Obstaculo) {
				obstaculos.remove(index);
			}
		}//While		
	}
}