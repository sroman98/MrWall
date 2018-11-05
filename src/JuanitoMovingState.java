public class JuanitoMovingState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoMovingState(JuanitoStateContext nc) {jc = nc;}
	public void jump(Juanito j) {
		if((j.getVely() > 0 && j.getY() <590) || j.getVely() <= 0) {
			j.y = (int)(j.y + j.vely*j.getDt());
			j.vely = (int)(j.vely + j.getGravity()*j.getDt());
		}
		System.out.println("Im jumping & moving");
	}/*no cambia de estado*/
	
	public void shoot() {System.out.println("Im shooting & moving");}/*no cambia de estado*/
	public void move() {System.out.println("Ya me estoy moviendo, no me puedo mover mas!");}
	public void stop() {
		jc.setCurrent(jc.getStaticState());
		System.out.println("Me detuve");
	}
	public void hurt() {
		jc.setCurrent(jc.getInjuredState());
		System.out.println("Me hirieron");
	}
	public void heal() {System.out.println("No se puede heal en moving!");}
	public void die() {System.out.println("No se puede die en moving!");}
}