
public interface GameState {

	public void active();
	public void paused();
	
	public void setBackground(Background bg);
	public Background getBackground();
	public void setButton(Button button);
	public Button getButton();
}
