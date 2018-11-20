
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
		foreground = new Background(0,0,"img/foreground3.png");
		middleground = new Background(0,0,"img/middleground3.png");
		background = new Background(0,0,"img/background3.png");
		obstaculos = new Obstaculos();
		buttons = new Buttons();
	
		obstaculos.addObstaculo(930 , 635, 100, 48, "img/obs1.png");
		enemigos = new Enemigos();
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
}