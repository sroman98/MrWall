import java.awt.Color;
import java.util.ArrayList;
import java.awt.color.*;
import java.awt.*;

public class NivelMenu implements PlayPanelStrategy{

	private Background background;
	private Background foreground;
	private Background middleground;
	private Obstaculos obstaculos;
	private Buttons buttons;
	
	PlayPanelStrategyContext context;

	public NivelMenu(PlayPanelStrategyContext context){
		this.context=context;
		foreground = new Background(0,0,"img/menubackground.png");
		middleground = new Background(0,0,"img/menubackground.png");
		background = new Background(0,0,"img/menubackground.png");
		obstaculos = new Obstaculos();
		buttons = new Buttons();
		buttons.addButton(700,100,240,70,Color.CYAN,"play",30, "#4372e8");
		buttons.addButton(700,180,240,70,Color.CYAN,"rules",30, "#4372e8");
		System.out.println(buttons.getButton("play").getFontSize());
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

	public PlayPanelStrategyContext getContext() {
		return context;
	}

	public void setContext(PlayPanelStrategyContext context) {
		this.context = context;
	}
	
	public void setButtons(Buttons buttons) {
		this.buttons=buttons;
	}
	
	public Buttons getButtons() {
		return this.buttons;
	}
}
