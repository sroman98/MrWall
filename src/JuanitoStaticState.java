public class JuanitoStaticState implements JuanitoState {
		JuanitoStateContext jc;

		public JuanitoStaticState(JuanitoStateContext nc) {jc = nc;}
		public void jump(Juanito juanito) {/*jc.setCurrent(jc.getMovingState());*/System.out.println("Estatico");}
		public void shoot() {jc.setCurrent(jc.getMovingState());}
		public void move() {
			jc.setCurrent(jc.getMovingState());
			System.out.println("I started moving");
		}
		public void stop() {System.out.println("Ya estoy detenido!");}
		public void hurt() {jc.setCurrent(jc.getInjuredState());}
		public void heal() {System.out.println("No se puede heal en static!");}
		public void die() {System.out.println("No se puede die en static!");}
}