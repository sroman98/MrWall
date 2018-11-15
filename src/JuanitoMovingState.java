
public class JuanitoMovingState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoMovingState(JuanitoStateContext nc) {
		jc = nc;
	}
	
	public void shoot() {
		System.out.println("Im shooting & moving");
	}/*no cambia de estado*/
	public void stop() {
		if(Juanito.getInstance().getMygif() == Juanito.getInstance().getJmoveder() || Juanito.getInstance().getMygif() == Juanito.getInstance().getJsaltader())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJstillder());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJstillizq());
		jc.setCurrent(jc.getStaticState());
		Juanito.getInstance().setVelx(0);
	}
	public void hurt() {
		jc.setCurrent(jc.getInjuredState());
		System.out.println("Me hirieron");
	}
	public void heal() {
		System.out.println("No se puede heal en moving!");
	}
	public void die() {
		System.out.println("No se puede die en moving!");
	}
	
	@Override
	public void moveRight() {
		if(Juanito.getInstance().getMygif() != Juanito.getInstance().getJmoveder())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveder());
		if(Juanito.getInstance().getVelx() < 3)
			Juanito.getInstance().setVelx(3);
	}
	@Override
	public void moveLeft() {
		if(Juanito.getInstance().getMygif() != Juanito.getInstance().getJmoveizq())
			Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveizq());
		if(Juanito.getInstance().getVelx()> -3)
			Juanito.getInstance().setVelx(-3);
		
	}
	@Override
	public void moveJump() {
		if(Juanito.getInstance().getVelx() > 0)
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
		else
			Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
		Juanito.getInstance().setVely(-25);
	}
}