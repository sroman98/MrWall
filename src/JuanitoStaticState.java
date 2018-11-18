
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
		Juanito.getInstance().setPerfilDer(true);
		Juanito.getInstance().setPerfilIzq(false);
	}
	@Override
	public void moveLeft() {
		Juanito.getInstance().setMygif(Juanito.getInstance().getJmoveizq());
		jc.setCurrent(jc.getMovingState());
		Juanito.getInstance().setVelx(-3);
		Juanito.getInstance().setPerfilDer(false);
		Juanito.getInstance().setPerfilIzq(true);
	}
	@Override
	public synchronized void moveJump() {
		System.out.println(Juanito.getInstance().getY());
		if(Juanito.getInstance().getY()>=500){//Aquí estaba en 600
			
			if(Juanito.getInstance().getPerfilDer()) { //Juanito.getInstance().getMygif() == Juanito.getInstance().getJstillder() && 
				Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
				jc.setCurrent(jc.getMovingState());
				Juanito.getInstance().setVely(-25);
			}
			else{
				Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
				jc.setCurrent(jc.getMovingState());
				Juanito.getInstance().setVely(-25);
			}
		}
	}
}