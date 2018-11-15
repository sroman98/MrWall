
public class JuanitoPausedState implements JuanitoState {
	JuanitoStateContext jc;
	
	public JuanitoPausedState(JuanitoStateContext nc) {jc = nc;}
	
	@Override
	public void shoot() {}
	@Override
	public void moveRight() {}
	@Override
	public void moveLeft() {}
	@Override
	public void moveJump() {}
	@Override
	public void stop() {}
	@Override
	public void hurt() {}
	@Override
	public void heal() {}
	@Override
	public void die() {}

}
