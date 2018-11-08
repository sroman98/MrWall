
public class PlayPanelStateContext {
	private PlayPanelState niveles[]= new PlayPanelState[2];
	private PlayPanelStateProperties propiedades;
	private PlayPanelState current;

	public PlayPanelStateContext() {
		propiedades = new PlayPanelStateProperties();
		niveles[0] = PlayPanelStateFactory.getInstance().getPlayPanelState(propiedades.getClase(0), this); //Nivel1
		niveles[1] = PlayPanelStateFactory.getInstance().getPlayPanelState(propiedades.getClase(1), this); //Nivel2
		current = niveles[0]; //Initial State: JuanitoStaticState
	}
	
	public void setCurrent(PlayPanelState pps) {current= pps;}
	public PlayPanelState getCurrent() {return current;}
	public Nivel1 getNivel1() {return (Nivel1) niveles[0];}
	public Nivel2 getNivel2() {return (Nivel2) niveles[1];}
}
