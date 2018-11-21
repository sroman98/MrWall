/*
 * This State models what Juanito can and cannot do when he is injured
 */


public class JuanitoInjuredState implements JuanitoState{
	JuanitoStateContext jc;

	public JuanitoInjuredState(JuanitoStateContext nc) {jc = nc;}
	
	public void shoot() {System.out.println("No se puede shoot en injured!");}
	public void stop() {System.out.println("No se puede stop en injured!");}
	public void hurt() {System.out.println("Ya estoy herido!");}
	public void heal() {jc.setCurrent(jc.getStaticState());}
	public void die() {jc.setCurrent(jc.getDeadState());}
	
	@Override
	public void moveRight() {
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVelx(3);
		Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveder());
	}
	@Override
	public void moveLeft() {
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVelx(-3);
		Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveizq());
		
	}
	@Override
	public void moveJump() {
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVely(-25);
		if(Juanito.getInstance().getVelx()>0 || Juanito.getInstance().getMygif() == Juanito.getInstance().getJstillder())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
	}
}
