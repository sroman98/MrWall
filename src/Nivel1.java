
public class Nivel1 implements PlayPanelState{
	
	private Background background;
	private Background foreground;
	private Background middleground;
	private Obstaculos obstaculos;
	
	PlayPanelStateContext context;

	public Nivel1(PlayPanelStateContext context){
		this.context=context;
		foreground = new Background(0,0,"img/foreground1.png");
		middleground = new Background(0,0,"img/middleground1.png");
		background = new Background(0,0,"img/background1.png");
		obstaculos = new Obstaculos();
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

	public PlayPanelStateContext getContext() {
		return context;
	}

	public void setContext(PlayPanelStateContext context) {
		this.context = context;
	}
	
	//methods
	public void mytitle(){
		System.out.println("ESTAS JUGANDO EL NIVEL 1");
	}
	
	public void myenemies() {
		System.out.println("ENEMIGOS 10");
	}
		
}
