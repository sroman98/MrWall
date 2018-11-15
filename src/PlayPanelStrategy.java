
public interface PlayPanelStrategy {

	public Background getBackground();

	public void setBackground(Background background);

	public Background getForeground();

	public void setForeground(Background foreground);

	public Background getMiddleground();

	public void setMiddleground(Background middleground);

	public Obstaculos getObstaculos();

	public void setObstaculos(Obstaculos obstaculos);

	public PlayPanelStrategyContext getContext();

	public void setContext(PlayPanelStrategyContext context);
	
}
