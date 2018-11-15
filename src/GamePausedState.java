
public class GamePausedState implements GameState {

	GameStateContext con;

	public GamePausedState(GameStateContext nc) {
		con = nc;
	}
	
	public void active() {}
	public void paused() {}
}
