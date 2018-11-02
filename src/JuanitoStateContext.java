
public class JuanitoStateContext {

	private JuanitoState statics;
	private JuanitoState move;
	private JuanitoState injured;
	private JuanitoState dead;

	private JuanitoState current;

	public JuanitoStateContext() {
		statics = JuanitoStateFactory.getInstance().getJuanitoState("JuanitoStaticState", this);
		move = JuanitoStateFactory.getInstance().getJuanitoState("JuanitoMovingState", this);
		injured = JuanitoStateFactory.getInstance().getJuanitoState("JuanitoInjuredState", this);
		dead = JuanitoStateFactory.getInstance().getJuanitoState("JuanitoDeadState", this);
		current = statics;
	}

	public JuanitoState getStatic() {
		return statics;
	}
	public JuanitoState getMove() {
		return move;
	}
	public JuanitoState getInjured() {
		return injured;
	}
	public JuanitoState getDead() {
		return dead;
	}

	public void setCurrent(JuanitoState js) {
		current= js;
	}

	public void jump() {
		current.jump();
	}
	public void shoot() {
		current.shoot();
	}
	public void move() {
		current.move();
	}
	public void stop() {
		current.stop();
	}
	public void hurt() {
		current.hurt();
	}
	public void heal() {
		current.heal();
	}
	public void die() {
		current.die();
	}
}
