
public class JuanitoStaticState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoStaticState(JuanitoStateContext nc) {
		jc = nc;
	}
	
	public void shoot() {
		jc.setCurrent(jc.getMovingState());
	}
	public void stop() {
		//NADA
	}
	public void hurt() {
		System.out.println("Im injured");
		jc.setCurrent(jc.getInjuredState());
	}
	public void heal() {
		System.out.println("No se puede heal en static!");
	}
	public void die() {
		System.out.println("No se puede die en static!");
	}
	
	@Override
	public void moveRight() {
		Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveder());
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVelx(3);
	}
	@Override
	public void moveLeft() {
		Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveizq());
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVelx(-3);
		
	}
	@Override
	public void moveJump() {
		if(Juanito.getInstance().getMygif() == Juanito.getInstance().getJstillder())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVely(-25);
	}
}