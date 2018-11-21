/*
 * This class is the interface implemented by the different Game States
 */
public interface GameState {

	public void active();
	public void paused();
	
	public void setBackground(Background bg);
	public Background getBackground();
	public void setButton(Button button);
	public Button getButton();
}
