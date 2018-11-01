
public class JuanitoStaticState implements JuanitoState {
		JuanitoStateContext jc;

		public JuanitoStaticState(JuanitoStateContext nc) {
			jc = nc;
		}

		public void jump() {
			jc.setCurrent(jc.getMove());
		}
		public void shoot() {
			jc.setCurrent(jc.getMove());
		}
		public void move() {
			jc.setCurrent(jc.getMove());
			System.out.println("I started moving");
		}
		public void stop() {
			System.out.println("Ya estoy detenido!");
		}
		public void hurt() {
			jc.setCurrent(jc.getInjured());
		}
		public void heal() {
			System.out.println("No se puede heal en static!");
		}
		public void die() {
			System.out.println("No se puede die en static!");
		}
}
