import java.awt.Color;

public class GamePausedState implements GameState {

	private GameStateContext con;
	private Background background;
	private Button button;

	public GamePausedState(GameStateContext nc) {
		con = nc;
		background = new Background(0,0, "img/pausedbackground.png");
		button = new Button(460,350,160,40,Color.CYAN, "resume",15, "#4372e8");
	}
	
	public void active() {}
	public void paused() {}
	
	public void setBackground(Background bg) {
		this.background=background;
	}
	
	public Background getBackground() {
		return background;
	}
	
	public void setButton(Button button) {
		this.button=button;
	}
	
	public Button getButton() {
		return button;
	}
}
