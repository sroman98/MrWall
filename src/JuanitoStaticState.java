public class JuanitoStaticState implements JuanitoState {
		JuanitoStateContext jc;

		public JuanitoStaticState(JuanitoStateContext nc) {jc = nc;}
		public void jump(Juanito juanito) {
			System.out.println("I started jumping");
			//jc.setCurrent(jc.getMovingState());
		}
		public void shoot() {jc.setCurrent(jc.getMovingState());}
		public void move() {
			System.out.println("I started moving");
			jc.setCurrent(jc.getMovingState());
		}
		public void stop() {System.out.println("Ya estoy detenido!");}
		public void hurt() {
			System.out.println("Im injured");
			jc.setCurrent(jc.getInjuredState());
		}
		public void heal() {System.out.println("No se puede heal en static!");}
		public void die() {System.out.println("No se puede die en static!");}
}