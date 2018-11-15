
public class GameStateContext {
	private GameState estados[]= new GameState[2];
	private GameStateProperties propiedades;
	private GameState current;

	public GameStateContext() {
		propiedades = new GameStateProperties();
		estados[0] = GameStateFactory.getInstance().getGameState(propiedades.getClase(0), this); //Nivel1
		estados[1] = GameStateFactory.getInstance().getGameState(propiedades.getClase(1), this); //Nivel2
		current = estados[0]; //Initial State: JuanitoStaticState
	}
	
	public void setCurrent(GameState pps) {current= pps;}
	public GameState getCurrent() {return current;}
	public GameActiveState getGameActiveState() {return (GameActiveState) estados[0];}
	public GamePausedState getGamePausedState() {return (GamePausedState) estados[1];}
}
