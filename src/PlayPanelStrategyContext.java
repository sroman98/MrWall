
public class PlayPanelStrategyContext {
	private PlayPanelStrategy niveles[]= new PlayPanelStrategy[2];
	private PlayPanelStrategyProperties propiedades;
	private PlayPanelStrategy current;

	public PlayPanelStrategyContext() {
		propiedades = new PlayPanelStrategyProperties();
		niveles[0] = PlayPanelStrategyFactory.getInstance().getPlayPanelState(propiedades.getClase(0), this); //Nivel1
		niveles[1] = PlayPanelStrategyFactory.getInstance().getPlayPanelState(propiedades.getClase(1), this); //Nivel2
		current = niveles[0]; //Initial State: JuanitoStaticState
	}
	
	public void setCurrent(PlayPanelStrategy pps) {current= pps;}
	public PlayPanelStrategy getCurrent() {return current;}
	public Nivel1 getNivel1() {return (Nivel1) niveles[0];}
	public Nivel2 getNivel2() {return (Nivel2) niveles[1];}
}
