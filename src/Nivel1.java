<<<<<<< HEAD
/*
 * This class is a strategy of the Play Panel. It models the first level with its corresponding buttons, enemies and obstacles
 */

import java.awt.Color;
=======
>>>>>>> fdee285dcd04d0ac0666dadf987ada6499003891
import java.util.Random;

public class Nivel1 implements PlayPanelStrategy{
	
	private Background background;
	private Background foreground;
	private Background middleground;
	private Obstaculos obstaculos;
	private Buttons buttons;
	private Enemigos enemigos;
	
	PlayPanelStrategyContext context;

	public Nivel1(PlayPanelStrategyContext context){
		this.context=context;
		foreground = new Background(0,0,"/foreground1.png");
		middleground = new Background(0,0,"/middleground1.png");
		background = new Background(0,0,"/background1.png");
		
		obstaculos = new Obstaculos();
		enemigos = new Enemigos();
		buttons = new Buttons();
		
		//buttons.addButton(730,5,140,40,Color.CYAN, "menu",15, "#4372e8");

		createStuff();		
	}
	
	public void createStuff() {
		Random r = new Random();
		for(int i=0; i<6; i++) {
			enemigos.addEnemigo(r.nextInt(3620)+400, 600, 70, 85);
			obstaculos.addObstaculo(r.nextInt(3720)+300 , 635, 95, 48, "/obs1.png");
		}
	}
	
	public void deleteStuff() {
		obstaculos.eliminarTodos();
		enemigos.eliminarTodos();
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
	
	public void setEnemigos(Enemigos enemigos) {
		this.enemigos = enemigos;
	}
	
	public Enemigos getEnemigos() {
		return enemigos;
	}

	public PlayPanelStrategyContext getContext() {
		return context;
	}

	public void setContext(PlayPanelStrategyContext context) {
		this.context = context;
	}
	@Override
	public Buttons getButtons() {
		return buttons;
	}
	@Override
	public void setButtons(Buttons buttons) {
		this.buttons = buttons;
		
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
