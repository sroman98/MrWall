import java.awt.Color;

public class GameActiveState implements GameState {

	private GameStateContext con;
	private Background background;
	private Button button;

	public GameActiveState(GameStateContext nc) {
		setCon(nc);
		background = new Background(0,0, "/runningbackground.png");
		button = new Button(200,5,140,40,Color.CYAN, "pause",15, "#4372e8");
	}
	
	public void active() {}
	public void paused() {}
	
	public void setBackground(Background bg) {
		this.background = bg;
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

	public GameStateContext getCon() {
		return con;
	}

	public void setCon(GameStateContext con) {
		this.con = con;
	}
}
