/*
 * This State models what Juanito can and cannot do when he is dead
 */


public class JuanitoDeadState implements JuanitoState{
	JuanitoStateContext jc;

	public JuanitoDeadState(JuanitoStateContext nc) {jc = nc;}

	public void shoot() {System.out.println("No se puede shoot en dead!");}
	public void stop() {System.out.println("No se puede stop en dead!");}
	public void hurt() {System.out.println("No se puede hurt en dead!");}
	public void heal() {System.out.println("No se puede heal en dead!");}
	public void die() {	System.out.println("Ya estoy muerto!");}
	public void moveRight() {
		System.out.println("No se puede move en dead!");
	}

	@Override
	public void moveLeft() {
		System.out.println("No se puede move en dead!");
	}

	@Override
	public void moveJump() {
		System.out.println("No se puede move en dead!");
	}
}