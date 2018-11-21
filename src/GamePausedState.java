/*
 * This class is the Game Paused State so the player cannot continue playing or moving, the enemies also stop.
 * It implements Game State to implements the methods
 */

import java.awt.Color;

public class GamePausedState implements GameState {

	private GameStateContext con;
	private Background background;
	private Button button;

	public GamePausedState(GameStateContext nc) {
		setCon(nc);
		background = new Background(0,0, "/pausedbackground.png");
		button = new Button(460,350,160,40,Color.CYAN, "resume",15, "#4372e8");
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
