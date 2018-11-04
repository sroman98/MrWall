import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

public class Obstaculos {
	private ArrayList obstaculos;
	private Obstaculo obs1;
	private Obstaculo obs2;
	private Obstaculo obs3;
	private Obstaculo obs4;
	private Obstaculo obs5;
	
	Obstaculos(){
		obstaculos = new ArrayList<Obstaculo>(5);
		
		obs1 = new Obstaculo(30, 300, 30, "");
		obs2 = new Obstaculo(70, 300, 30, "");
		obs3 = new Obstaculo(110, 300, 30, "");
		obs4 = new Obstaculo(150, 300, 30, "");
		obs5 = new Obstaculo(190, 300, 30, "");
		
		obstaculos.add(obs1);
		obstaculos.add(obs2);
		obstaculos.add(obs3);
		obstaculos.add(obs4);
		obstaculos.add(obs5);
	}
	
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
			if(obstaculo instanceof Obstaculo) {obstaculos.remove(index);}
		}//While		
	}
}