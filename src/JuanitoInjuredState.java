public class JuanitoInjuredState implements JuanitoState{
	JuanitoStateContext jc;

	public JuanitoInjuredState(JuanitoStateContext nc) {jc = nc;}
	public void jump() {System.out.println("No se puede jump en injured!");}
	public void shoot() {System.out.println("No se puede shoot en injured!");}
	public void move() {jc.setCurrent(jc.getMovingState());}
	public void stop() {System.out.println("No se puede stop en injured!");}
	public void hurt() {System.out.println("Ya estoy herido!");}
	public void heal() {jc.setCurrent(jc.getStaticState());}
	public void die() {jc.setCurrent(jc.getDeadState());}
}
