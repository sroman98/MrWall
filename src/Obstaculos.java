import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

public class Obstaculos {
	private ArrayList<Obstaculo> obstaculos;
	private Obstaculo obs1;
	private Obstaculo obs2;
	private Obstaculo obs3;
	private Obstaculo obs4;
	private Obstaculo obs5;
	
	public Obstaculos(){
		obstaculos = new ArrayList<Obstaculo>();
	}
	
	public void addObstaculo(int x, int y, int width, int height, String path){
		obstaculos.add(new Obstaculo(x, y, width, height, path));
	}
	
	public void draw(Graphics g) {
		ListIterator<Obstaculo> apt = obstaculos.listIterator(); 
		while(apt.hasNext()) {
			Obstaculo obstaculo = apt.next();
			obstaculo.draw(g);
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