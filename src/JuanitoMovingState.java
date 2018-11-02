
public class JuanitoMovingState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoMovingState(JuanitoStateContext nc) {
		jc = nc;
	}

	public void jump() {
		//no cambia de estado
		System.out.println("Im jumping & moving");
	}
	public void shoot() {
		//no cambia de estado
		System.out.println("Im shooting & moving");
	}
	public void move() {
		System.out.println("Ya me estoy moviendo, no me puedo mover más!");
	}
	public void stop() {
		jc.setCurrent(jc.getStatic());
		System.out.println("Me detuve");
	}
	public void hurt() {
		jc.setCurrent(jc.getInjured());
		System.out.println("Me hirieron");
	}
	public void heal() {
		System.out.println("No se puede heal en moving!");
	}
	public void die() {
		System.out.println("No se puede die en moving!");
	}
}
