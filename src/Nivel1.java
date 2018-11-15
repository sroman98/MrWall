
public class Nivel1 implements PlayPanelStrategy{
	
	private Background background;
	private Background foreground;
	private Background middleground;
	private Obstaculos obstaculos;
	
	PlayPanelStrategyContext context;

	public Nivel1(PlayPanelStrategyContext context){
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

	public PlayPanelStrategyContext getContext() {
		return context;
	}

	public void setContext(PlayPanelStrategyContext context) {
		this.context = context;
	}
		
}
