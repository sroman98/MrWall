/*
 * This class is a strategy of the Play Panel. It models the second level with its corresponding buttons, enemies and obstacles
 */

import java.awt.Color;
import java.util.Random;

public class Nivel2 implements PlayPanelStrategy {
	private Background background;
	private Background foreground;
	private Background middleground;
	private Obstaculos obstaculos;
	private Buttons buttons;
	private Enemigos enemigos;
	
	PlayPanelStrategyContext context;

	public Nivel2(PlayPanelStrategyContext context){
		this.context=context;
		foreground = new Background(0,0,"/foreground3.png");
		middleground = new Background(0,0,"/middleground3.png");
		background = new Background(0,0,"/background3.png");
		
		obstaculos = new Obstaculos();
		buttons = new Buttons();
		enemigos = new Enemigos();		
		//buttons.addButton(730,5,140,40,Color.CYAN, "menu",15, "#4372e8");
	}
	
	public void createStuff() {
		Random r = new Random();
		
		for(int i=0; i<10; i++) {
			enemigos.addEnemigo(r.nextInt(3620)+400, 600, 70, 85);
			obstaculos.addObstaculo(r.nextInt(3720)+300 , 635, 95, 48, "/obs1.png");
		}
		
		obstaculos.addObstaculo(4180, 375, 139, 311, "/wall2.png");
	}
	//getters & setters
	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public Background getForeground() {
		return foreground;
	}

	public void setForeground(Background foreground) {
		this.foreground = foreground;
	}

	public Background getMiddleground() {
		return middleground;
	}

	public void setMiddleground(Background middleground) {
		this.middleground = middleground;
	}

	public Obstaculos getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(Obstaculos obstaculos) {
		this.obstaculos = obstaculos;
	}
	
	public Enemigos getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(Enemigos enemigos) {
		this.enemigos=enemigos;
	}

	public PlayPanelStrategyContext getContext() {
		return context;
	}

	public void setContext(PlayPanelStrategyContext context) {
		this.context = context;
	}
	@Override
	public Buttons getButtons() {
		// TODO Auto-generated method stub
		return buttons;
	}
	@Override
	public void setButtons(Buttons buttons) {
		// TODO Auto-generated method stub
	}
	
	public void reset() {
		enemigos.getEnemigos().clear();
		obstaculos.getObstaculos().clear();
		background.setX(0);
		middleground.setX(0);
		foreground.setX(0);
		Juanito.getInstance().setX(10);
		createStuff();
	}
}