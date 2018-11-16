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
	
	//Getters & setters
	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}

	public Obstaculo getObs1() {
		return obs1;
	}

	public void setObs1(Obstaculo obs1) {
		this.obs1 = obs1;
	}

	public Obstaculo getObs2() {
		return obs2;
	}

	public void setObs2(Obstaculo obs2) {
		this.obs2 = obs2;
	}

	public Obstaculo getObs3() {
		return obs3;
	}

	public void setObs3(Obstaculo obs3) {
		this.obs3 = obs3;
	}

	public Obstaculo getObs4() {
		return obs4;
	}

	public void setObs4(Obstaculo obs4) {
		this.obs4 = obs4;
	}

	public Obstaculo getObs5() {
		return obs5;
	}

	public void setObs5(Obstaculo obs5) {
		this.obs5 = obs5;
	}
}