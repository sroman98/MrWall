
public class GameActiveState implements GameState {

	GameStateContext con;

	public GameActiveState(GameStateContext nc) {
		con = nc;
	}
	
	public void active() {}
	public void paused() {}
}
