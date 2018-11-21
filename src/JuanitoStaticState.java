/*
 * This State models what Juanito can and cannot do when he is static. For example the gifs used are the ones portraying a standing Juanito
 */

public class JuanitoStaticState implements JuanitoState {
	JuanitoStateContext jc;

	public JuanitoStaticState(JuanitoStateContext nc) {
		jc = nc;
	}
	
	public void shoot() {
		Juanito.getInstance().getMychancla().getChanclasprite().totalFrames = 10;
		if(Juanito.getInstance().getPerfilDer()) { 
			Juanito.getInstance().getMychancla().setRight(true);
			Juanito.getInstance().getMychancla().setVelx(50);
			SoundLoader.getInstance().playSound("/throw.wav");
		}
		else {
			Juanito.getInstance().getMychancla().setRight(false);
			Juanito.getInstance().getMychancla().setVelx(-50);
			SoundLoader.getInstance().playSound("/throw.wav");
		}
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
		if(Juanito.getInstance().getY() ==600){ //==600
			
			if(Juanito.getInstance().getPerfilDer()) {
				Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltader());
				jc.setCurrent(jc.getMovingState());
			}
			else{
				Juanito.getInstance().setMygif(Juanito.getInstance().getJsaltaizq());
				jc.setCurrent(jc.getMovingState());
			}
			if(Juanito.getInstance().getY()==600 || Juanito.getInstance().isCd()) { //==600
				Juanito.getInstance().setVely(-25);
				SoundLoader.getInstance().playSound("/jump.wav");
			}
			
			if(Juanito.getInstance().getY()==550 || Juanito.getInstance().isCd()) { //==600
				Juanito.getInstance().setVely(-25);
				SoundLoader.getInstance().playSound("/jump.wav");
			}
		}
	}
}