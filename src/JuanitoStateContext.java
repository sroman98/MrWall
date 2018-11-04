

public class JuanitoStateContext {

	private JuanitoState estados[]= new JuanitoState[5];
	private JuanitoStateProperties propiedades;
	private JuanitoState current;

	public JuanitoStateContext() {
		propiedades = new JuanitoStateProperties();
		estados[0] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(0), this); //JuanitoStaticState
		estados[1] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(1), this); //JuanitoMovingState
		estados[2] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(2), this); //JuanitoInjuredState
		estados[3] = JuanitoStateFactory.getInstance().getJuanitoState(propiedades.getClase(3), this); //JuanitoDeadState
		current = estados[0]; //Initial State: JuanitoStaticState
	}
	public void setCurrent(JuanitoState js) {current= js;}
	public JuanitoState getCurrent() {return current;}
	public JuanitoStaticState getStaticState() {return (JuanitoStaticState) estados[0];}
	public JuanitoMovingState getMovingState() {return (JuanitoMovingState) estados[1];}
	public JuanitoInjuredState getInjuredState() {return (JuanitoInjuredState) estados[2];}
	public JuanitoDeadState getDeadState() {return (JuanitoDeadState) estados[3];}

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
